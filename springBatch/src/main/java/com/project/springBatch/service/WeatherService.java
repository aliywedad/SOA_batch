package com.project.springBatch.service;

import com.project.springBatch.model.WeatherData;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {

    private static final String API_HOST = "meteostat.p.rapidapi.com";
    private static final String API_KEY = "190e7c29d8mshdf8ece2d86ac905p 1525adjsn8e6c3d92ee2b"; // Replace with your actual RapidAPI key
    private static final String BASE_URL = "https://meteostat.p.rapidapi.com/point/hourly?lat=%f&lon=%f&alt=0&start=2024-01-01&end=2024-01-01&units=metric";

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<WeatherData> fetchWeatherData(List<CityCoordinates> cities) {
        List<WeatherData> weatherDataList = new ArrayList<>();

        for (CityCoordinates city : cities) {
            try {
                String url = String.format(BASE_URL, city.getLatitude(), city.getLongitude());
                HttpHeaders headers = new HttpHeaders();
                headers.set("X-RapidAPI-Host", API_HOST);
                headers.set("X-RapidAPI-Key", API_KEY);

                HttpEntity<String> entity = new HttpEntity<>(headers);

                ResponseEntity<JsonNode> response = restTemplate.exchange(url, HttpMethod.GET, entity, JsonNode.class);

                if (response.getBody() != null && response.getBody().has("data")) {
                    JsonNode weatherDataNode = response.getBody().get("data");
                    if (weatherDataNode.isArray() && weatherDataNode.size() > 0) {
                        JsonNode firstRecord = weatherDataNode.get(0); // Using the first record for simplicity
                        weatherDataList.add(new WeatherData(
                                city.getName(),
                                firstRecord.get("temperature").asDouble(),
                                firstRecord.get("weather").asText()
                        ));
                    }
                }
            } catch (Exception e) {
                System.err.println("Failed to fetch weather data for city: " + city.getName());
                e.printStackTrace();
            }
        }

        return weatherDataList;
    }
}

class CityCoordinates {
    private String name;
    private double latitude;
    private double longitude;

    public CityCoordinates(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
