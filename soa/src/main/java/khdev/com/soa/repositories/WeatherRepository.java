package khdev.com.soa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import khdev.com.soa.Entities.Weather;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {
}
