package br.com.socialnetwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.socialnetwork.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

}
