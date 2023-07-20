package todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import todo.model.Todo;
import todo.model.User;
import todo.repository.TodoRepository;
import todo.service.TodoService;

@RestController
@RequestMapping("/todo")
public class TodoController {
	
	@Autowired
	TodoService new_service;
	TodoRepository todoRepository;
	  @Autowired
	   TodoService todoService;
	
	  /* @PostMapping("/users/{userId}/todos")
	    public Todo createTodoForUser(@PathVariable Long userId, @RequestBody Todo todo) {
	        return todoService.createTodoForUser(userId, todo);
	    }*/
	   @GetMapping("/todos/{id}")
	    public Todo getTodoById(@PathVariable Integer id) {
	        return todoService.getTodoById(id);
	    }
	
	
	
	 /*  @GetMapping("/users/{userId}/todos")
	    public List<Todo> getTodosForUser(@PathVariable Long userId) {
	        return todoService.getTodosForUser(userId);
	    }*/
	   
	    // Update a specific todo by id
	    @PutMapping("/todos/{id}")
	    public Todo updateTodoById(@PathVariable Integer id, @RequestBody Todo todo) {
	        return todoService.updateTodoById(id, todo);
	    }

	
	    // Delete a specific todo by id
	    @DeleteMapping("/todos/{id}")
	    public ResponseEntity<Void> deleteTodoById(@PathVariable Integer id) {
	        todoService.deleteTodoById(id);
	        return ResponseEntity.noContent().build();
	    }
	    
		/*@PostMapping("/add")
		public String addTodo(@RequestBody Todo newtodo ) {
			
			new_service.addTodo(newtodo);
			
			return "saved done";
			
		}*/
	    
	    
	    @PostMapping("/create")
	    public ResponseEntity<Todo> create(@RequestBody Todo todo) {
	        Todo savedTodo = todoService.createTodo(todo);
	        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
	    }
		
		@GetMapping("/gettodo")
		public List<Todo> getAllMTodo (){

			
			return new_service.getAllTodo();
		}
		
		
		
	/*
	
	/*@DeleteMapping("delete/{id}")
	public ResponseEntity<?> deleteTodo(@PathVariable Integer id){
		
		if(this.todoRepository.findById(id).isPresent()){
			
			this.todoRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}*/
	
	
	
	
	
	

}
