package com.roop.app.controller;

import static org.mockito.Matchers.any;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roop.app.exception.UserAlreadyExistException;
import com.roop.app.exception.UserNotFoundException;
import com.roop.app.model.User;
import com.roop.app.service.UserService;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {

	private MockMvc mockMvc;
	private User user;
	private List<User> userList;
	
	@MockBean
	private UserService service;
	
	@InjectMocks
	private UserController controller;
	
	ObjectMapper MAPPER = new ObjectMapper();
	
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		 
		mockMvc= MockMvcBuilders.standaloneSetup(controller).build();
		
		user = new User("test1", "sp", "roopesh", 45125896);
		
		userList = new ArrayList<>();
		
		User user1=new User("test2", "jj", "tony", 8784565);
		User user2=new User("test3", "rr", "stark", 45125896);
		
		userList.add(user1);
		userList.add(user2);
		 
		
	}

	@After
	public void tearDown() throws Exception {
		user= null;
		userList=null;
	}

	@Test
	public void testCreateUser() throws Exception {
		when(service.saveUser(any())).thenReturn(user);
		
		mockMvc.perform(post("/createUser").contentType(MediaType.APPLICATION_JSON)
				.content(MAPPER.writeValueAsString(user))).andExpect(status().isCreated())
		        .andDo(MockMvcResultHandlers.print());
				
		
	}

	@Test
	public void testGetUser() throws Exception {
		 
		//when(service.getUserById(any())).thenThrow(UserNotFoundException.class);
		when(service.getUserById(any())).thenReturn(user);
		
		 
		mockMvc.perform(get("/getUser/test1").contentType(MediaType.APPLICATION_JSON))
		            .andExpect(status().isOk())
		            .andDo(MockMvcResultHandlers.print());
		
	}

	@Test
	public void testGetAllUsers() throws Exception {
		when(service.getAllUsers()).thenReturn(userList);
		
		mockMvc.perform(get("/getAllUsers").contentType(MediaType.APPLICATION_JSON))
		        .andExpect(status().isOk())
		        .andDo(MockMvcResultHandlers.print());
	}

}
