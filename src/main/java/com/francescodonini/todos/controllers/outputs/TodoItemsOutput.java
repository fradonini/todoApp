package com.francescodonini.todos.controllers.outputs;

import java.util.List;

import lombok.Data;

@Data
public class TodoItemsOutput {
	private List<TodoItemOutput> todoItems;
}
