package com.francescodonini.todos.mediator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.francescodonini.todos.mediator.handlers.MediatorHandler;
import com.francescodonini.todos.mediator.requests.MediatorRequest;
import com.francescodonini.todos.mediator.responses.MediatorResponse;

@Service
public class ConcreteTodoMediator implements TodoMediator {

//	@Autowired
//	private ApplicationContext appContext;
	
	@Autowired
	private List<MediatorHandler<? extends MediatorRequest, ? extends MediatorResponse>> handlers;

	@Override
	public MediatorResponse send(MediatorRequest request) {
		
		MediatorResponse result;
		try {
			String classFullName = request.getClass().getName();
			String className = classFullName.replace(request.getClass().getPackageName() + ".", "");
			char c[] = className.replace("Request", "Handler").toCharArray();
			// Elegant
			var handler = handlers
				.stream()
				.filter(x -> x.getClass().getSimpleName().equals(new String(c)))
				.findFirst()
				.orElse(null);
			var handlerMethod = handler.getClass().getMethod("handle", request.getClass());
			result = (MediatorResponse) handlerMethod.invoke(handler, request);
//			// Naive
//			c[0] = Character.toLowerCase(c[0]);
//			className = new String(c);
//			Object bean = appContext.getBean(className);
//			Method method = bean.getClass().getMethod("handle", request.getClass());
//			result = (MediatorResponse) method.invoke(bean, request);
		} catch (Exception e) {
			e.printStackTrace();
			result = new MediatorResponse() {};
		}
		return result;
	}
}
