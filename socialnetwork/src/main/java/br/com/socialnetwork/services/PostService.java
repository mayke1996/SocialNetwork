package br.com.socialnetwork.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.socialnetwork.entities.Post;
import br.com.socialnetwork.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	public ResponseEntity<Post> getPostById(Long postId) {
		Optional<Post> postData = postRepository.findById(postId);
		if (postData.isPresent()) {
			return new ResponseEntity<>(postData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Post> createPost(Post post) {
		try {
			Post postData = postRepository.save(post);
			return new ResponseEntity<>(postData, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Post> updatePost(Long postId, Post post) {
		Optional<Post> postData = postRepository.findById(postId);

		if (postData.isPresent()) {
			Post postForUpdate = postData.get();
//			postForUpdate.setCommentsId(post.getCommentsId());
//			postForUpdate.setImage(post.getImage());
			postForUpdate.setLink(post.getLink());
			postForUpdate.setText(post.getText());
			postForUpdate.setUserId(post.getUserId());
			return new ResponseEntity<>(postRepository.save(postForUpdate), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<HttpStatus> deletePost(Long postId) {
		try {
			postRepository.deleteById(postId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
