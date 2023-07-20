package todo.service;

import java.util.List;


import org.springframework.stereotype.Service;

import todo.model.Admin;
import todo.model.AdminAlreadyExistsException;
import todo.model.User;
import todo.model.UserAlreadyExistsException;
import todo.repository.AdminRepository;
import todo.repository.UserRepository;


@Service
public class AdminService {
	
    private AdminRepository adminRepository;
    private UserRepository userRepository;

    // Inject the UserRepository instance using constructor injection
    public AdminService(AdminRepository adminRepository, UserRepository userRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
    }


    /*public Admin createAdmin(String username, String password) {
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        return adminRepository.save(admin);
    }*/
    
    public Admin signUp(Admin admin) throws AdminAlreadyExistsException {
        // Check if a user with the same username or email already exists
        if (adminRepository.findByUsername(admin.getUsername()) != null) {
            throw new AdminAlreadyExistsException("Username already exists");
        }
        if (admin.getPassword().length() <= 8) {
            throw new IllegalArgumentException("Password should be greater than 8 characters");
        }

        // Save the user to the database
        return adminRepository.save(admin);
    }

    public Admin getAdmin(Long id) {
        return adminRepository.findById(id).orElse(null);
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }
    
    
    public Admin updateAdmin(Admin admin) {
        // Find the existing user by ID
        Admin existingAdmin = adminRepository.findById(admin.getId()).orElse(null);
        if (existingAdmin == null) {
            throw new IllegalArgumentException("Admin not found");
        }

        // Update the user fields
        existingAdmin.setUsername(admin.getUsername());
        existingAdmin.setPassword(admin.getPassword());
        

        // Save the updated user to the database
        return adminRepository.save(existingAdmin);
    }
    
    
    public void deleteUser(Long id) {
    	
    	userRepository.deleteById(id);
    }
    
    public User createUser(String username, String password, String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        return userRepository.save(user);
    }
}

