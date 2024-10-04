//http://api.openweathermap.org/data/2.5/weather?q=Austin&APPID=26aa1d90a24c98fad4beaac70ddbf274


package com.example;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) 
    {
        
        try {
            


        System.out.println("Enter the city/Zip you want weather for: ");
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();

        /* 
        // Check if the input is numeric
        if (userInput.matches("\\d+")) {
            // Input is a number, store it as an integer (Zip code)
            int zip = Integer.parseInt(userInput);
            System.out.println("You entered a Zip code: " + zip);
        } else {
            // Input is not a number, store it as a string (City)
            String city = userInput;
            System.out.println("You entered a city: " + city);
        }
        */

        input.close();


        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + userInput + "&APPID=26aa1d90a24c98fad4beaac70ddbf274";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        int respondeCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL: " + url);
        System.out.println("Response Code : " + respondeCode);
        BufferedReader in = new BufferedReader (
            new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //System.out.println(response.toString());


        JSONObject myresponse = new JSONObject(response.toString());

        //System.out.println(myresponse);

        JSONObject main = myresponse.getJSONObject("main");
       //System.out.println("base: " + myresponse.getString("main"));
        
        double temp = main.getDouble("temp");

        JSONArray weatherArray = myresponse.getJSONArray("weather");
        JSONObject weather = weatherArray.getJSONObject(0);

        String description = weather.getString("description");


        double tempFahrenheit = (temp - 273.15) * 9/5 + 32;

        System.out.println("\n----------------------------------------------------\n\n\n");



        System.out.print("The temperature of " +  userInput + " is: ");
        System.out.printf("%.2f Degrees", tempFahrenheit);

        System.out.println("\n");

        //System.out.println("Description: " + description);
        System.out.println("Weather Description: " + description + "\n\n");


        
        
    } catch (Exception e) {
        // TODO: handle exception
        System.out.println("Error");
    }


        
    }
}