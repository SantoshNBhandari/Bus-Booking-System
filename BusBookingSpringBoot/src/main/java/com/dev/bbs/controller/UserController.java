package com.dev.bbs.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.bbs.beans.Admin;
import com.dev.bbs.beans.Available;
import com.dev.bbs.beans.Booking;
import com.dev.bbs.beans.Bus;
import com.dev.bbs.beans.Feedback;
import com.dev.bbs.beans.Ticket;
import com.dev.bbs.beans.User;
import com.dev.bbs.sevice.ServiceUser;
/** USer Controler **/
@RestController
/**Path of the user controller**/
@RequestMapping(path="/user")
/**for connection of backend with servers **/
@CrossOrigin(origins="*",allowedHeaders="*")
public class UserController {
	/** Auto wiring services**/
	@Autowired
	ServiceUser services;
	
	
	/**Post Mapping and adding the paths and consuming **/
	@PostMapping(path = "/create",consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	/**Request body to get the whole object**/
	public User createUser(@RequestBody User user)
	{
		/**Calling the creataeuser of autowired service class **/
		 return user = services.createUser(user);
	}
	
	
	/**Post Mapping and adding the paths and consuming **/
	@PostMapping(value = "/updateuser",consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	/**Request body to get the whole object**/
	public User updateUser(@RequestBody User user)
	{
		/**calling the update service method**/
		Boolean state = services.updateUser(user, user.getPassword());
		if(state)
		{
			/** getting the updated user **/
		 user = services.searchUser(user.getUserId());
          /**Returning the updated user**/
		 return user;
		}
		return null;
		
	}
	
	/**Deleteing the user by taking the userid from the path and password**/
	@DeleteMapping(path = "/delete/{userId}/{password}",produces = MediaType.APPLICATION_JSON_VALUE)
	/** using path variable we are  getting the data from the path**/
	public User deleteUser(@PathVariable("userId") int userId,@PathVariable("password") String password) {
		/** Seraching the userid and deleting the  that user**/
		User user = services.searchUser(userId);
		Boolean state = services.deleteUser(userId, password);
		return user;
	}
	
	@PostMapping(path = "/login",consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public User loginUser(@RequestBody User user) {
		Boolean check = services.loginUser(user.getUserId(), user.getPassword());
		System.out.println(check);
		if(check) {
			return user;
		}
		return null;
	}


	@GetMapping(path ="/search/bus/{source}/{destination}/{date}",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Bus> searchBus(@PathVariable("source") String source,@PathVariable("destination") String destination,@PathVariable("date") Date date)
	{
		List<Bus> list = services.searchBus(source, destination, date);
		if(list!=null) {
			return list;
		}
		return null;
	}
	
	
	@PostMapping(path = "/book/ticket", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Booking bookTicket(@RequestBody Ticket ticket) {
		Booking check = services.bookTicket(ticket);
		if(check!=null) {
			return check;
		}
		return null;
	}
	
	
	@PostMapping(path = "/give-feedback", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Feedback giveFeedback(@RequestBody Feedback feedback) {
		Boolean list = services.giveFeedBack(feedback);
		if(list!=null) {
			return feedback;
		}
		return null;
	}
//	
	
	@DeleteMapping(path = "/cancel/ticket/{id}", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean cancelTicket(@PathVariable ("id") int r) {
		
		Boolean ticket = services.cancelTicket(r);
		if(ticket)
		{
			System.out.println("Ticket Cancelled Sucessfully");
			return true;
		}
		return false;
		
	}
	
	
//	@GetMapping(path = "/get/ticket/{booking_id}",consumes = MediaType.APPLICATION_JSON_VALUE,
//			produces = MediaType.APPLICATION_JSON_VALUE)
//	public Booking getTicket(@PathVariable("booking_id") int booking_id)
//	{
//		Booking check = services.getTicket(booking_id);
//		if(check!=null) {
//			return check;
//		}
//		return null;
//	}
	
	
	
	@GetMapping(path = "/printticket/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Booking printTicket(@PathVariable("id") int userId)
	{
		Booking book = services.printTicket(userId);
		return book;
	}
	
	
}
