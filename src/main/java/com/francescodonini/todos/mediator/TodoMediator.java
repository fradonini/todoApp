package com.francescodonini.todos.mediator;

import com.francescodonini.todos.mediator.requests.MediatorRequest;
import com.francescodonini.todos.mediator.responses.MediatorResponse;

public interface TodoMediator {
	MediatorResponse send(MediatorRequest request);
}
