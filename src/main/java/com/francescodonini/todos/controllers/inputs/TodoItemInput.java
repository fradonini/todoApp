package com.francescodonini.todos.controllers.inputs;

import java.time.Instant;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TodoItemInput {
	
	private Long todoId;

	@NotBlank(message = "Description is required")
	private String description;

	private Boolean isComplete;

	private Instant createdAt;

	private Instant updatedAt;

}
