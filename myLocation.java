package com.h3ll3m4.myLocation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class myLocation {

    static private String getIPLocation(String url) throws Exception {
        String lat, lon;
    	URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        if (con.getResponseCode() != 200) {
            return null;
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        inputLine=in.readLine();
        if (inputLine.equals("success")){
        	inputLine=in.readLine();
        	response.append("City : "+ inputLine + System.getProperty("line.separator"));
        	lat=in.readLine();
        	response.append("Latitude : "+ lat + System.getProperty("line.separator"));
        	lon=in.readLine();
        	response.append("Longitude : "+ lon + System.getProperty("line.separator"));
            String map = "https://www.google.com/maps/?q="+lat+","+lon;
            System.out.println("My approximate location on a map: "+map);
        }
        in.close();
        return response.toString();
    }
    
	
	public static String getIP() throws Exception {
        URL service = new URL("http://checkip.amazonaws.com");
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(
            		service.openStream()));
            String ip = in.readLine();
            return ip;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
	
	public static void main(String[] args) {
		// Get our IP address
		String ip;
		try {
			ip = getIP();
	        System.out.println("My public IP Address: " + ip);

	        // Build the url
			String url = "http://ip-api.com/line/"+ip+"?fields=status,city,lat,lon";
			System.out.println("Request sent: " + url);
			
			// Connect to location API			
			try {
				String res = getIPLocation(url);
				System.out.println(res);
				
			}catch(Exception e) {
				System.out.println("Error while getting the answer from the API");
				e.printStackTrace();
			}
		} catch (Exception e1) {
			System.out.println("Error while getting IP address of the host");
			e1.printStackTrace();
		}


	}

}
