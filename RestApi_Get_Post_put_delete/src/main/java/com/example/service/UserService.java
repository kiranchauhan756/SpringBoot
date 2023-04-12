package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.repository.UserRepository;

@Service
public class UserService implements UserServiceI{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User addUser(User user) {
		return this.userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
	List<User>list=(List<User>)this.userRepository.findAll();
	
	return list;
	}

	@Override
	public User findByEmail(String email) {
		User user=this.userRepository.findByEmail(email);
		
		return user;
	}

	@Override
	public User updateUser(String email,User user) {
		User user1=this.userRepository.findByEmail(email);
		user1.setEmail(user.getEmail());
		user1.setPassword(user.getPassword());
		return this.userRepository.save(user1);
	}

	@Override
	public void  deleteUser(String email) {
		User user1=this.userRepository.findByEmail(email);
		this.userRepository.delete(user1);	
	}
	
	

}
