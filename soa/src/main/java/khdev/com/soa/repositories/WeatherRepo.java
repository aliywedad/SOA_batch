package khdev.com.soa.repositories;

import khdev.com.soa.Entites.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepo extends JpaRepository<WeatherEntity, Long> {


}