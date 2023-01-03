package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.Scanner;

import org.json.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class Runner {
	private static Scanner input;
	
	public static void main(String[] args) throws Exception {
		Runner m = new Runner();
		boolean run = true;
		boolean loggedIn = false;
		boolean userViewedAlerts = false;
		
		while(run) {
			System.out.println("Website Menu");
			System.out.println("1. Log in");
			System.out.println("2. Bad log in");
			System.out.println("3. Log out");
			System.out.println("4. View alerts");
			System.out.println("5. Exit");
			
			input = new Scanner(System.in);
			int choice = input.nextInt();
			
			switch (choice) {
			case 1: m.getEventLog(); loggedIn=true; userViewedAlerts=true; System.out.println("Login status: "+loggedIn);System.out.println("User viewed alerts: "+userViewedAlerts); break;
			case 2: m.getLoginState(); System.out.println("Login status: "+loggedIn); break;
			case 3: m.getEventLog(); loggedIn=false; userViewedAlerts=false; System.out.println("Login status: "+loggedIn); System.out.println("User viewed alerts: "+userViewedAlerts); break;
			case 4: m.getEventLog(); userViewedAlerts = true; System.out.println("User viewed alerts: "+userViewedAlerts); break;
			case 5: run = false; break;
			}
		}
	}
	
	public void getLoginState() throws Exception {
		String USER_AGENT = "Mozilla/5.0";
		String GET_URL = "https://api.marketalertum.com/EventsLog/d59bc878-de0f-459a-89dd-a5a4832067f6/login-status";
		URL obj = new URL(GET_URL);
		HttpURLConnection httpURLConnection = (HttpURLConnection) obj.openConnection();
		httpURLConnection.setRequestMethod("GET");
		httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = httpURLConnection.getResponseCode();
		
		String loginStatus = "";
		
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
		    BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
		    String inputLine;
		    StringBuffer response = new StringBuffer();
		
		    while ((inputLine = in .readLine()) != null) {
		        response.append(inputLine);
		    } in .close();
		
		    // print result
		    if (!response.toString().equals("[]")) {
		    	// Parse eventLog JSON into eventLogType number value pertaining to event log
		    	String jsonStr = response.toString();
		    	
		    	String[] jsonData = jsonStr.split(",");
		    	String loginStatusStr = jsonData[1];

		    	String[] loginStatusStrArr = loginStatusStr.split(":");
		    	loginStatus = loginStatusStrArr[1];
		    	if (loginStatus.equals("false}")) {
		    		System.out.println(badLogin());
		    	}
		    }
		}
		else {
		    System.out.println("GET request not worked");
		}
	}
	
	public void getEventLog() throws Exception {
		LinkedList<Integer> events = new LinkedList<Integer>();
		String USER_AGENT = "Mozilla/5.0";
		String GET_URL = "https://api.marketalertum.com/EventsLog/d59bc878-de0f-459a-89dd-a5a4832067f6";
		URL obj = new URL(GET_URL);
		HttpURLConnection httpURLConnection = (HttpURLConnection) obj.openConnection();
		httpURLConnection.setRequestMethod("GET");
		httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = httpURLConnection.getResponseCode();
		// System.out.println("GET Response Code :: " + responseCode);
		
		String eventLogType = "";
		
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
		    BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
		    String inputLine;
		    StringBuffer response = new StringBuffer();
		
		    while ((inputLine = in .readLine()) != null) {
		        response.append(inputLine);
		    } in .close();
		
		    // print result
		    if (!response.toString().equals("[]")) {
		    	// Parse json
		    	Object jsonObj = new JSONParser().parse(response.toString());
		    	JSONArray list = (JSONArray) jsonObj;
		    	
		    	for (int i = 0; i < list.size(); i++) {
		    		JSONObject jo = (JSONObject) list.get(i);
		    		long eventType = (Long) jo.get("eventLogType");
		    		events.add((int) eventType);
		    	}
		    	
		    	for (int i = 0; i < events.size(); i++) {
		    		switch (events.get(i)) {
		    		case 5: System.out.println(goodLogin()); break;
		    		case 6: System.out.println(logOut()); break;
		    		case 7: System.out.println(viewMyAlerts()); break;
		    		}
		    	}
		    }
		}
		else {
		    System.out.println("GET request not worked");
		}
	}
	
	public static String goodLogin() { 
		return "UserValidLogin";
	}
	
	public static String badLogin() { 
		return "UserInvalidLogin";
	}
	
	public static String logOut() { 
		return "UserLoggedOut";
	}
	
	public static String viewMyAlerts() {
		return "UserViewedAlerts";
	}

}
