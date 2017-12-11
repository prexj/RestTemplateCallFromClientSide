package com.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.core.Context;
//import javax.ws.rs.core.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.model.User;
import com.example.service.UserService;
import com.example.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class HomeController {
	
	@Autowired
	UserService userService;
	
	/*@RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> listAllUsers() {
		System.out.println("Fetching Users list");
		List<User> users = userService.findAllUsers();
        if (users.isEmpty()) {
        	return new ArrayList<User>();
        }
        return users;
    }*/
	@RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers(@RequestHeader(name="bearer-key") String urlVariables,
    		HttpServletRequest rest,HttpServletResponse resp) {
		//@RequestHeader(name="bearer-key") String urlVariables,
		System.out.println("Fetching Users list");
		//@RequestParam(value = "urlVariables") String urlVariables,
		//String val = urlVariables.get("headerName");
		System.out.println("val ::: "+urlVariables);
		
		if(urlVariables.equals("abc123")) {
		List<User> users = userService.findAllUsers();
	        if (users.isEmpty()) {
	        	//return new ArrayList<User>();
	        	return new ResponseEntity(HttpStatus.NO_CONTENT);
	        }
	        //return users;
	        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
	
	@RequestMapping(value = "/userP", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsersP(@RequestParam(name="bearer-key") String urlVariables,
    		HttpServletRequest rest,HttpServletResponse resp) {
		System.out.println("Fetching Users list");
		//@RequestParam(value = "urlVariables") String urlVariables,
		//String val = urlVariables.get("headerName");
		System.out.println("val ::: "+urlVariables);
		
		//if(urlVariables.equals("abc123")) {
		List<User> users = userService.findAllUsers();
	        if (users.isEmpty()) {
	        	//return new ArrayList<User>();
	        	return new ResponseEntity(HttpStatus.NO_CONTENT);
	        }
	        //return users;
	        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		//}
		//return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
	
	@RequestMapping(value = "/user/{val}", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers1(@PathVariable("val") String urlVariables,
    		HttpServletRequest rest,HttpServletResponse resp) {
		System.out.println("Fetching Users list");
		//String val = urlVariables.get("headerName");
		System.out.println("val ::: "+urlVariables);
		//urlVariables.put(headerName, headerValue);
		//System.out.println("request body : " + requestEntity.getBody());
		//System.out.println("userAgent ::: "+userAgent);
        //HttpHeaders headers = requestEntity.getHeaders();
       // System.out.println("headers :: "+headers);
        //System.out.println("encoding >> "+encoding);
        //Map map = new HashMap();
    	//map.put("parameters", encoding);
    	//String validateval = validate(rest);
       // List<String> head =headers.get(0);
		
        //for (String string : head) {
			//System.out.println("string ::"+string);
		//}
        //my code
        /** how to get specific header info? **/
       // String cacheControl = head1.getRequestHeader("Cache-Control").get(0);
       // System.out.println("Cache-Control: "+cacheControl);
        /** get list of all header parameters from request **/
        /*Set<String> headerKeys = head1.getRequestHeaders().keySet();
        for(String header:headerKeys){
            System.out.println(header+":"+head1.getRequestHeader(header).get(0));
        }*/
        //end
        //System.out.println("request headers : " + headers);
       // HttpMethod method = requestEntity.getMethod();
        //System.out.println("request method : " + method);
        //System.out.println("request url: " + requestEntity.getUrl());
		if(urlVariables.equals("abc123")) {
		List<User> users = userService.findAllUsers();
	        if (users.isEmpty()) {
	        	//return new ArrayList<User>();
	        	return new ResponseEntity(HttpStatus.NO_CONTENT);
	        }
	        //return users;
	        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
	
	public String validate(HttpServletRequest rest) {
		String header = rest.getHeader("parameters");
		System.out.println("validate header :: "+header);
		return null;
	}
 
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity getUser(@PathVariable("id") long id) {
        System.out.println("Fetching User with id {}" + id);
        User user = userService.findById(id);
        /*if (user == null) {
            System.out.println("User with id {} not found." + id);
            return new User();
        }
        return user;*/
        //logger.info("Fetching User with id {}", id);
        //User user1 = userService.findById(id);
        if (user == null) {
            //logger.error("User with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("User with id " + id 
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
 
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        /*System.out.println("Creating User : {}" + user);
        String createVal ="";
        userService.saveUser(user);
        createVal ="user is created";
        HttpHeaders headers = new HttpHeaders();*/
    	/* if (userService.isUserExist(user)) {
            // logger.error("Unable to create. A User with name {} already exist", user.getName());
             return new ResponseEntity(new CustomErrorType("Unable to create. A User with name " + 
             user.getName() + " already exist."),HttpStatus.CONFLICT);
         }*/
         userService.saveUser(user);
  
         HttpHeaders headers = new HttpHeaders();
         headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
         return new ResponseEntity<String>(headers, HttpStatus.CREATED);
        //return createVal;
    }
 
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public String updateUser(@PathVariable("id") long id, @RequestBody User user) {
        System.out.println("Updating User with id {}" +  id);
        
        User currentUser = userService.findById(id);
        String updatedVal ="";
        if (currentUser == null) {
            System.out.println("Unable to update. User with id {} not found." + id);
            updatedVal =id+" id Of User do not updated";
            return  updatedVal;
        }
        
        currentUser.setName(user.getName());
        currentUser.setAge(user.getAge());
        currentUser.setSalary(user.getSalary());
 
        userService.updateUser(currentUser);
        updatedVal =id+" id Of User is updated";
        return updatedVal;
    }
    
 
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting User with id {}" + id);
        
        User user = userService.findById(id);
        String deleteVal ="";
        if (user == null) {
            System.out.println("Unable to delete. User with id {} not found." + id);
            deleteVal=id+" id of user are not delete";
            return deleteVal;
        }
        userService.deleteUserById(id);
        deleteVal =id+" id of user are delete";
        return deleteVal;
    }
    
    @RequestMapping(value = "/userbyname/{name}", method = RequestMethod.GET)
    public String getUserByName(@PathVariable("name") String name) {
    	System.out.println("Fetching User with name {}" + name);
        
        List<User> users = userService.findUserByName(name);
        String getUserName ="";
        if (users == null) {
            System.out.println("Unable to fatch. User with name {} not found." + name);
            getUserName=name+" Unable to found";
            return getUserName;
        }
        getUserName=name+" user to fatch";
        return getUserName;
    }
    
    @RequestMapping(value = "/searching/{name}", method = RequestMethod.GET)
    public List<User> searchingUserByName(@PathVariable("name") String name) {
    	System.out.println("Fetching User with name {}" + name);
        
        List<User> users = userService.findAllUsers();
        Predicate<User> p1 = e -> e.getName().equalsIgnoreCase(name)  && e.getDflag() == 1;
        boolean b1 = users.stream().anyMatch(p1);
	    System.out.println(b1);
		//List<String>  val1 =emplist.stream().filter(e->e.getFirstname() == search).map(e->e.getFirstname()).collect(Collectors.toList());
		List<User>  val1 =(List<User>) users.stream().parallel().filter(e->e.getName().equalsIgnoreCase(name) && e.getDflag() ==1).sequential().collect(Collectors.toList());
      // Long  val2 =plist.stream().filter(p->p.name == "pratik").map(p->p.name).collect(Collectors.counting());
       System.out.println("val1 ::"+val1);
       /* String getUserName ="";
        if (users == null) {
            System.out.println("Unable to fatch. User with name {} not found." + name);
            getUserName=name+" Unable to found";
            return new ArrayList<User>();
        }
        getUserName=name+" user to fatch";*/
       
       /*if (val1 == null) {
           //logger.error("User with id {} not found.", id);
           return (List<User>) new ResponseEntity(new CustomErrorType("User with name " + name 
                   + " not found"), HttpStatus.NOT_FOUND);
       }*/
       //return new ResponseEntity<User>(user, HttpStatus.OK);
        return val1;
    }
    
    @RequestMapping(value = "/testparam/{name}/{salary}", method = RequestMethod.GET)
    public ResponseEntity<String> getSalaryByName(@PathVariable("name") String name,@PathVariable("salary") String salary) {
    	System.out.println("Fetching User with name {}" + name);
    	System.out.println("Fetching User with salary {}" + salary);
        
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
    
}
