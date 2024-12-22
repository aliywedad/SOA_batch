package khdev.com.soa;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import khdev.com.soa.models.WeatherData;

@SpringBootApplication
public class SoaApplication {
    private List<WeatherData> data;

    public List<WeatherData> getData() {
        return data;
    }

    public void setData(List<WeatherData> data) {
        this.data = data;
    }
	public static void main(String[] args) {
		SpringApplication.run(SoaApplication.class, args);
	}

}
