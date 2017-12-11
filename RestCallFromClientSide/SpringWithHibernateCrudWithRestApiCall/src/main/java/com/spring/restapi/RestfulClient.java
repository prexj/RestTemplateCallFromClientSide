package com.spring.restapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonObject;
//import com.spring.controller.LinkedHashMap;
//import com.javasampleapproach.resttemplate.model.Customer;
import com.spring.model.User;

public class RestfulClient {
	static RestTemplate restTemplate =new RestTemplate();
	
	/*public RestfulClient(){
		restTemplate = new RestTemplate();
	}*/
	
	/**
	 * post entity
	 */
	/*public void postEntity(){
		System.out.println("Begin /POST request!");
		String postUrl = "http://localhost:8080/post";
		Customer customer = new Customer(123, "Jack", 23);
		ResponseEntity<String> postResponse = restTemplate.postForEntity(postUrl, customer, String.class);
		System.out.println("Response for Post Request: " + postResponse.getBody());
	}*/
	
	
	/**
	 * get entity
	 */
	public static List getEntity2(){
		
		
		//start
		try {
			System.out.println("Begin /GET request!");///api
			String getUrl = "http://localhost:8081/rest/api/user";
			System.out.println(" get url >> "+getUrl);
			
			String headerValue ="abc123";
			Map<String, String> urlVariables = new HashMap<String, String>();
		    urlVariables.put("bearer-key", headerValue);
			
		    HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAll(urlVariables);
			
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<List> response = restTemplate.exchange(getUrl, HttpMethod.GET, entity, List.class);
			System.out.println(response.getBody());
			
			//ResponseEntity<List> result = restTemplate.exchange(getUrl, HttpMethod.GET, entity, List.class);
			//List<LinkedHashMap<String, Object>> usersMap1 = result.getBody();

			//List<LinkedHashMap<String, Object>> usersMap1 = restTemplate.getForObject(getUrl ,List.class,entity);
			//List<LinkedHashMap<String, Object>> usersMap1 = restTemplate.getForObject(getUrl ,List.class,urlVariables);
			//List<LinkedHashMap<String, Object>> usersMap1 = restTemplate.getForObject(getUrl, List.class, entity);
	        //List<LinkedHashMap<String, Object>> usersMap1 = restTemplate.getForObject(getUrl, List.class);
	         
	      /*if(usersMap1!=null){
	            for(LinkedHashMap<String, Object> map : usersMap1){
	                System.out.println("User : id="+map.get("id")+", Name="+map.get("name")+", Age="+map.get("age")+", Salary="+map.get("salary"));;
	            }
	            return usersMap1;
	        }else{
	            System.out.println("No user exist----------");
	            return null;
	        }*/
		
			return response.getBody();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			System.out.println("hie1");
			return null;
		}
	}
	
	public static List getEntity3(){
		
		
		//start
		try {
			System.out.println("Begin /GET request!");///api
			String getUrl = "http://localhost:8081/rest/api/userP";
			System.out.println(" get url >> "+getUrl);
			String headerValue ="abc123";
			Map<String, String> urlVariables = new HashMap<String, String>();
		    urlVariables.put("bearer-key", headerValue);
			
		    HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAll(urlVariables);
			
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			RestTemplate restTemplate = new RestTemplate();
			//restTemplate.getForObject(getUrl ,List.class,urlVariables);
			//List<LinkedHashMap<String, Object>> usersMap1 = (List<LinkedHashMap<String, Object>>) restTemplate.exchange(getUrl, HttpMethod.GET, entity, List.class);
			//List<LinkedHashMap<String, Object>> usersMap1 = restTemplate.postForObject(getUrl,urlVariables,List.class);
			//List<LinkedHashMap<String, Object>> usersMap1 = restTemplate.getForObject(getUrl ,List.class,urlVariables);
			ResponseEntity<List> response = (ResponseEntity<List>) restTemplate.getForObject(getUrl, List.class, urlVariables);
			//ResponseEntity<List> response = (ResponseEntity<List>) restTemplate.getForObject(getUrl, List.class, entity);
	        //List<LinkedHashMap<String, Object>> usersMap1 = restTemplate.getForObject(getUrl, List.class);
	         
	       /*if(usersMap1!=null){
	            for(LinkedHashMap<String, Object> map : usersMap1){
	                System.out.println("User : id="+map.get("id")+", Name="+map.get("name")+", Age="+map.get("age")+", Salary="+map.get("salary"));;
	            }
	            return usersMap1;
	        }else{
	            System.out.println("No user exist----------");
	            return null;
	        }*/
		
		return null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			System.out.println("hie1");
			return null;
		}
	}
	
	public static User getUser(Integer searchId){
        System.out.println("Testing getUser API----------");
        String getUrl = "http://localhost:8081/rest/api/user/"+searchId;
		System.out.println(" get url >> "+getUrl);
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject(getUrl, User.class);
        System.out.println(user);
        return user;
    }
	
	
	public static User searchUser(String searchName){
        System.out.println("Testing getUser API----------");
        String getUrl = "http://localhost:8081/rest/api/user/"+searchName;
		System.out.println(" get url >> "+getUrl);
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject(getUrl, User.class);
        System.out.println(user);
        return user;
    }
	
	public static String getEntity(){
		/*System.out.println("Begin /GET request!");
		String getUrl = "http://localhost:8080/get?id=1&name='Mary'&age=20";
		ResponseEntity<Customer> getResponse = restTemplate.getForEntity(getUrl, Customer.class);
		if(getResponse.getBody() != null){
			System.out.println("Response for Get Request: " + getResponse.getBody().toString());	
		}else{
			System.out.println("Response for Get Request: NULL");
		}*/
		
		//start
		try {
		System.out.println("Begin /GET request!");///api
		String getUrl = "http://localhost:8081/rest/api/user";
		System.out.println(" get url >> "+getUrl);
		//List<User> u = getUrl.
		//ResponseEntity<User> getResponse = restTemplate.getForEntity(getUrl, User.class);
		
		//new
		//response = (ResponseEntity<String>) restTemplate.exchange(getUrl, HttpMethod.GET, entity, String.class);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>("parameters",headers);
		//headers.set(headerName, headerValue);
		ResponseEntity<User[]> responseEntity = restTemplate.getForEntity(getUrl, User[].class);
		
		System.out.println("responseEntity.getBody() >> "+responseEntity.getBody());
		/*User objects = responseEntity.getBody();
		MediaType contentType = responseEntity.getHeaders().getContentType();
		HttpStatus statusCode = responseEntity.getStatusCode();*/
		System.out.println("responseEntity.getStatusCode() ::: "+responseEntity.getStatusCode());

		//end
		
		
		if(responseEntity.getBody() != null){
			System.out.println("Response for Get Request: " + responseEntity.getBody().toString());
			return responseEntity.getBody().toString();
		}else{
			System.out.println("Response for Get Request: NULL");
			getEntity();
		}
		System.out.println("hie");
		//return null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			System.out.println("hie1");
			return null;
		}
		return null;
	}
	
	/**
	 * put entity
	 */
	/*public void putEntity(){
		System.out.println("Begin /PUT request!");
		String putUrl = "http://localhost:8080/put/2";
		Customer puttCustomer = new Customer("Bush", 23);
		restTemplate.put(putUrl, puttCustomer);
	}*/
	
	/**
	 * delete entity
	 */
	/*public void deleteEntity(){
		System.out.println("Begin /DELETE request!");
		String deleteUl = "http://localhost:8080/delete/1";
		restTemplate.delete(deleteUl);
	}*/
}
