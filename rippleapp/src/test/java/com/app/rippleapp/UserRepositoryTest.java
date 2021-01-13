package com.app.rippleapp;

/*import org.junit.runner.RunWith;*/
import org.springframework.boot.test.context.SpringBootTest;

import com.app.rippleapp.service.UserService;
//@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserService.class)
public class UserRepositoryTest {
	/*
	 * @Autowired private UserRepository userRepository;
	 * 
	 * @Test public void contextLoads() { }
	 * 
	 * @Test public void givenUserProfile_whenAddUser_thenCreateNewUser() { User
	 * user = new User(); user.setFirstName("John"); user.setLastName("Doe");
	 * userRepository.save(user); List<User> users = (List<User>)
	 * userRepository.findAll(); assertFalse(users.isEmpty());
	 * 
	 * String firstName = "Aliko"; String lastName = "Dangote"; User user1 =
	 * userRepository.findById(users.get(0).getId()).get();
	 * user1.setLastName(lastName); user1.setFirstName(firstName);
	 * userRepository.save(user1);
	 * 
	 * user = userRepository.findById(user.getId()).get();
	 * assertEquals(user.getFirstName(), firstName);
	 * assertEquals(user.getLastName(), lastName);
	 * 
	 * userRepository.deleteById(user.getId()); assertTrue( ((List<User>)
	 * userRepository.findAll()).isEmpty()); }
	 */
}