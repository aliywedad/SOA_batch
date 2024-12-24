package khdev.com.soa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import khdev.com.soa.Entities.WeatherData;
import khdev.com.soa.services.WeatherService;
@CrossOrigin
@RestController
public class WeatherController {

    private final WeatherService weatherService;
    private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }









    @GetMapping("/weather")
    public ResponseEntity<List<WeatherData>> getWeather(
            @RequestParam double latitude, 
            @RequestParam double longitude) {

        try {
            List<WeatherData> weatherData = weatherService.fetchWeatherData(latitude, longitude);

            if (weatherData == null || weatherData.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(weatherData);
        } catch (Exception e) {
            logger.error("Error fetching weather data", e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/weatherTest")

    public ResponseEntity<Map<String, Object>> getWeatherTest(@RequestParam double latitude,
                                                          @RequestParam double longitude) {
        System.out.println("===================== latitude : " + latitude);
        System.out.println("===================== longitude : " + longitude);

        // Creating the weather data manually as a Map
        Map<String, Object> weatherData = new HashMap<>();

        weatherData.put("id", 1);
        weatherData.put("datetime", "2024-12-24T12:00:00");
        weatherData.put("temp", 25.3);
        weatherData.put("app_temp", 23.5);
        weatherData.put("snow", 0.0);
        weatherData.put("solar_rad", 450.5);
        weatherData.put("wind_gust_spd", 15.3);
        weatherData.put("pop", 0.0);
        weatherData.put("ozone", 300.0);
        weatherData.put("clouds_hi", 40);
        weatherData.put("clouds_low", 60);
        weatherData.put("timestamp_utc", "2024-12-24T10:00:00Z");
        weatherData.put("wind_cdir", "NE");
        weatherData.put("rh", 70);
        weatherData.put("pod", "d");
        weatherData.put("pres", 1013);
        weatherData.put("snow_depth", 0.0);
        weatherData.put("clouds", 50);
        weatherData.put("vis", 10.0);
        weatherData.put("wind_spd", 5.0);
        weatherData.put("wind_cdir_full", "North-East");
        weatherData.put("slp", 1012);
        weatherData.put("timestamp_local", "2024-12-24T12:00:00");
        weatherData.put("ts", "2024-12-24T12:00:00");
        weatherData.put("dni", 800.5);
        weatherData.put("dewpt", 18.2);
        weatherData.put("uv", 6.0);
        weatherData.put("clouds_mid", 50);
        weatherData.put("wind_dir", 45);
        weatherData.put("ghi", 550.0);
        weatherData.put("dhi", 200.5);
        weatherData.put("precip", 0.0);

        // Weather details as an embedded map
        Map<String, Object> weather = new HashMap<>();
        weather.put("id", 2);
        weather.put("description", "Clear sky");
        weather.put("icon", "01d");

        weatherData.put("weather", weather);

        // Return the data as JSON
        return ResponseEntity.ok(weatherData);
    }

}


// @RestController
// public class WeatherController {

//     private final JobLauncher jobLauncher;
//     private final Job weatherJob;

//     @Autowired
//     public WeatherController(JobLauncher jobLauncher, Job weatherJob) {
//         this.jobLauncher = jobLauncher;
//         this.weatherJob = weatherJob;
//     }

//     @GetMapping("/run-weather-job")
//     public String runWeatherJob(@RequestParam("time") Long time) {
//         try {
//             JobParameters jobParameters = new JobParametersBuilder()
//                 .addLong("time", time) // Ajout d'un paramètre pour éviter l'exécution multiple du même job
//                 .toJobParameters();
//             jobLauncher.run(weatherJob, jobParameters);
//             return "Job executed successfully";
//         } catch (Exception e) {
//             e.printStackTrace();
//             return "Job execution failed: " + e.getMessage();
//         }
//     }
// }
