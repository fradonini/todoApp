package com.francescodonini.todos.mediator.handlers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.francescodonini.todos.mediator.requests.GetSingleRequest;
import com.francescodonini.todos.mediator.responses.GetSingleResponse;
import com.francescodonini.todos.models.TodoItem;
import com.francescodonini.todos.repositories.TodoItemRepository;

@Service
public class GetSingleHandler implements MediatorHandler<GetSingleRequest, GetSingleResponse> {

	private TodoItemRepository todoItemRepository;
	
	private ModelMapper mapper;
	
	public GetSingleHandler(ModelMapper mapper, TodoItemRepository todoItemRepository) {
		this.mapper = mapper;
		this.todoItemRepository = todoItemRepository;
	}
	
	@Override
	public GetSingleResponse handle(GetSingleRequest request) {
		
		TodoItem item = todoItemRepository
				.findById(request.getTodoId())
				.orElseThrow(() -> new IllegalArgumentException("TodoItem id: " + request.getTodoId() + "not found"));
		
		var response = mapper.map(item, GetSingleResponse.class);
		return response;
	}

}
