package br.com.socialnetwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.socialnetwork.entities.User;
import br.com.socialnetwork.services.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/user/{userId}")
	  public ResponseEntity<User> getUserById(@PathVariable("userId") Long userId) {
		return userService.getUserById(userId);
	}
	
	 @PostMapping("/user")
	  public ResponseEntity<User> createUser(@RequestBody User user) {
	   return userService.createUser(user);
	  }
	 
	 @PutMapping("/user/{userId}")
	  public ResponseEntity<User> updateUser(@PathVariable("userId") Long userId, @RequestBody User user) {
		 return userService.updateUser(userId, user);
	 }
	 
	 @DeleteMapping("/user/{id}")
	  public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
		 return userService.deleteUser(id);
	 }
	
	
}
