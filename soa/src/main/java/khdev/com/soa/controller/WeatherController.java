package khdev.com.soa.controller;

import java.util.List;

import khdev.com.soa.repositories.WeatherRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import khdev.com.soa.models.WeatherData;
import khdev.com.soa.services.WeatherService;

@RestController
public class WeatherController {

    private final WeatherService weatherService;
    private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Autowired
    private WeatherRepo weatherRepo;





    @GetMapping("/weather")
    public ResponseEntity<List<WeatherData>> getWeather(
            @RequestParam double latitude, 
            @RequestParam double longitude) {
        
        try {
            List<WeatherData> weatherData = weatherService.fetchWeatherData(latitude, longitude);

            if (weatherData == null || weatherData.isEmpty()) {
                return ResponseEntity.noContent().build();
            }


                weatherService.saveWeatherData(weatherData);


            return ResponseEntity.ok(weatherData);
        } catch (Exception e) {
            logger.error("Error fetching weather data", e);
            return ResponseEntity.status(500).body(null);
        }
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
