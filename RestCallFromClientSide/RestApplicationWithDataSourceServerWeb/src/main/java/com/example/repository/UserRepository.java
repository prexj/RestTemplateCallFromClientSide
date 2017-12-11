package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.User;

@Transactional
public interface UserRepository extends CrudRepository<User, Long>{
	
	
	@Query("select u from User u where u.name = :userName")
    public List<User> findUser (@Param("userName") String userName);
	
}
