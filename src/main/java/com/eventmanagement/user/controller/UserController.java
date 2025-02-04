package com.eventmanagement.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.eventmanagement.user.entity.User;
import com.eventmanagement.user.service.UserService;

@RestController
@RequestMapping("/api/eventmanagement/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("healthcheck")
	public ResponseEntity<String> healthcheck() {
		return new ResponseEntity<>("User Service is Running", HttpStatus.OK);
	}

	@GetMapping("getUser/{userName}")
	public ResponseEntity<User> getUserDetails(@PathVariable(value = "userName", required = true) String userName) {
		User user = null;
		if (null != userName && !userName.isBlank()) {
			user = userService.getUser(userName);
		} else {
			return new ResponseEntity<User>(new User(), HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(user);
	}

	@GetMapping("getAllUsers")
	public ResponseEntity<List<User>> getUserDetails() {
		return ResponseEntity.ok(userService.getAllUsers());
	}

	@PostMapping("addUser")
	public ResponseEntity<String> addUser(@Valid @RequestBody User user) {
		User addedUser = null;
		addedUser = userService.createUser(user);
		if (null != addedUser) {
			return ResponseEntity.ok("User added Succesfully!!!!");
		} else {
			return new ResponseEntity<String>("User Already Exists.", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("updateUser")
	public ResponseEntity<String> updateUser(@Valid @RequestBody User user) {
		User updatedUser = null;
		updatedUser = userService.updateUser(user);

		if (null != updatedUser) {
			return ResponseEntity.ok("User Updates Succesfully !!!");
		} else {
			return new ResponseEntity<String>("User Not Found", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("deleteUser/{userName}")
	public ResponseEntity<String> deleteUser(@PathVariable(value = "userName", required = false) String userName) {
		if (userService.deleteUser(userName)) {
			return ResponseEntity.ok("User Deleted Successfully");
		} else {
			return new ResponseEntity<String>("User Does Not Exist/Error Occured", HttpStatus.CONFLICT);
		}
	}

	@GetMapping("isAdmin/{userName}")
	public ResponseEntity<Boolean> isAdmin(@PathVariable(value = "userName", required = true) String userName) {
		Boolean isAdmin = false;
		if (null != userName && !userName.isBlank()) {
			isAdmin = userService.isAdmin(userName);
			return ResponseEntity.ok(isAdmin);
		} else {
			return ResponseEntity.ok(false);
		}
	}

	@GetMapping("validateUser/{userName}/{userEmail}")
	public ResponseEntity<Boolean> validateUser(@PathVariable(value = "userName", required = true) String userName,
			@PathVariable(value = "userEmail", required = true) String userEmail) {
		Boolean isUserValid = false;
		if (null != userName && !userName.isBlank()) {
			User user = userService.getUser(userName);
			if (null != user && null != user.getUserName() && null != user.getUserEmail()) {
				if (null != userEmail && user.getUserEmail().equalsIgnoreCase(userEmail)) {
					return ResponseEntity.ok(true);
				}
			}
		}
		return ResponseEntity.ok(isUserValid);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationException(MethodArgumentNotValidException mex) {

		Map<String, String> validationError = new HashMap<String, String>();

		mex.getBindingResult().getAllErrors().forEach(error -> {
			validationError.put(error.getObjectName(), error.getDefaultMessage());
		});

		return validationError;

	}

}
