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

import br.com.socialnetwork.entities.Comment;
import br.com.socialnetwork.repository.CommentRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class CommentController {

	@Autowired
	private CommentRepository commentRepository;

	@GetMapping("/comment/{commentId}")
	public ResponseEntity<Comment> getCommentById(@PathVariable("commentId") Long commentId) {
		Optional<Comment> commentData = commentRepository.findById(commentId);
		if (commentData.isPresent()) {
			return new ResponseEntity<>(commentData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/comment")
	public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
		try {
			Comment commentData = commentRepository.save(comment);
			return new ResponseEntity<>(commentData, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/comment/{commentId}")
	public ResponseEntity<Comment> updateUser(@PathVariable("commentId") Long commentId, @RequestBody Comment comment) {
		Optional<Comment> commentData = commentRepository.findById(commentId);

		if (commentData.isPresent()) {
			Comment commentForUpdate = commentData.get();
			commentForUpdate.setPostId(comment.getPostId());
			commentForUpdate.setText(comment.getText());
			commentForUpdate.setUserId(comment.getUserId());
			return new ResponseEntity<>(commentRepository.save(commentForUpdate), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("commentId") Long commentId) {
		try {
			commentRepository.deleteById(commentId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
