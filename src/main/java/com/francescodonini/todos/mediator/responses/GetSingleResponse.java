package com.francescodonini.todos.mediator.responses;

import java.time.Instant;

import lombok.Data;

@Data
public class GetSingleResponse implements MediatorResponse {

	private Long todoId;

	private String description;

	private Boolean isComplete;

	private Instant createdAt;

	private Instant updatedAt;
}
