package br.com.socialnetwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.socialnetwork.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
