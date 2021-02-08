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

import br.com.socialnetwork.entities.Post;
import br.com.socialnetwork.services.PostService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class PostController {

	@Autowired
	private PostService postService;

	@GetMapping("/post/{postId}")
	public ResponseEntity<Post> getPostById(@PathVariable("postId") Long postId) {
		return postService.getPostById(postId);
	}

	@PostMapping("/post")
	public ResponseEntity<Post> createPost(@RequestBody Post post) {
		return postService.createPost(post);
	}

	@PutMapping("/post/{postId}")
	public ResponseEntity<Post> updatePost(@PathVariable("postId") Long postId, @RequestBody Post post) {
		return postService.updatePost(postId, post);
	}

	@DeleteMapping("/post/{postId}")
	public ResponseEntity<HttpStatus> deletePost(@PathVariable("postId") Long postId) {
		return postService.deletePost(postId);
	}

}
