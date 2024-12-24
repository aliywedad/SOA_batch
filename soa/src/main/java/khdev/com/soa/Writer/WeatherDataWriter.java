package khdev.com.soa.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import khdev.com.soa.Entities.WeatherData;
import khdev.com.soa.repositories.WeatherDataRepository;
@Component
public class WeatherDataWriter implements ItemWriter<WeatherData> {
    private final WeatherDataRepository weatherDataRepository;
    private static final Logger logger = LoggerFactory.getLogger(WeatherDataWriter.class);

     
    public WeatherDataWriter(WeatherDataRepository weatherDataRepository) {
        this.weatherDataRepository = weatherDataRepository;
    }
    @Override
    public void write(Chunk<? extends WeatherData> items) throws Exception {
        logger.info("Writing chunk of {} weather records to the database.", items.size());
        if (!items.isEmpty()) {
            weatherDataRepository.saveAll(items);
            System.out.println("Persisted " + items.size() + " weather records.");
        }else {
            System.out.println("No weather records to persist.");
        }
    }
}
