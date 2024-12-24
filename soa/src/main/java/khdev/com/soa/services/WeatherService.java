package khdev.com.soa.services;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import khdev.com.soa.Entites.WeatherEntity;
import khdev.com.soa.repositories.WeatherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import khdev.com.soa.ApiResponse;
import khdev.com.soa.Entities.WeatherData;

@Service
public class WeatherService {

    private static final String API_URL = "https://weatherbit-v1-mashape.p.rapidapi.com/forecast/3hourly";
    private static final String API_KEY = "3efcf98d9cmsheb5ab8c04b2d6bbp162bbbjsne505242e9a45";
    private static final String API_HOST = "weatherbit-v1-mashape.p.rapidapi.com";

    public List<WeatherData> fetchWeatherData(double latitude, double longitude) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + "?lat=" + latitude + "&lon=" + longitude + "&units=imperial&lang=en"))
                .header("x-rapidapi-key", API_KEY)
                .header("x-rapidapi-host", API_HOST)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
         System.out.println("Réponse brute de l'API : ");
        System.out.println(response.body());  
        ObjectMapper objectMapper = new ObjectMapper();
        ApiResponse apiResponse = objectMapper.readValue(response.body(), ApiResponse.class);  

        return apiResponse.getData();
    }


    @Autowired
    private WeatherRepo weatherRepo; // Injecting the WeatherRepo

    public void saveWeatherData(List<WeatherData> weatherData) {
        // Loop through each weather data and save to the repository
        for (WeatherData weather : weatherData) {
            // Create a new WeatherEntity from WeatherData
            WeatherEntity weatherEntity = new WeatherEntity();
            weatherEntity.setDatetime(weather.getDatetime());
            weatherEntity.setTemp(weather.getTemp());
            weatherEntity.setAppTemp(weather.getAppTemp());
            weatherEntity.setSnow(weather.getSnow());
            weatherEntity.setSolarRad(weather.getSolarRad());
            weatherEntity.setWindGustSpd(weather.getWindGustSpd());
            weatherEntity.setPop(weather.getPop());
            weatherEntity.setOzone(weather.getOzone());
            weatherEntity.setCloudsHi(weather.getCloudsHi());
            weatherEntity.setCloudsLow(weather.getCloudsLow());
            weatherEntity.setTimestampUtc(weather.getTimestampUtc());
            weatherEntity.setWindCdir(weather.getWindCdir());
            weatherEntity.setRh(weather.getRh());
            weatherEntity.setPod(weather.getPod());
            weatherEntity.setPres(weather.getPres());
            weatherEntity.setSnowDepth(weather.getSnowDepth());
            weatherEntity.setClouds(weather.getClouds());
            weatherEntity.setVis(weather.getVis());
            weatherEntity.setWindSpd(weather.getWindSpd());
            weatherEntity.setWindCdirFull(weather.getWindCdirFull());
            weatherEntity.setSlp(weather.getSlp());
            weatherEntity.setTimestampLocal(weather.getTimestampLocal());
            weatherEntity.setTs(weather.getTs());
            weatherEntity.setDni(weather.getDni());
            weatherEntity.setDewpt(weather.getDewpt());
            weatherEntity.setUv(weather.getUv());
            weatherEntity.setCloudsMid(weather.getCloudsMid());
            weatherEntity.setWindDir(weather.getWindDir());
            weatherEntity.setGhi(weather.getGhi());
            weatherEntity.setDhi(weather.getDhi());
            weatherEntity.setPrecip(weather.getPrecip());

            // Save the WeatherEntity into the database
            weatherRepo.save(weatherEntity);
        }
    }


    
}


