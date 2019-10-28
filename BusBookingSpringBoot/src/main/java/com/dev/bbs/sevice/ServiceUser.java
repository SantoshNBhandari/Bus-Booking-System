package com.dev.bbs.sevice;


import java.util.Date;
import java.util.List;

import com.dev.bbs.beans.Booking;
import com.dev.bbs.beans.Bus;
import com.dev.bbs.beans.Feedback;
import com.dev.bbs.beans.Ticket;
import com.dev.bbs.beans.User;
/**Interface for Service class methods**/
public interface ServiceUser {
	public User createUser(User user);
	public Boolean updateUser(User user,String password);
	public Boolean deleteUser(int user_id,String password);
	public Boolean loginUser(int user_id,String password);
	public User searchUser(int user_id);
	public List<Bus> searchBus(String source,String destination , Date date);
	
	
	
	public Booking bookTicket(Ticket ticket);
	public Boolean cancelTicket(int booking_id);
	public Booking getTicket(int booking_id);
	public Integer checkAvailability(int bus_id,Date date);
	public Boolean giveFeedBack(Feedback feedback);
	public List<Booking> getAllTickets(int userId);
	
	public String checkUserIdAndBookinIdAndBusId(String number);
	public String checkContact (String contact);
	public String checkEmail (String email);
	public Booking printTicket (int booking_id);

}
