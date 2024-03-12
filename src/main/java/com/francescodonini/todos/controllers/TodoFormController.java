package com.francescodonini.todos.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.francescodonini.todos.controllers.inputs.TodoItemInput;
import com.francescodonini.todos.mediator.ConcreteTodoMediator;
import com.francescodonini.todos.mediator.requests.DeleteRequest;
import com.francescodonini.todos.mediator.requests.GetSingleRequest;
import com.francescodonini.todos.mediator.requests.SaveRequest;
import com.francescodonini.todos.mediator.responses.GetSingleResponse;

import jakarta.validation.Valid;

@Controller
public class TodoFormController {

	@Autowired
	private ConcreteTodoMediator mediator;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping("/create-todo") 
	public String showCreateForm(TodoItemInput todoItem) {
		return "new-todo-item";
		
	}
	
	@PostMapping("/todo")
	public String createTodoItem(@Valid TodoItemInput input, BindingResult result, Model model) {		
		SaveRequest request = mapper.map(input, SaveRequest.class);
		mediator.send(request);
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteTodoItem(@PathVariable("id") Long id, Model model) {	
		DeleteRequest request = new DeleteRequest();
		request.setTodoId(id);
		mediator.send(request);
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		
		GetSingleRequest request = new GetSingleRequest();
		request.setTodoId(id);
		GetSingleResponse todoItem = (GetSingleResponse) mediator.send(request);
		
		model.addAttribute("todo", todoItem);
		return "edit-todo-item";
	}
	
    @PostMapping("/todo/{id}")
    public String updateTodoItem(@PathVariable("id") Long id, @Valid TodoItemInput input, BindingResult result, Model model) {

		SaveRequest request = mapper.map(input, SaveRequest.class);
		request.setTodoId(id);
        mediator.send(request);
        return "redirect:/";
    }
	
}
