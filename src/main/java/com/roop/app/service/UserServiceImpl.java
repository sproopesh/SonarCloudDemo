package com.roop.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roop.app.exception.UserAlreadyExistException;
import com.roop.app.exception.UserNotFoundException;
import com.roop.app.model.User;
import com.roop.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;
	
	@Override
	public User saveUser(User user) throws UserAlreadyExistException{
		// TODO Auto-generated method stub
		Optional<User> userExist= repository.findById(user.getUserId());
	//System.out.println("userExist "+userExist);
		if(userExist.isPresent()) {
			throw new UserAlreadyExistException("User already exists");
	}
		return repository.save(user);
	}

	
	@Override
	public User getUserById(String userId) throws UserNotFoundException {
		// TODO Auto-generated method stub
		
		Optional<User> optional= repository.findById(userId);
		   if(optional.isPresent()) {
			   return optional.get();
		   }else {
			   throw new UserNotFoundException("User Id is not present");
		   }
		
		
	}
	

	@Override
	public List<User> getAllUsers() {
		
		List<User> users= repository.findAll();
		   
		return users;
	}



	
	
	

}
