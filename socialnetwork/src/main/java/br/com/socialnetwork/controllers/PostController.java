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

import br.com.socialnetwork.entities.Post;
import br.com.socialnetwork.repository.PostRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class PostController {

	@Autowired
	private PostRepository postRepository;

	@GetMapping("/post/{postId}")
	public ResponseEntity<Post> getPostById(@PathVariable("postId") Long postId) {
		Optional<Post> postData = postRepository.findById(postId);
		if (postData.isPresent()) {
			return new ResponseEntity<>(postData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/post")
	public ResponseEntity<Post> createPost(@RequestBody Post post) {
		try {
			Post postData = postRepository.save(post);
			return new ResponseEntity<>(postData, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/post/{postId}")
	public ResponseEntity<Post> updateUser(@PathVariable("postId") Long postId, @RequestBody Post post) {
		Optional<Post> postData = postRepository.findById(postId);

		if (postData.isPresent()) {
			Post postForUpdate = postData.get();
			postForUpdate.setCommentsId(post.getCommentsId());
			postForUpdate.setImage(post.getImage());
			postForUpdate.setLink(post.getLink());
			postForUpdate.setText(post.getText());
			postForUpdate.setUserId(post.getUserId());
			return new ResponseEntity<>(postRepository.save(postForUpdate), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/post/{postId}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("postId") Long postId) {
		try {
			postRepository.deleteById(postId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
