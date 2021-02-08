package br.com.socialnetwork.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.socialnetwork.entities.Comment;
import br.com.socialnetwork.repository.CommentRepository;

@Service	
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	public ResponseEntity<Comment> getCommentById(Long commentId) {
		Optional<Comment> commentData = commentRepository.findById(commentId);
		if (commentData.isPresent()) {
			return new ResponseEntity<>(commentData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<Comment> createComment(Comment comment) {
		try {
			Comment commentData = commentRepository.save(comment);
			return new ResponseEntity<>(commentData, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<Comment> updateUser(Long commentId, Comment comment) {
		Optional<Comment> commentData = commentRepository.findById(commentId);

		if (commentData.isPresent()) {
			Comment commentForUpdate = commentData.get();
			commentForUpdate.setPost(comment.getPost());
			commentForUpdate.setText(comment.getText());
			commentForUpdate.setUser(comment.getUser());
			return new ResponseEntity<>(commentRepository.save(commentForUpdate), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<HttpStatus> deleteUser(Long commentId) {
		try {
			commentRepository.deleteById(commentId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
