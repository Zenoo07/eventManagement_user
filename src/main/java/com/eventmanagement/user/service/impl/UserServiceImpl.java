package com.eventmanagement.user.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eventmanagement.user.entity.User;
import com.eventmanagement.user.repository.UserRepository;
import com.eventmanagement.user.service.UserService;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public User getUser(String userName) {
		if (isUserExistsById(userName)) {
			Optional<User> user = userRepo.findById(userName);
			return user.get();
		} else {
			return null;
		}
	}

	private boolean isUserExistsById(String userName) {
		return userRepo.existsById(userName);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User createUser(User user) {
		if (isUserExistsById(user.getUserName())) {
			return null;
		} else {
			userRepo.save(user);
		}
		return user;
	}

	@Override
	public User updateUser(User user) {
		if (isUserExistsById(user.getUserName())) {
			userRepo.save(user);
		}else {
			return null;
		}
		return user;
	}

	@Override
	public boolean deleteUser(String userName) {
		if (isUserExistsById(userName)) {
			userRepo.deleteById(userName);
			return true;
		}
		return false;
	}

	@Override
	public Boolean isAdmin(String userName) {
		if (isUserExistsById(userName)) {
			Optional<User> user = userRepo.findById(userName);
			return user.get().isAdmin();
		} else {
			return null;
		}
	}

}
