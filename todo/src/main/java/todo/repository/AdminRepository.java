package todo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import todo.model.Admin;
import todo.model.User;



@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
	
	Admin findByUsername(String username);
}

