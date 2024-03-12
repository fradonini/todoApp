package com.francescodonini.todos.mediator.handlers;

import com.francescodonini.todos.mediator.requests.MediatorRequest;
import com.francescodonini.todos.mediator.responses.MediatorResponse;

public interface MediatorHandler<T extends MediatorRequest, S extends MediatorResponse> {
	S handle(T request);
}
