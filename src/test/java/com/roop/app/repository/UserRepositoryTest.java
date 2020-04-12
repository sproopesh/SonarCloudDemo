package com.roop.app.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.roop.app.model.User;
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
	
	private User user;

	private User user1;
	private User user2;
	private List<User> userList;
	
	private Optional<User> optUser;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UserRepository repositroy;
	
	@Before
	public void setUp() throws Exception {
		
		user = new User("test1", "sp", "roopesh", 45125896);
		
		userList = new ArrayList<>();
		
		 user1=new User("test2", "jj", "tony", 8784565);
		 user2=new User("test3", "rr", "stark", 45125896);
		
		userList.add(user1);
		userList.add(user2);
		 
		
	}

	@After
	public void tearDown() throws Exception {
		user= null;
		userList=null;
	}

	
	@Test
	public void testSaveUser() {
		 User saveInDb= entityManager.persist(user);
		 Optional<User> getFromDb = repositroy.findById(saveInDb.getUserId());
		 
		 assertThat(getFromDb.get()).isEqualTo(saveInDb);
		 
	}
	
	@Test
	public void testFindUsers() {
		 User saveInDb1= entityManager.persist(user);
		 User saveInDb2= entityManager.persist(user2);
		 Optional<User> getFromDb = repositroy.findById(saveInDb2.getUserId());
		 
		/*
		 * Iterable<User> allTicketsFromDb = repositroy.findAll(); List<User> ticketList
		 * = new ArrayList<>();
		 * 
		 * for (User user : allTicketsFromDb) { ticketList.add(user); }
		 * assertThat(ticketList.size()).isEqualTo(2);
		 */
			
			
			
			
			assertThat(getFromDb.get()).isEqualTo(saveInDb2);
		 
	//	 List<User> list= repositroy.findAll();
		 
	}
	
	
	@Test
	public void testFindUserById() {
		 User saveInDb1= entityManager.persist(user);
		Optional<User> result= repositroy.findById(user.getUserId());
		assertEquals(user, result.get());
	}
	
}
