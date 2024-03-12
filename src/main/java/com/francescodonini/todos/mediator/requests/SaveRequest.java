package com.francescodonini.todos.mediator.requests;

import java.time.Instant;

import lombok.Data;

@Data
public class SaveRequest implements MediatorRequest {

	private Long todoId;

	private String description;

	private Boolean isComplete;

	private Instant createdAt;

	private Instant updatedAt;
}
