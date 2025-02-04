package com.eventmanagement.user.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.eventmanagement.user.entity.User;

@Service
public interface UserService {

	public User getUser(String userName);

	public List<User> getAllUsers();

	public User createUser(@Valid User user);

	public User updateUser(@Valid User user);

	public boolean deleteUser(String userName);

	public Boolean isAdmin(String userName);

}
