package khdev.com.soa;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import khdev.com.soa.models.WeatherData;

public class ApiResponse {
    private List<WeatherData> data;
    private String timezone;
    @JsonProperty("city_name")
    private String cityName;
    private double lat;
    @JsonProperty("state_code")
    private String stateCode;
    @JsonProperty("country_code")
    private String country_code;
    private String lon;

    public String getCountryCode() {
        return country_code;
    }

    public void setCountryCode(String country_code) {
        this.country_code = country_code;
    }

    public String getLon() {
        return lon;
    }
    public void setLon(String lon) {
        this.lon = lon;
    }
    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
     public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
    public List<WeatherData> getData() {
        return data;
    }

    public void setData(List<WeatherData> data) {
        this.data = data;
    }
}
