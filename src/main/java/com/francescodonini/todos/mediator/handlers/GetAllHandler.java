package com.francescodonini.todos.mediator.handlers;

import com.francescodonini.todos.mediator.requests.GetAllRequest;
import com.francescodonini.todos.mediator.responses.GetAllResponse;
import com.francescodonini.todos.mediator.responses.GetSingleResponse;
import com.francescodonini.todos.repositories.TodoItemRepository;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class GetAllHandler implements MediatorHandler<GetAllRequest, GetAllResponse> {
	
	private TodoItemRepository todoItemRepository;
	
	private ModelMapper mapper;
	
	public GetAllHandler(ModelMapper mapper, TodoItemRepository todoItemRepository) {
		this.mapper = mapper;
		this.todoItemRepository = todoItemRepository;
	}

	@Override
	public GetAllResponse handle(GetAllRequest request) {
		
		List<GetSingleResponse> inner = todoItemRepository
				.findAll()
				.stream()
				.map(x -> mapper.map(x, GetSingleResponse.class))
				.toList();
		
		var response = new GetAllResponse();
		response.setTodoItems(inner);
		return response;
	}

}
