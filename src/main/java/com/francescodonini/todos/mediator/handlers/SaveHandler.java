package com.francescodonini.todos.mediator.handlers;

import java.time.Instant;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.francescodonini.todos.mediator.requests.SaveRequest;
import com.francescodonini.todos.mediator.responses.SaveResponse;
import com.francescodonini.todos.models.TodoItem;
import com.francescodonini.todos.repositories.TodoItemRepository;

@Service
public class SaveHandler implements MediatorHandler<SaveRequest, SaveResponse> {
	
	@Autowired
	private TodoItemRepository todoItemRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public SaveResponse handle(SaveRequest request) {
		
		TodoItem actualItem;
		if (request.getTodoId() == null) {
			actualItem = new TodoItem();
			actualItem.setCreatedAt(Instant.now());
		} else {
			actualItem = todoItemRepository
					.findById(request.getTodoId())
					.orElseThrow(() -> new IllegalArgumentException("TodoItem id: " + request.getTodoId() + "not found"));
			actualItem.setIsComplete(request.getIsComplete());
		}
		
		actualItem.setDescription(request.getDescription());
		actualItem.setUpdatedAt(Instant.now());
		TodoItem responseItem = todoItemRepository.save(actualItem);
		var response = mapper.map(responseItem, SaveResponse.class);
		return response;
	}

}
