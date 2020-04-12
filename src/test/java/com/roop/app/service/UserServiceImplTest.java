package com.roop.app.service;

import static org.mockito.Matchers.any;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.roop.app.exception.UserAlreadyExistException;
import com.roop.app.exception.UserNotFoundException;
import com.roop.app.model.User;
import com.roop.app.repository.UserRepository;

public class UserServiceImplTest {


	private User user;

	private List<User> userList;
	
	private Optional<User> optUser;

	@Mock
	private UserRepository repository;

	@InjectMocks
	private UserServiceImpl serviceImpl;






	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		user = new User("test1", "sp", "roopesh", 45125896);

		userList = new ArrayList<>();

		User user1=new User("test2", "jj", "tony", 8784565);
		User user2=new User("test3", "rr", "stark", 45125896);

		userList.add(user1);
		userList.add(user2);
		
		optUser = Optional.of(user);


	}



	@After
	public void tearDown() throws Exception {
		user= null;
		userList=null;
	}

	@Test
	public void testSaveUser() throws Exception {
		 when(repository.save(any())).thenReturn(user);
		 
		 User result= serviceImpl.saveUser(user);
		 assertEquals(user, result);
		 //verify(repository,times(1)).save(any());
		
	}

	@Test
	public void testGetUserById() throws Exception {
		when(repository.findById("test1")).thenReturn(optUser);
		
		User result= serviceImpl.getUserById("test1");
	//	System.out.println("***result "+result);
		
		assertEquals(user, result);
		//verify(repository, times(1)).findById(any());
	}

	@Test
	public void testGetAllUsers() {
		when(repository.findAll()).thenReturn(userList);
		
		List<User> result = serviceImpl.getAllUsers();
		
		assertEquals(userList, result);
		//verify(repository, times(1)).findAll();
	}

}
