package todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import todo.model.Todo;
import todo.model.User;
import todo.model.UserAlreadyExistsException;
import todo.repository.TodoRepository;
import todo.repository.UserRepository;
import todo.model.Todo;
import todo.model.NotFoundException;
 @Service
public class TodoService {
	
	@Autowired
	TodoRepository todoRepository;
	UserRepository userRepository;
	

	public Todo getTodoById(Integer id) {
	    return todoRepository.findById(id).orElse(null);
	}
	
	public Todo updateTodoById(Integer id, Todo todo) {
	    Todo existingTodo = todoRepository.findById(id).orElse(null);
	    if (existingTodo == null) {
	        throw new NotFoundException("Todo not found");
	    }
	    existingTodo.setTask(todo.getTask());
	    existingTodo.setDate(todo.getDate());
	    existingTodo.setDone(todo.isDone());
	    existingTodo.setDescription(todo.getDescription());
	    return todoRepository.save(existingTodo);
	}
	
	public void deleteTodoById(Integer id) {
	    Todo todo = todoRepository.findById(id).orElse(null);
	    if (todo == null) {
	        throw new NotFoundException("Todo not found");
	    }
	    todoRepository.delete(todo);
	}
	
	/*public void addTodo(Todo newtodo) {
		
		todoRepository.save(newtodo);
	}*/
	
    public Todo createTodo(Todo todo)  {

        if (todo.getTask().length() <= 1) {
            throw new IllegalArgumentException("Task should not be empty ");
        }

        // Save the user to the database
        return todoRepository.save(todo);
    }

	public List<Todo> getAllTodo(){
		
		return todoRepository.findAll();
	}
	
	
	

	
	
}
