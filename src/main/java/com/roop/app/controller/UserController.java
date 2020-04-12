package com.roop.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roop.app.exception.UserAlreadyExistException;
import com.roop.app.exception.UserNotFoundException;
import com.roop.app.model.User;
import com.roop.app.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;


	@PostMapping("/createUser")
	public ResponseEntity<?> createUser(@RequestBody User user){
		ResponseEntity<?> entity;

		try {
			entity= new ResponseEntity<>(service.saveUser(user),HttpStatus.CREATED);
		}
		catch(UserAlreadyExistException e) {
			entity= new ResponseEntity<>("User Id Already exisits Exception",HttpStatus.CONFLICT);
		}
		catch (Exception e) {
			entity= new ResponseEntity<>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;
	}

	
	@GetMapping("getUser/{userId}")
	public ResponseEntity<?> getUser(@PathVariable("userId") String userId){
		ResponseEntity<?> entity;
		try {
		entity= new ResponseEntity<>(service.getUserById(userId),HttpStatus.OK);	
		}catch(UserNotFoundException e) {
			entity= new ResponseEntity<>("User Id Not Found",HttpStatus.NOT_FOUND);	
		}catch (Exception e) {
			entity= new ResponseEntity<>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}
	

	@GetMapping("/getAllUsers")
	public ResponseEntity<?> getAllUsers(){
		ResponseEntity<?> entity;
		try {
			entity= new ResponseEntity<>(service.getAllUsers(),HttpStatus.OK);
		}catch (Exception e) {
			entity= new ResponseEntity<>("Internal server error",HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return entity;
	}


}
