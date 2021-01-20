package com.app.rippleapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.rippleapp.bean.dao.User;
import com.app.rippleapp.repository.UserRepository;
import com.app.rippleapp.utils.exception.EntityNotFoundException;

@Service
public class UserService {
	@Autowired
	UserRepository userDao;

	// method that retrieve all users from the list
	public List<User> findAll() {
		List<User> users = userDao.findAll();

		if (users.size() > 0) {
			return users;
		} else {
			return new ArrayList<User>();
		}
	}

	// method that add the user in the list
	public User saveOrUpdate(User userToBeSavedOrUpdated, Integer id) {

		if (null != id) {
			return (userDao.findById(id).map(user -> {
				user.setFirstName(userToBeSavedOrUpdated.getFirstName());
				user.setLastName(userToBeSavedOrUpdated.getLastName());
				return userDao.save(user);
			}).orElseGet(() -> {
				return userDao.save(userToBeSavedOrUpdated);
			}));
		} else {
			return userDao.save(userToBeSavedOrUpdated);

		}
		/*
		 * Integer id = userToBeSavedOrUpdated.getId(); if (null != id) { Optional<User>
		 * user = userDao.findById(id); if (user.isPresent()) { User updateUser =
		 * user.get();
		 * 
		 * updateUser.setFirstName(userToBeSavedOrUpdated.getFirstName());
		 * updateUser.setLastName(userToBeSavedOrUpdated.getLastName());
		 * 
		 * updateUser = userDao.save(userToBeSavedOrUpdated); return updateUser; } else
		 * { throw new EntityNotFoundException(("No user found for id: " + id)); } }
		 * else { userToBeSavedOrUpdated = userDao.save(userToBeSavedOrUpdated); return
		 * userToBeSavedOrUpdated; }
		 */
	}

	// method that find a particular user from the list
	public User findOne(Integer id) {
		return userDao.findById(id).orElseThrow(() -> new EntityNotFoundException(("No user found for id: " + id)));
	}

	// method that delete a user resource
	public void deleteById(int id) {
		try {
			userDao.deleteById(id);
		} catch (Exception e) {
			throw new EntityNotFoundException(("No user found for id: " + id));
		}

		/*
		 * return Optional.ofNullable( userDao.findById(id).orElse(() -> { return
		 * userDao.de.delete.save(userToBeSavedOrUpdated)}));
		 */
	}
}
