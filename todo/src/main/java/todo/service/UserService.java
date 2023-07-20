package todo.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import todo.model.InvalidCredentialsException;
import todo.model.Todo;
import todo.model.User;
import todo.model.UserAlreadyExistsException;
import todo.model.UserNotFoundException;
import todo.repository.AdminRepository;
import todo.repository.TodoRepository;
import todo.repository.UserRepository;
import todo.model.NotFoundException;

import java.util.Optional;
@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	TodoRepository todoRepository;
	

	public List<User> findAllUsers() {
	    return userRepository.findAll();
	}
    
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    

        public User signIn(String username, String password) {
            User user = userRepository.findByUsername(username);
            if (user == null || !user.getPassword().equals(password)) {
                throw new InvalidCredentialsException("Invalid username or password");
            }
            return user;
        }
       

        public User signUp(User user) throws UserAlreadyExistsException {
            // Check if a user with the same username or email already exists
            if (userRepository.findByUsername(user.getUsername()) != null) {
                throw new UserAlreadyExistsException("Username already exists");
            }
            if (userRepository.findByEmail(user.getEmail()) != null) {
                throw new UserAlreadyExistsException("Email already exists");
            }
            if (user.getPassword().length() <= 8) {
                throw new IllegalArgumentException("Password should be greater than 8 characters");
            }

            // Save the user to the database
            return userRepository.save(user);
        }
        
        
        public User updateUser(User user) {
            // Find the existing user by ID
            User existingUser = userRepository.findById(user.getId()).orElse(null);
            if (existingUser == null) {
                throw new IllegalArgumentException("User not found");
            }

            // Update the user fields
            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());

            // Save the updated user to the database
            return userRepository.save(existingUser);
        }
        
        public void deleteUserById(Long id) {
            User user = userRepository.findById(id).orElse(null);
            if (user == null) {
                throw new NotFoundException("User not found");
            }
            userRepository.delete(user);
        }
        
        
        
        /*public Todo createTodoForUser(Long userId, Todo newTodo) {
            // Find the user by ID
            User user = userRepository.findById(userId).orElse(null);
            if (user == null) {
                throw new UserNotFoundException("User not found");
            }

            // Add the new todo to the user's list of todos
            newTodo.setUser(user);
            user.getTodos().add(newTodo);

            // Save the updated user to the database
            userRepository.save(user);

            // Return the new todo
            return newTodo;
        }*/

}
