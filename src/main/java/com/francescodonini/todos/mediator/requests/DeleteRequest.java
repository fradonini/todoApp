package com.francescodonini.todos.mediator.requests;

import lombok.Data;

@Data
public class DeleteRequest implements MediatorRequest {
	private Long todoId;
}
