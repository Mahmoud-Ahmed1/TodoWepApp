package todo.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import todo.model.Todo;
import todo.model.User;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	User findByUsername(String username);
	User findByEmail(String email);
	 void deleteById(Long id);
	/* Todo createTodoForUser(Long userId, Todo newTodo);*/

}
