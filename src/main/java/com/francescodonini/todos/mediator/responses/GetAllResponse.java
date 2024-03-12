package com.francescodonini.todos.mediator.responses;

import java.util.List;

import lombok.Data;

@Data
public class GetAllResponse implements MediatorResponse {
	private List<GetSingleResponse> todoItems;
}
