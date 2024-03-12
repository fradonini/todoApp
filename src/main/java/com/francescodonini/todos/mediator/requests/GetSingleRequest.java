package com.francescodonini.todos.mediator.requests;

import lombok.Data;

@Data
public class GetSingleRequest implements MediatorRequest  {
	private Long todoId;
}
