package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.service.UserServiceI;

@RestController
public class HomeController {
	@Autowired
	private UserServiceI userService;
	
	@PostMapping("/home")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		User user1=	 this.userService.addUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
		
	}
	
	@GetMapping("/find")
	public ResponseEntity<List<User>> getAllUser() {
		List<User>list=this.userService.getAllUser();
		if(list.size()<=0) {
			return ResponseEntity.status(HttpStatus.CREATED.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(list);
	}
	
	@GetMapping("/findName/{email}")
	public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
	    User user=this.userService.findByEmail(email);
	    if(user==null) {
	    	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    }
	    return ResponseEntity.of(Optional.of(user));
	}
	
	@PutMapping("/update/{email}")
	public ResponseEntity<User> updateEmail(@PathVariable String email,@RequestBody User user){
		User user1=this.userService.updateUser(email,user);
		user1.setEmail(user.getEmail());
		user1.setPassword(user.getPassword());
		if(user1==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
    
	@DeleteMapping("/delete/{email}")
	public void deleteEmail(@PathVariable String email){
	   this.userService.deleteUser(email);
	}
}
