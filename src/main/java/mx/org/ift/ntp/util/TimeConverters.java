package mx.org.ift.ntp.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeConverters {
	public static Timestamp convertTime(String time){
		Timestamp timestamp = null;
		try {
			//System.out.println("-->" + time);
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
		    Date parsedDate = dateFormat.parse(time);
		    timestamp = new java.sql.Timestamp(parsedDate.getTime());
		} catch(ParseException e) { 
			
		}
		return timestamp;
	}
	
	public static String addMilis(String time){
		
		Date dd = new Date();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("SSS");
		String milis = dateFormat.format(dd);
		
		time = time + "." + milis;
		return time;
	}
	
	
	public static Timestamp convertLong(Long time){
		Timestamp timestamp = null;
		try {
			timestamp = new java.sql.Timestamp(time);
		} catch(Exception e) { //this generic but you can control another types of exception
		    // look the origin of excption 
		}
		return timestamp;
	}
}
