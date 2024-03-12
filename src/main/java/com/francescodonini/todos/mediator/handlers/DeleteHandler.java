package com.francescodonini.todos.mediator.handlers;

import org.springframework.stereotype.Service;

import com.francescodonini.todos.mediator.requests.DeleteRequest;
import com.francescodonini.todos.mediator.responses.DeleteResponse;
import com.francescodonini.todos.models.TodoItem;
import com.francescodonini.todos.repositories.TodoItemRepository;

@Service
public class DeleteHandler implements MediatorHandler<DeleteRequest, DeleteResponse> {
	
	private TodoItemRepository todoItemRepository;
	
	public DeleteHandler(TodoItemRepository todoItemRepository) {
		this.todoItemRepository = todoItemRepository;
	}

	@Override
	public DeleteResponse handle(DeleteRequest request) {
		
		TodoItem item = todoItemRepository
				.findById(request.getTodoId())
				.orElseThrow(() -> new IllegalArgumentException("TodoItem id: " + request.getTodoId() + "not found"));
		
		todoItemRepository.delete(item);
		return new DeleteResponse();
	}

}
