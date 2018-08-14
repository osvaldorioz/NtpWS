package mx.org.ift.ntp.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class WebBot {
	public static Long timeElapsed = 0l;
	private final String USER_AGENT = "Mozilla/5.0";
	
	public String explorer(String url){
		StringBuffer result = new StringBuffer();
		String d = "";
		try{
			HttpClient client = HttpClientBuilder.create().build();
			
			
			HttpGet request = new HttpGet(url);
	
			request.setHeader("User-Agent", USER_AGENT);
			request.setHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			request.setHeader("Accept-Language", "en-US,en;q=0.5");
	
			HttpResponse response = client.execute(request);
			int responseCode = response.getStatusLine().getStatusCode();
	
			BufferedReader rd = new BufferedReader(
		                new InputStreamReader(response.getEntity().getContent()));
	
			String line = "";
			while ((line = rd.readLine()) != null) {
				if(line.contains("id=t1c")){
					result.append(line);
					break;
				}
			}
			int a = result.toString().indexOf("<span id=t1>");
			int b = result.toString().indexOf("</span>");
			d = result.toString().substring(a+12,b);
			
			System.out.println(">>"+d);
		}catch(ClientProtocolException err){
			err.printStackTrace();
		}catch(IOException err){
			err.printStackTrace();
		}
		
		return d;
	}
}
