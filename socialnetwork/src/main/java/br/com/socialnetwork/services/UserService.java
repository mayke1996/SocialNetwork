package br.com.socialnetwork.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.socialnetwork.entities.User;
import br.com.socialnetwork.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public ResponseEntity<User> getUserById(Long userId) {
		Optional<User> userData = userRepository.findById(userId);
		if (userData.isPresent()) {
			return new ResponseEntity<>(userData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<User> createUser(User user) {
		try {
			User userData = userRepository.save(user);
			return new ResponseEntity<>(userData, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<User> updateUser(Long userId, User user) {
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

	public ResponseEntity<HttpStatus> deleteUser(Long id) {
		try {
			userRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
