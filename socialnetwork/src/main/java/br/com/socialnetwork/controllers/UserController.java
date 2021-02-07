package br.com.socialnetwork.controllers;

import java.util.Optional;

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
import br.com.socialnetwork.repository.UserRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/user/{userId}")
	  public ResponseEntity<User> getUserById(@PathVariable("userId") Long userId) {
	    Optional<User> userData = userRepository.findById(userId);
	    if (userData.isPresent()) {
	      return new ResponseEntity<>(userData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	 }
	
	 @PostMapping("/user")
	  public ResponseEntity<User> createUser(@RequestBody User user) {
	    try {
	    	User userData = userRepository.save(user);
	      return new ResponseEntity<>(userData, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	 
	 @PutMapping("/user/{userId}")
	  public ResponseEntity<User> updateUser(@PathVariable("userId") Long userId, @RequestBody User user) {
	    Optional<User> userData = userRepository.findById(userId);

	    if (userData.isPresent()) {
	    	User userForUpdate = userData.get();
	    	userForUpdate.setEmail(user.getEmail());
	    	userForUpdate.setName(user.getName());
	    	userForUpdate.setPassword(user.getPassword());
	      return new ResponseEntity<>(userRepository.save(userForUpdate), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	 
	 @DeleteMapping("/user/{id}")
	  public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
	    try {
	      userRepository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	
}
