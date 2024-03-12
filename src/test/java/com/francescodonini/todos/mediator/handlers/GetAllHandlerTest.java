package com.francescodonini.todos.mediator.handlers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.francescodonini.todos.mediator.requests.GetAllRequest;
import com.francescodonini.todos.models.TodoItem;
import com.francescodonini.todos.repositories.TodoItemRepository;

class GetAllHandlerTest {

	@Mock
	private TodoItemRepository repository;
	
	private ModelMapper mapper;

	private GetAllHandler handler;
	
	private AutoCloseable mocks;

	@BeforeEach
	public void initMocks() {
		mocks = MockitoAnnotations.openMocks(this);
		mapper = new ModelMapper();
		handler = new GetAllHandler(mapper, repository);
	}

	@AfterEach
	void tearDown() throws Exception {
		mocks.close();
	}

	@Test
	void handleWithSuccess() {
		
		// Arrange
		var items = createTodoItems();
		doReturn(items).when(repository).findAll();
		
		// Act
		var result = handler.handle(new GetAllRequest());

		// Assert
		assertAll(
				() -> assertTrue(result.getTodoItems().getFirst().getTodoId() == items.getFirst().getId()),
				() -> assertTrue(result.getTodoItems().getLast().getTodoId() == items.getLast().getId())
			);
	}

	private List<TodoItem> createTodoItems() {
		List<TodoItem> mockData = new ArrayList<TodoItem>();
		TodoItem tdi1 = new TodoItem();
		tdi1.setId((long) 1);
		tdi1.setDescription("First Todo");
		tdi1.setCreatedAt(Instant.now());
		tdi1.setUpdatedAt(Instant.now());
		tdi1.setIsComplete(true);
		TodoItem tdi2 = new TodoItem();
		tdi2.setId((long) 2);
		tdi2.setDescription("Second Todo");
		tdi2.setCreatedAt(Instant.now());
		tdi2.setUpdatedAt(Instant.now());
		tdi2.setIsComplete(false);
		mockData.add(tdi1);
		mockData.add(tdi2);
		return mockData;
	}

}
