package main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Runner {
	private static Scanner input;
	
	public static void main(String[] args) throws IOException {
		int alertsCount = 0;
		input = new Scanner(System.in);
		Runner m = new Runner();
		boolean run = true;
		while (run) {
			System.out.println("Alerts Menu");
			System.out.println("1. Create alert");
			System.out.println("2. Delete alerts");
			System.out.println("3. Exit");
			
			int choice = input.nextInt();
			switch (choice) {
			case 1: m.createAlert(); alertsCount++; System.out.println("Created alert. Alert count: "+alertsCount); break;
			case 2: m.deleteAlerts(); alertsCount=0; System.out.println("Deleted alerts. Alerts count: "+alertsCount); break;
			case 3: run = false; break;
			}
		}
	}
	
	public void createAlert() throws IOException {
		String USER_AGENT = "Mozilla/5.0";

	    String POST_URL = "https://api.marketalertum.com/Alert";
	    String json = "{\r\n    \"alertType\": 6,\r\n    \"heading\": \"Jumper Windows 11 Laptop\",\r\n    \"description\": \"Jumper Windows 11 Laptop 1080P Display,12GB RAM 256GB SSD\",\r\n    \"url\": \"https://www.amazon.co.uk/Windows-Display-Ultrabook-Processor-Bluetooth\",\r\n    \"imageUrl\": \"https://m.media-amazon.com/images/I/712Xf2LtbJL._AC_SX679_.jpg\",\r\n    \"postedBy\": \"d59bc878-de0f-459a-89dd-a5a4832067f6\",\r\n    \"priceInCents\": \"24999\"\r\n}";
	    String POST_PARAMS = json;
        URL obj = new URL(POST_URL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) obj.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);
        httpURLConnection.setRequestProperty("Content-Type", "application/json");

        // For POST only - START
        httpURLConnection.setDoOutput(true);
        OutputStream os = httpURLConnection.getOutputStream();
        os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();
        // For POST only - END

        int responseCode = httpURLConnection.getResponseCode();
        // System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_CREATED) { // success 201
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in .readLine()) != null) {
                response.append(inputLine);
            } in .close();

            // print result
            // System.out.println(response.toString());
        } else {
            System.out.println("POST request not worked");
        }
    }
	
	public void deleteAlerts() throws IOException {
		URL url = new URL("https://api.marketalertum.com/Alert?userId=d59bc878-de0f-459a-89dd-a5a4832067f6");
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setDoOutput(true);
		httpCon.setRequestProperty(
		    "Content-Type", "application/x-www-form-urlencoded" );
		httpCon.setRequestMethod("DELETE");
		httpCon.connect();
		int responseCode = httpCon.getResponseCode();
        // System.out.println("DELETE Response Code :: " + responseCode);
	}
}
