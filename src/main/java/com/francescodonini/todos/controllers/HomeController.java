package com.francescodonini.todos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.francescodonini.todos.controllers.outputs.TodoItemOutput;
import com.francescodonini.todos.controllers.outputs.TodoItemsOutput;
import com.francescodonini.todos.mediator.ConcreteTodoMediator;
import com.francescodonini.todos.mediator.requests.GetAllRequest;
import com.francescodonini.todos.mediator.responses.GetAllResponse;

@Controller
public class HomeController {
	
	@Autowired
	private ConcreteTodoMediator mediator;
	
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("index");
		GetAllResponse response = (GetAllResponse) mediator.send(new GetAllRequest());
		TodoItemsOutput output = new TodoItemsOutput();
		output.setTodoItems( response
				.getTodoItems()
				.stream()
				.map(x -> { 
					var r = new TodoItemOutput();
					r.setTodoId(x.getTodoId());
					r.setDescription(x.getDescription());
					r.setUpdatedAt(x.getUpdatedAt());
					r.setCreatedAt(x.getCreatedAt());
					r.setIsComplete(x.getIsComplete());
					return r;
				})
				.toList()
			);
		modelAndView.addObject("todoItems", output.getTodoItems());
		return modelAndView;
	}

}
