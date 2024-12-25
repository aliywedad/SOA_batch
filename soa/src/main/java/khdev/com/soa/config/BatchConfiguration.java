package khdev.com.soa.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import khdev.com.soa.Entities.WeatherData;
import khdev.com.soa.Processor.WeatherDataProcessor;
import khdev.com.soa.Reader.WeatherDataReader;
import khdev.com.soa.Writer.WeatherDataWriter;
import khdev.com.soa.listeners.JobCompletionNotificationListener;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    private final WeatherDataReader weatherDataReader;
    private final ItemProcessor<WeatherData, WeatherData> weatherDataProcessor;
    private final WeatherDataWriter weatherDataWriter;
    private final JobCompletionNotificationListener listeners;
    public BatchConfiguration(WeatherDataReader weatherDataReader,
                              WeatherDataProcessor weatherDataProcessor,
                              WeatherDataWriter weatherDataWriter , JobCompletionNotificationListener listeners) {
        this.weatherDataReader = weatherDataReader;
        this.weatherDataProcessor = weatherDataProcessor;
        this.weatherDataWriter = weatherDataWriter;
        this.listeners = listeners;
    }

    @Bean
    public Step weatherStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("weatherStep", jobRepository)
            .<WeatherData, WeatherData>chunk(10, transactionManager)
            .reader(weatherDataReader)
            .processor(weatherDataProcessor)
            .writer(weatherDataWriter)
            .build();
    }

    @Bean
    public Job weatherJob(JobRepository jobRepository, Step weatherStep) {
        return new JobBuilder("weatherJob", jobRepository)
            .listener(listeners)
            .start(weatherStep)
            .build();
    }
}
