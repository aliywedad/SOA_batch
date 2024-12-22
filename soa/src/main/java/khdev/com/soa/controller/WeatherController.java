package khdev.com.soa.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private final JobLauncher jobLauncher;
    private final Job weatherJob;

    @Autowired
    public WeatherController(JobLauncher jobLauncher, Job weatherJob) {
        this.jobLauncher = jobLauncher;
        this.weatherJob = weatherJob;
    }

    @GetMapping("/run-weather-job")
    public String runWeatherJob(@RequestParam("time") Long time) {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", time) // Ajout d'un paramètre pour éviter l'exécution multiple du même job
                .toJobParameters();
            jobLauncher.run(weatherJob, jobParameters);
            return "Job executed successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Job execution failed: " + e.getMessage();
        }
    }
}
