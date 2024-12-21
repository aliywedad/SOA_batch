package com.project.springBatch.batch;

import com.project.springBatch.model.WeatherData;
import com.project.springBatch.service.WeatherService;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class WeatherDataReader implements ItemReader<WeatherData> {

    private final WeatherService weatherService;
    private List<WeatherData> weatherDataList;
    private int nextWeatherIndex;

    private ExecutionContext executionContext;

    public WeatherDataReader(WeatherService weatherService) {
        this.weatherService = weatherService;
        this.nextWeatherIndex = 0;
    }

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        // Retrieve the ExecutionContext to access job parameters
        this.executionContext = stepExecution.getJobExecution().getExecutionContext();
    }

    @Override
    public WeatherData read() {
        if (weatherDataList == null) {
            initializeWeatherDataList();
        }

        WeatherData nextWeather = null;

        // Provide the next WeatherData item or null if all items have been read
        if (nextWeatherIndex < weatherDataList.size()) {
            nextWeather = weatherDataList.get(nextWeatherIndex);
            nextWeatherIndex++;
        }

        return nextWeather;
    }

    private void initializeWeatherDataList() {
        try {
            // Retrieve cities from job parameters in ExecutionContext
            String cityParameter = (String) executionContext.get("cities");
            if (cityParameter == null || cityParameter.isEmpty()) {
                cityParameter = "Paris,London,New York"; // Default cities
            }

            List<String> cities = Arrays.asList(cityParameter.split(","));
            weatherDataList = weatherService.fetchWeatherData(cities);

            if (weatherDataList == null || weatherDataList.isEmpty()) {
                System.out.println("No weather data fetched for the provided cities.");
                weatherDataList = List.of(); // Ensure the list is not null
            }
        } catch (Exception e) {
            System.err.println("Error fetching weather data: " + e.getMessage());
            weatherDataList = List.of(); // Ensure a non-null list even on error
        }
    }
}
