package com.roop.app.service;

import java.util.List;

import com.roop.app.exception.UserAlreadyExistException;
import com.roop.app.exception.UserNotFoundException;
import com.roop.app.model.User;

public interface UserService {

	public  User saveUser(User user) throws UserAlreadyExistException;
	
	public User getUserById(String name) throws UserNotFoundException;
	
	public List<User> getAllUsers();
	
	
	
	
}
