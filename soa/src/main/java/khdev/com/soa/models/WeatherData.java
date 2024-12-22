package khdev.com.soa.models;


public class WeatherData {

    private String datetime;
    private double temp;
    private double appTemp;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getAppTemp() {
        return appTemp;
    }

    public void setAppTemp(double appTemp) {
        this.appTemp = appTemp;
    }
}
