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

import br.com.socialnetwork.entities.Comment;
import br.com.socialnetwork.services.CommentService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@GetMapping("/comment/{commentId}")
	public ResponseEntity<Comment> getCommentById(@PathVariable("commentId") Long commentId) {
		return commentService.getCommentById(commentId);
	}

	@PostMapping("/comment")
	public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
		return commentService.createComment(comment);
	}

	@PutMapping("/comment/{commentId}")
	public ResponseEntity<Comment> updateUser(@PathVariable("commentId") Long commentId, @RequestBody Comment comment) {
		return commentService.updateUser(commentId, comment);
	}

	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("commentId") Long commentId) {
		return commentService.deleteUser(commentId);
	}

}
