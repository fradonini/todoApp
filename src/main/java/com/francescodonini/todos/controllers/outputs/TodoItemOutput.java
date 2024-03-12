package com.francescodonini.todos.controllers.outputs;

import java.time.Instant;

import lombok.Data;

@Data
public class TodoItemOutput {
	
	private Long todoId;

	private String description;

	private Boolean isComplete;

	private Instant createdAt;

	private Instant updatedAt;

}
