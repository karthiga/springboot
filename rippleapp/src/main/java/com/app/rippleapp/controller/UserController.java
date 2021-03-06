package com.app.rippleapp.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.app.rippleapp.bean.dao.User;
import com.app.rippleapp.service.UserService;
import com.app.rippleapp.utils.exception.EntityNotFoundException;

//Controller  
@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping(path = "/users")
	public List<User> getAllUsers() {
		return userService.findAll();
	}

//retrieves a specific user detail  
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable int id) {
		User user = userService.findOne(id);
		if (null == user) {
			throw new EntityNotFoundException(("No user found for id: " + id));
		}
		return user;
	}

//method that posts a new user detail   
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		User createdUser = userService.saveOrUpdate(user, null);
//Use servlet component builder to return appropriate http status
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdUser.getId()).toUri();
		// Return appropriate http status Created if new or accepted if update

		return ResponseEntity.created(location).build();
	}

	// method that posts a new user detail
	@PutMapping("/users/{id}")
	public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable Integer id) {

		userService.saveOrUpdate(user, id);

		// Return appropriate http status Created if new or accepted if update

		return ResponseEntity.accepted().build();

	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		 userService.deleteById(id);
		
	}
}