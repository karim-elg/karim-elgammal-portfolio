package ca.mcgill.ecse.flexibook.application;

import java.time.LocalDateTime;
import java.sql.Date;
import java.sql.Time;

public class SystemTime {
	private static boolean isTest;
	static Date date;
	static Time time;
	
	public SystemTime() {
		
		long millis=System.currentTimeMillis();  
	    date = new Date(millis);  
	    time = new Time (millis);
		isTest = false;
		
	}
	
	public static boolean setTest(boolean newIsTest) {
		boolean didChange = false;
		isTest = newIsTest;
		didChange = true;
		return didChange;
	}
	
	public static Date getDate() {
		if(!isTest) {
			long millis=System.currentTimeMillis();  
		    date = new java.sql.Date(millis);
		    time  = new Time (millis);
		}
		
		return date;
	}
	
	public static Time getTime() {
		if(!isTest) {
			long millis=System.currentTimeMillis();  
		    date = new java.sql.Date(millis);
		    time  = new Time (millis);
		}
		
		return time;
	}
	
	public static void setDate(Date aDate) {
		if (isTest) {
			date = aDate;
		}
	}
	
	public static void setTime(Time t) {
		if (isTest) {
			time = t;
		}
	}

}

