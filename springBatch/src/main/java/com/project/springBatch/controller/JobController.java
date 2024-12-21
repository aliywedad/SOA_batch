package com.project.springBatch.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {

    private static final Logger logger = LoggerFactory.getLogger(JobController.class);

    private final JobLauncher jobLauncher;
    private final Job weatherJob;

    @Autowired
    public JobController(JobLauncher jobLauncher, Job weatherJob) {
        this.jobLauncher = jobLauncher;
        this.weatherJob = weatherJob;
    }

    @GetMapping("/start-weather-job")
    public ResponseEntity<String> startWeatherJob(@RequestParam(value = "cities", required = false) String cities) {
        try {
            // Provide default cities if none are passed
            if (cities == null || cities.isEmpty()) {
                cities = "Paris,London,New York";
            }

            // Validate cities input (basic example)
            if (!cities.matches("^[a-zA-Z, ]+$")) {
                return ResponseEntity.badRequest().body("Invalid cities format. Only letters, spaces, and commas are allowed.");
            }

            // Build job parameters
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("cities", cities)
                    .addLong("time", System.currentTimeMillis()) // Ensure uniqueness for each job
                    .toJobParameters();

            // Launch the job
            jobLauncher.run(weatherJob, jobParameters);

            logger.info("Weather job started successfully with cities: {}", cities);
            return ResponseEntity.ok("Weather job started successfully with cities: " + cities);

        } catch (Exception e) {
            logger.error("Failed to start weather job", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to start weather job. Error: " + e.getMessage());
        }
    }
}
