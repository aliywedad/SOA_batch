package khdev.com.soa.Reader;


import java.util.Iterator;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import khdev.com.soa.Entities.WeatherData;
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
            List<WeatherData> weatherDataList = weatherService.fetchWeatherData(36.7783, -119.4179); // Exemple de latitude et longitude
            weatherDataIterator = weatherDataList.iterator();
            
        }

        return weatherDataIterator != null && weatherDataIterator.hasNext() ? weatherDataIterator.next() : null;
    }
}
