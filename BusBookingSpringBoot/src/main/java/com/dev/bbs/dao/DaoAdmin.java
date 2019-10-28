package com.dev.bbs.dao;

import java.util.List;

import com.dev.bbs.beans.Bus;
import com.dev.bbs.beans.Feedback;
/**Interface for DAo class methods**/
public interface DaoAdmin {
	public Bus createBus(Bus bus);
	public Boolean updateBus(Bus bus);
	public Bus searchBus(int busId);
	public Boolean deletebus(int busId,String password);
	public List<Bus> busBetween(String source,String destination);
	public List<Feedback> showFeedback();
	
	public Boolean addAvailability(com.dev.bbs.beans.Available available);
	public Boolean adminLogin(int adminId, String password);
}
