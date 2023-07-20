package todo.controller;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import todo.model.DuplicateUserException;
import todo.model.Todo;
import todo.model.User;
import todo.model.UserLoginDto;
import todo.repository.TodoRepository;
import todo.repository.UserRepository;
import todo.service.UserService;
import todo.service.TodoService;

import java.net.URI;
import java.util.HashMap;


@RestController
@RequestMapping("/users")
public class UserController {
	
	
		@Autowired
		UserService userService;
		@Autowired
		 UserRepository userRepository;
		@Autowired
		TodoRepository todoRepository;
		 @Autowired
		  TodoService service;
		
		
		 @GetMapping("/getusers")
	    public List<User> getAllUsers() {
	        return userService.findAllUsers();
	    }
		 
		    @PostMapping("/signin")
		    public ResponseEntity<String> signIn(@RequestBody UserLoginDto userLoginDto) {
		        User user = userService.signIn(userLoginDto.getUsername(), userLoginDto.getPassword());
		        return ResponseEntity.ok("You are logged in now");
		    }
		    
		   
		    @PostMapping("/signup")
		    public ResponseEntity<User> signUp(@RequestBody User user) {
		        User savedUser = userService.signUp(user);
		        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
		    }
		    
		    @PutMapping("/updateuser")
		    public ResponseEntity<User> updateUser(@RequestBody User user) {
		        User updatedUser = userService.updateUser(user);
		        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		    }

		    @ExceptionHandler(DuplicateUserException.class)
		    public ResponseEntity<Map<String, String>> handleDuplicateUserException(DuplicateUserException ex) {
		        Map<String, String> response = new HashMap<>();
		        response.put("error", "Username already exists");
		        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
		    }
		    
		    @DeleteMapping("/{id}")
		    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
		        userService.deleteUserById(id);
		        return ResponseEntity.noContent().build();
		    }
		   
		    
		    
		  /*  @PostMapping("/create")
		    public ResponseEntity<Todo> createTodoForUser(@PathVariable Long userId, @RequestBody Todo newTodo) {
		        // Call the createTodoForUser method to create the new todo for the user
		        Todo createdTodo = userRepository.createTodoForUser(userId, newTodo);

		        // Return a response entity with the created todo and HTTP status code 201 (Created)
		        return ResponseEntity.status(HttpStatus.CREATED).body(createdTodo);
		    }*/
		 	
}
