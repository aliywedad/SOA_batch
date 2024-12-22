package khdev.com.soa.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherData {

    @JsonProperty("datetime")
    private String datetime;

    @JsonProperty("temp")
    private double temp;

    @JsonProperty("app_temp")
    private double appTemp;

    @JsonProperty("snow")
    private double snow;

    @JsonProperty("solar_rad")
    private double solarRad;

    @JsonProperty("wind_gust_spd")
    private double windGustSpd;

    @JsonProperty("pop")
    private double pop;

    @JsonProperty("ozone")
    private double ozone;

    @JsonProperty("clouds_hi")
    private int cloudsHi;

    @JsonProperty("clouds_low")
    private int cloudsLow;

    @JsonProperty("timestamp_utc")
    private String timestampUtc;

    @JsonProperty("wind_cdir")
    private String windCdir;

    @JsonProperty("rh")
    private int rh;

    @JsonProperty("pod")
    private String pod;

    @JsonProperty("pres")
    private int pres;

    @JsonProperty("snow_depth")
    private double snowDepth;

    @JsonProperty("clouds")
    private int clouds;

    @JsonProperty("vis")
    private double vis;

    @JsonProperty("wind_spd")
    private double windSpd;

    @JsonProperty("wind_cdir_full")
    private String windCdirFull;

    @JsonProperty("slp")
    private int slp;

    @JsonProperty("timestamp_local")
    private String timestampLocal;

    @JsonProperty("ts")
    private String ts;

    @JsonProperty("dni")
    private double dni;

    @JsonProperty("dewpt")
    private double dewpt;

    @JsonProperty("uv")
    private double uv;

    @JsonProperty("clouds_mid")
    private int cloudsMid;

    @JsonProperty("wind_dir")
    private int windDir;

    @JsonProperty("ghi")
    private double ghi;

    @JsonProperty("dhi")
    private double dhi;

    @JsonProperty("precip")
    private double precip;

    @JsonProperty("weather")
    private Weather weather;

    // Getters and Setters for all fields
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

    public double getSnow() {
        return snow;
    }

    public void setSnow(double snow) {
        this.snow = snow;
    }

    public double getSolarRad() {
        return solarRad;
    }

    public void setSolarRad(double solarRad) {
        this.solarRad = solarRad;
    }

    public double getWindGustSpd() {
        return windGustSpd;
    }

    public void setWindGustSpd(double windGustSpd) {
        this.windGustSpd = windGustSpd;
    }

    public double getPop() {
        return pop;
    }

    public void setPop(double pop) {
        this.pop = pop;
    }

    public double getOzone() {
        return ozone;
    }

    public void setOzone(double ozone) {
        this.ozone = ozone;
    }

    public int getCloudsHi() {
        return cloudsHi;
    }

    public void setCloudsHi(int cloudsHi) {
        this.cloudsHi = cloudsHi;
    }

    public int getCloudsLow() {
        return cloudsLow;
    }

    public void setCloudsLow(int cloudsLow) {
        this.cloudsLow = cloudsLow;
    }

    public String getTimestampUtc() {
        return timestampUtc;
    }

    public void setTimestampUtc(String timestampUtc) {
        this.timestampUtc = timestampUtc;
    }

    public String getWindCdir() {
        return windCdir;
    }

    public void setWindCdir(String windCdir) {
        this.windCdir = windCdir;
    }

    public int getRh() {
        return rh;
    }

    public void setRh(int rh) {
        this.rh = rh;
    }

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

    public int getPres() {
        return pres;
    }

    public void setPres(int pres) {
        this.pres = pres;
    }

    public double getSnowDepth() {
        return snowDepth;
    }

    public void setSnowDepth(double snowDepth) {
        this.snowDepth = snowDepth;
    }

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    public double getVis() {
        return vis;
    }

    public void setVis(double vis) {
        this.vis = vis;
    }

    public double getWindSpd() {
        return windSpd;
    }

    public void setWindSpd(double windSpd) {
        this.windSpd = windSpd;
    }

    public String getWindCdirFull() {
        return windCdirFull;
    }

    public void setWindCdirFull(String windCdirFull) {
        this.windCdirFull = windCdirFull;
    }

    public int getSlp() {
        return slp;
    }

    public void setSlp(int slp) {
        this.slp = slp;
    }

    public String getTimestampLocal() {
        return timestampLocal;
    }

    public void setTimestampLocal(String timestampLocal) {
        this.timestampLocal = timestampLocal;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public double getDni() {
        return dni;
    }

    public void setDni(double dni) {
        this.dni = dni;
    }

    public double getDewpt() {
        return dewpt;
    }

    public void setDewpt(double dewpt) {
        this.dewpt = dewpt;
    }

    public double getUv() {
        return uv;
    }

    public void setUv(double uv) {
        this.uv = uv;
    }

    public int getCloudsMid() {
        return cloudsMid;
    }

    public void setCloudsMid(int cloudsMid) {
        this.cloudsMid = cloudsMid;
    }

    public int getWindDir() {
        return windDir;
    }

    public void setWindDir(int windDir) {
        this.windDir = windDir;
    }

    public double getGhi() {
        return ghi;
    }

    public void setGhi(double ghi) {
        this.ghi = ghi;
    }

    public double getDhi() {
        return dhi;
    }

    public void setDhi(double dhi) {
        this.dhi = dhi;
    }

    public double getPrecip() {
        return precip;
    }

    public void setPrecip(double precip) {
        this.precip = precip;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }
}
