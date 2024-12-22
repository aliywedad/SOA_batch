package khdev.com.soa.Writer;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import khdev.com.soa.models.WeatherData;

@Component
public class WeatherDataWriter implements ItemWriter<WeatherData> {

    @Override
    public void write(Chunk<? extends WeatherData> items) throws Exception {
        items.forEach(item -> {
            System.out.println("Saving Weather Data: " + item.getDatetime() + " - Temp: " + item.getTemp() + "C");
        });
    }
}
