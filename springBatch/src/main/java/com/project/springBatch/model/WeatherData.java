package com.project.springBatch.model;

public class WeatherData {
    private String city;
    private double temperature; // Celsius
    private String condition; // Weather description
    private double latitude;
    private double longitude;

    // Default constructor
    public WeatherData() {}

    // Constructor for initializing all fields
    public WeatherData(String city, double temperature, String condition, double latitude, double longitude) {
        this.city = city;
        this.temperature = temperature;
        this.condition = condition;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Constructor for minimal fields
    public WeatherData(String city, double temperature, String condition) {
        this.city = city;
        this.temperature = temperature;
        this.condition = condition;
    }

    // Getters and Setters
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "city='" + city + '\'' +
                ", temperature=" + temperature +
                ", condition='" + condition + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
