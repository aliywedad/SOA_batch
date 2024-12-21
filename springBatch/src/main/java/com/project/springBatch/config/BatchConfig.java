package com.project.springBatch.config;

import com.project.springBatch.batch.WeatherDataReader;
import com.project.springBatch.model.WeatherData;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final WeatherDataReader weatherDataReader;

    public BatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
                       WeatherDataReader weatherDataReader) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.weatherDataReader = weatherDataReader;
    }

    @Bean
    public Job weatherJob() {
        return jobBuilderFactory.get("weatherJob")
                .start(weatherStep())
                .build();
    }

    @Bean
    public Step weatherStep() {
        return stepBuilderFactory.get("weatherStep")
                .<WeatherData, WeatherData>chunk(10) // Processes 10 items at a time
                .reader(weatherDataReader)
                .processor(weatherProcessor())
                .writer(weatherWriter())
                .build();
    }

    @Bean
    public ItemProcessor<WeatherData, WeatherData> weatherProcessor() {
        return item -> {
            // Example: Filter out invalid or incomplete weather data
            if (item.getTemperature() >= -50 && item.getTemperature() <= 60) {
                item.setCondition(item.getCondition().toUpperCase()); // Example transformation
                System.out.println("Processing valid weather data: " + item);
                return item;
            } else {
                System.out.println("Skipping invalid weather data: " + item);
                return null; // Returning null skips the item
            }
        };
    }

    @Bean
    public ItemWriter<WeatherData> weatherWriter() {
        return items -> {
            // Example: Write data to the console or integrate with a database
            System.out.println("Writing weather data...");
            items.forEach(System.out::println);
        };
    }
}
