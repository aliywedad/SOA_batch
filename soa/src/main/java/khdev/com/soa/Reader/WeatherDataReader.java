package khdev.com.soa.Reader;


import java.util.Iterator;

import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import khdev.com.soa.models.WeatherData;
import khdev.com.soa.services.WeatherService;

@Component
public class WeatherDataReader implements ItemReader<WeatherData> {

    private final WeatherService weatherService;
    private Iterator<WeatherData> weatherDataIterator;

    public WeatherDataReader(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Override
    public WeatherData read() throws Exception {
        if (weatherDataIterator == null || !weatherDataIterator.hasNext()) {
            // Fetch new data and prepare the iterator
            weatherDataIterator = weatherService.fetchWeatherData(35.5, -78.5).iterator();
        }

        return weatherDataIterator != null && weatherDataIterator.hasNext() ? weatherDataIterator.next() : null;
    }
}
