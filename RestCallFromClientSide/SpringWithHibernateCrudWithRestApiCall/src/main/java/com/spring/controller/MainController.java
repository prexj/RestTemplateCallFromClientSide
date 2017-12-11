package com.spring.controller;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.annotation.LazySingletonAspectInstanceFactoryDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.spring.dao.EmployeeDao;
import com.spring.model.Employee;
import com.spring.model.User;
import com.spring.restapi.RestfulClient;

/**
 * Handles requests for the application home page.
 */
@Controller
/*@Configuration
@ComponentScan(basePackages="com.spring")
@PropertySource(value ={"classpath:database.properties"})*/
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	EmployeeDao empDao;
	/*@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		return "emp1";
	}*/
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		return "emp";
	}
	@RequestMapping(value = "insertInfo", method = RequestMethod.POST,produces="application/json")
	@ResponseBody
	public int insertData(@RequestParam("fname")String firstname,
			@RequestParam("lname")String lastname,
			@RequestParam("address")String address,
			@RequestParam("gen") String gen, Model model) throws  RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
		logger.info("Welcome insertData()");
		logger.info("fname"+firstname);
		logger.info("fname"+lastname);
		logger.info("fname"+address);
		logger.info("gen"+gen);
		
		Employee empBean= new Employee();
		empBean.setFirstname(firstname);
		empBean.setLastname(lastname);
		empBean.setAddress(address);
		empBean.setDflag(1);
		empBean.setGender(gen);
		
		int i = empDao.addEmployee(empBean);
		logger.info("Welcome insertData()"+empBean);
		if(i>0){
			i = 1;
		}else{
			i = 0;
		}
		return i;
	}
	@RequestMapping(value = "showAll", method = RequestMethod.GET ,produces="application/json")
	@ResponseBody
	public List<LinkedHashMap<String, Object>> showAll(Locale locale, Model model) {
		logger.info("Welcome ShowData()");
		
		List<LinkedHashMap<String, Object>> userlist = RestfulClient.getEntity2();
		
		return userlist;
	}
	
	@RequestMapping(value = "showAllP", method = RequestMethod.GET ,produces="application/json")
	@ResponseBody
	public List<LinkedHashMap<String, Object>> showAllP(Locale locale, Model model) {
		logger.info("Welcome ShowData()");
		List<LinkedHashMap<String, Object>> userlist = RestfulClient.getEntity3();
		
		return userlist;
	}
	public static JsonObject parseStringToJson(String json) {
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonElement jelem = gson.fromJson(json, JsonElement.class);
			JsonObject jobj = jelem.getAsJsonObject();
			return jobj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	public static JsonArray parseJsonForArray(JsonObject obj,String attr ) {
		try {
			JsonArray ja = new JsonArray();
			ja = obj.get(attr).getAsJsonArray();
			return ja;
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	
	@RequestMapping(value = "searchByid", method = RequestMethod.GET ,produces="application/json")
	@ResponseBody
	public User searchByid(@RequestParam("id")Integer searchId, Model model) throws ClassNotFoundException {
		logger.info("Welcome searchName()");
		logger.info("searchId :: "+searchId);
		User outputCallApi = RestfulClient.getUser(searchId);
		System.out.println("outputCallApi id"+outputCallApi.getId());
		return outputCallApi;
	}
	
	@RequestMapping(value = "searchName", method = RequestMethod.GET ,produces="application/json")
	@ResponseBody
	public List<Employee> searchName(@RequestParam("firstname")String search, Model model) throws ClassNotFoundException {
		logger.info("Welcome searchName()");
		logger.info("search :: "+search);
		//model.addAttribute("show", empDao.listemployee());
		ArrayList<Employee> emplist = empDao.listemployee();
		Predicate<Employee> p1 = e -> e.getFirstname().equalsIgnoreCase(search)  && e.getDflag() == 1;
		Employee newemp = new Employee(); 
		Class cls = newemp.getClass();
		 //Class cls = Class.forName("p1");
	        // Getting declared methods inside the Calculate class  
	        Method[] method = cls.getDeclaredMethods(); // It returns array of methods  
	        // Iterating method array  
	        for (Method method2 : method) {  
	            System.out.println(method2.getName().toString());    // getting name of method  
	            // Getting parameters of each method  
	            Parameter parameter[] = method2.getParameters(); // It returns array of parameters  
	            // Iterating parameter array  
	            for (Parameter parameter2 : parameter) {  
	                System.out.println(""+parameter2.getParameterizedType().toString()); // returns type of parameter  
	                System.out.println(""+parameter2.getName().toString()); // returns parameter name  
	            }   
	        }  
		boolean b1 = emplist.stream().anyMatch(p1);
	     System.out.println("b1 >>>>"+b1);
		//List<String>  val1 =emplist.stream().filter(e->e.getFirstname() == search).map(e->e.getFirstname()).collect(Collectors.toList());
		List<Employee>  val1 =(List<Employee>) emplist.stream().parallel().filter(e->e.getFirstname().equalsIgnoreCase(search) && e.getDflag() ==1).sequential().collect(Collectors.toList());
       // Long  val2 =plist.stream().filter(p->p.name == "pratik").map(p->p.name).collect(Collectors.counting());
        System.out.println("val1 ::"+val1);
       //System.out.println("val2 ::"+val2);
		return val1;
	}
	/*@RequestMapping(value = "findEmp", method = RequestMethod.GET)
	public String findEmp(@RequestParam("skill")String[] skill, Model model) {
		for (int i = 0; i < skill.length; i++) {
			logger.info("skill is"+skill[i]);
		}
		
		model.addAttribute("editemp", empDao.findBySkill(skill));
		return "employeelist";
	}*/
	@RequestMapping(value = "editData", method = RequestMethod.GET ,produces="application/json")
	@ResponseBody
	public Employee editEmployee(@RequestParam("id")int id, Model model) {
		logger.info("id is"+id);
		//model.addAttribute("editemp", empDao.getById(id));
		Employee em = empDao.getById(id);
		return em;
	}
	@RequestMapping(value = "deleteData", method = RequestMethod.POST ,produces="application/json")
	@ResponseBody
	public String deleteEmployee(@RequestParam("id")int id, Model model) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
		logger.info("id is"+id);
		
		int i  =empDao.deleteEmp(id);
		String rtn = "";
		if(i>0){
			rtn = "1";
		}else{
			rtn ="0";
		}
		return rtn;
	}
	@RequestMapping(value = "updateInfo", method = RequestMethod.POST ,produces="application/json")
	@ResponseBody
	public int updateEmployee(@RequestParam("id")int id,
			@RequestParam("fname")String firstname,
			@RequestParam("lname")String lastname,
			@RequestParam("gen")String gen,
			@RequestParam("address")String address, Model model) throws  RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
		logger.info("id is"+id);
		logger.info("fname"+firstname);
		logger.info("lastname"+lastname);
		logger.info("gen"+gen);
		logger.info("address"+address);
		Employee empBean = new Employee();
		empBean.setId(id);
		empBean.setFirstname(firstname);
		empBean.setLastname(lastname);
		empBean.setAddress(address);
		empBean.setGender(gen);
		empBean.setDflag(1);
		empBean = empDao.updateEmployee(empBean);
		int i;
		 if(empBean.getId()>0){
			 return i = 1;
		 }else{
			 return i = 0;
		 }
		 
		//return i;
	}
	/*@RequestMapping(value = "serchAJAX", method = RequestMethod.GET , produces="application/json")
	@ResponseBody
	public ArrayList<Employee> serchAJAX( @RequestParam("skill") String[] skill)  {
		logger.info("serchajax()");
		for (int i = 0; i < skill.length; i++) {
			logger.info("skill is"+skill[i]);
		}
		
		
		Employee emp = null;
        //ArrayList<Employee> empFname = new ArrayList<Employee>();
        String skl =" "; 
        for (int i = 0; i < skill.length; i++) {
			skl = skl+skill[i]+"";
		}
        emp.setSkill(skl);
        ArrayList<Employee> empfname = empDao.findBySkill(emp);
        
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			Statement st =con.createStatement();
			ResultSet rs = st.executeQuery("select * from emp where skill like '%"+skill+"%'; ");
			while( rs.next() ){
				ben = new EmployeeBen();
				ben.setFname(rs.getString("fname"));
				System.out.println(ben.getFname());
				empFname.add(ben);
			}
			 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return empfname;
	}*/
	 @Bean
	    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
	        return new PropertySourcesPlaceholderConfigurer();
	    }
	
}
