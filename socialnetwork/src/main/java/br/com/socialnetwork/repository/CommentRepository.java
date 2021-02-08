package br.com.socialnetwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.socialnetwork.entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

}
