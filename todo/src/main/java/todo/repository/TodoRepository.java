package todo.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import todo.model.Todo;
@Repository
public interface TodoRepository extends JpaRepository<Todo,Integer>{

	Optional<Todo> findById(Integer id);
}
