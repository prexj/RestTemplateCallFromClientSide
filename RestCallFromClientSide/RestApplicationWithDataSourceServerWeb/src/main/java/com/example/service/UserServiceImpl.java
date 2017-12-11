package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.repository.UserRepository;
import com.example.model.User;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepo;
	
	public List<User> findAllUsers() {
        return (List<User>) userRepo.findAll();
    }
     
    public User findById(long id) {
        return userRepo.findOne(id);
    }
     
    public List<User> findUserByName(String userName) {
        return userRepo.findUser(userName);
    }
     
    public void saveUser(User user) {
    	userRepo.save(user);
    }
 
    public void updateUser(User user) {
    	userRepo.save(user);
    }
 
    public void deleteUserById(long id) {
    	userRepo.delete(id);
    }
    	
}
