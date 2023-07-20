package todo.controller;

import java.net.URI;
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
import org.springframework.web.bind.annotation.RestController;

import todo.model.Admin;
import todo.model.AdminDTO;
import todo.model.User;
import todo.repository.UserRepository;
import todo.service.AdminService;

@RestController
@RequestMapping("/admins")
public class AdminController {
	@Autowired
    private  AdminService adminService;
	  
	 private UserRepository userRepository;

  

   /* @PostMapping("/create")
    public ResponseEntity<Admin> createAdmin(@RequestBody AdminDTO adminDTO) {
        Admin admin = adminService.createAdmin(adminDTO.getUsername(), adminDTO.getPassword());
        return ResponseEntity.ok(admin);
    }*/
	 
	    @PostMapping("/signup")
	    public ResponseEntity<Admin> signUp(@RequestBody Admin admin) {
	        Admin savedAdmin = adminService.signUp(admin);
	        return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
	    }
    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdmin(@PathVariable Long id) {
        Admin admin = adminService.getAdmin(id);
        if (admin == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(admin);
        }
    }

    @GetMapping("/getAllAdmins")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        return ResponseEntity.ok(admins);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/updateadmin")
    public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin) {
        Admin updatedAdmin = adminService.updateAdmin(admin);
        return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
    }
    
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = adminService.createUser(user.getUsername(), user.getPassword(), user.getEmail());
        return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
    }
}



