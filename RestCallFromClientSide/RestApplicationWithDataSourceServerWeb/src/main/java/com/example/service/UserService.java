package com.example.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.example.model.User;

@Component
//@Cacheable("User")
public interface UserService {
	
	User findById(long id);
    
	
    List<User> findUserByName(String userName);
     
    void saveUser(User user);
     
    void updateUser(User user);
     
    void deleteUserById(long id);
 
    List<User> findAllUsers();


	//boolean isUserExist(User user);
    
    
}
