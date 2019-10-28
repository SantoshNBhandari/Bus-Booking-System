package com.dev.bbs.controller;
/**ADMIN CONTROLLER CLASS**/
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.bbs.beans.Admin;
import com.dev.bbs.beans.Available;
import com.dev.bbs.beans.Bus;
import com.dev.bbs.beans.Feedback;
import com.dev.bbs.sevice.ServiceAdmin;

/**TO make class as controller**/
@RestController
/**MAPPING (giving controller path)**/
@RequestMapping(path="/admin")
/**TO Connect with front end if it is runing in any port**/
@CrossOrigin(origins="*",allowedHeaders="*")
public class AdminController {
	/**Autowirng of the service class**/
	@Autowired
	ServiceAdmin services;
	
	/**Post mapping to post the data by giving path same as front end **/
	@PostMapping(path = "/rest/bus/create",consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)

	/**Getting(Consuming ) the whole Object**/
	public Bus createBus(@RequestBody Bus bus) {

		/**Calling theservice layer create bus method**/
		Bus check = services.createBus(bus);
		
		/**Retrning the whole object**/
		return check;
		
		
	}
	
	
	/**Delete mapping is use to invoke delete method **/
	/** providing the path**/
	@DeleteMapping(path = "/rest/bus/delete/{busId}/{password}", 
			produces = MediaType.APPLICATION_JSON_VALUE)

	/**Using path variables getting the path variables from front end**/
	public Bus deleteBus(@PathVariable("busId") int id,@PathVariable("password") String  password) {
		/**invoking the search bus method by passing id**/ 
		Bus bus = services.searchBus(id);
		/**After getting the bus passing the bus id and password**/
		 Boolean check = services.deletebus(id, password);
		 if(check) {
		/**If it returns true then it will return bus**/
				return bus;
			}
			return null;
	}
	
	
	/**using put mapping and passing the path variable and url**/
	@PutMapping(path = "/rest/bus/update",consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	/**using request body  consuming the whole object**/
	public Bus updateBus(@RequestBody Bus bus) {
		/**calling the update bus method which is present in service**/
		Boolean check = services.updateBus(bus);
		if(check) {
			/**If true returning the bus**/
			return bus;
		}
		return null;
	}
	
	
	/**Login using postmapping**/
	@PostMapping(path = "/rest/admin/login",consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Admin loginAdmin(@RequestBody Admin admin) {
		Boolean check = services.adminLogin(admin.getAdminId(),admin.getPassword());
		System.out.println(check);
		if(check) {
			return admin;
		}
		return null;
	}
	

	
//	@GetMapping(path = "/rest/admin/show-feedback", 
//			produces = MediaType.APPLICATION_JSON_VALUE)
//	public List<Feedback> showFeedback() {
//		List<Feedback> list = services.showFeedback();
//		if(list!=null) {
//			return list;
//		}
//		return null;
//	}

	
	
}