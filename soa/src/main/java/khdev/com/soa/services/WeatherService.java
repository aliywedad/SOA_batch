package khdev.com.soa.services;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import khdev.com.soa.ApiResponse;
import khdev.com.soa.Entities.WeatherData;

@Service
public class WeatherService {



//client.prepare("GET", "https://weatherbit-v1-mashape.p.rapidapi.com/forecast/3hourly?lat=35.5&lon=-78.5&units=imperial&lang=en")
//        .setHeader("x-rapidapi-key", "ce594f7257msh48b8eac801c7db4p1e0bb1jsn0401f231056a")
//	.setHeader("x-rapidapi-host", "weatherbit-v1-mashape.p.rapidapi.com")



    private static final String API_URL = "https://weatherbit-v1-mashape.p.rapidapi.com/forecast/3hourly";
    private static final String API_KEY = "ce594f7257msh48b8eac801c7db4p1e0bb1jsn0401f231056a";
    private static final String API_HOST = "weatherbit-v1-mashape.p.rapidapi.com";

    public List<WeatherData> fetchWeatherData(double latitude, double longitude) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + "?lat=" + latitude + "&lon=" + longitude + "&units=imperial&lang=en"))
                .header("x-rapidapi-key", API_KEY)
                .header("x-rapidapi-host", API_HOST)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        //  System.out.println("Réponse brute de l'API : ");
        // System.out.println(response.body());  
        ObjectMapper objectMapper = new ObjectMapper();
        ApiResponse apiResponse = objectMapper.readValue(response.body(), ApiResponse.class);  

        return apiResponse.getData();
    }
    
}


