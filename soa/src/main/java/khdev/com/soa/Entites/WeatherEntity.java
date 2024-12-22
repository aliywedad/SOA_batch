package khdev.com.soa.Entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WeatherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Add strategy if required
    private Long id;

    private String datetime;
    private double temp;
    private double appTemp;
    private double snow;
    private double solarRad;
    private double windGustSpd;
    private double pop;
    private double ozone;
    private int cloudsHi;
    private int cloudsLow;
    private String timestampUtc;
    private String windCdir;
    private int rh;
    private String pod;
    private int pres;
    private double snowDepth;
    private int clouds;
    private double vis;
    private double windSpd;
    private String windCdirFull;
    private int slp;
    private String timestampLocal;
    private String ts;
    private double dni;
    private double dewpt;
    private double uv;
    private int cloudsMid;
    private int windDir;
    private double ghi;
    private double dhi;
    private double precip;
}
