package khdev.com.soa.Processor;


import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import khdev.com.soa.models.WeatherData;

@Component
public class WeatherDataProcessor implements ItemProcessor<WeatherData, WeatherData> {

    @Override
    public WeatherData process(WeatherData item) throws Exception {
        item.setTemp((item.getTemp() - 32) * 5 / 9); 
        return item;
    }
}
