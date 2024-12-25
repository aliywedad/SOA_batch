package khdev.com.soa.controller;

import java.io.PrintWriter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import khdev.com.soa.Entities.Weather;
import khdev.com.soa.Entities.WeatherData;
import khdev.com.soa.repositories.WeatherDataRepository;
import khdev.com.soa.repositories.WeatherRepository;
import khdev.com.soa.services.WeatherService;
import khdev.com.soa.utils.CsvExporter;

@RestController
public class WeatherController {

    private final WeatherService weatherService;
    private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);
    private final WeatherDataRepository weatherDataRepository;
    private final WeatherRepository weatherRepository;
    public WeatherController(WeatherService weatherService,WeatherDataRepository weatherDataRepository , WeatherRepository weatherRepository) {
        this.weatherService = weatherService;
        this.weatherDataRepository = weatherDataRepository;
        this.weatherRepository = weatherRepository;
    }
    @GetMapping("/weather")
    public ResponseEntity<List<WeatherData>> getWeather(@RequestParam double latitude, @RequestParam double longitude) {
        try {
            List<WeatherData> weatherDataList = weatherService.fetchWeatherData(latitude, longitude);

            for (WeatherData weatherData : weatherDataList) {
                Weather weather = weatherData.getWeather();
                if (weather != null && weather.getId() == null) {
                    weatherRepository.save(weather); 
                }
            }

            weatherDataRepository.saveAll(weatherDataList);

            if (weatherDataList.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(weatherDataList);
        } catch (Exception e) {
            logger.error("Error fetching weather data", e);
            return ResponseEntity.status(500).body(null);
        }
    }
     @GetMapping("/weather/csv")
    public void exportWeatherDataToCsv(HttpServletResponse response) {
        try {
            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=\"weather_data.csv\"");

            List<WeatherData> weatherDataList = weatherDataRepository.findAll();
            PrintWriter writer = response.getWriter();

            CsvExporter.writeWeatherDataToCsv(writer, weatherDataList);

        } catch (Exception e) { 
            throw new RuntimeException("Error during CSV export", e);
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
