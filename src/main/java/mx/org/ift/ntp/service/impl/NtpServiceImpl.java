package mx.org.ift.ntp.service.impl;

import java.sql.Timestamp;

import mx.org.ift.ntp.service.NtpService;
import mx.org.ift.ntp.util.TimeConverters;
import mx.org.ift.ntp.util.WebBot;

import static mx.org.ift.ntp.constantes.Constante.URL_TIME;


public class NtpServiceImpl implements NtpService{
	
	public Timestamp getTime(String url){
		String time = new WebBot().explorer(url);
		return TimeConverters.convertTime(time);
	}
	
	public static void main(String ...strings ){
		String url = URL_TIME;
		
		Timestamp s = new NtpServiceImpl().getTime(url);
		System.out.println(s);
	}

}
