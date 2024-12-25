package khdev.com.soa.utils;

import java.io.Writer;
import java.util.List;

import com.opencsv.CSVWriter;

import khdev.com.soa.Entities.WeatherData;

public class CsvExporter {

    public static void writeWeatherDataToCsv(Writer writer, List<WeatherData> weatherDataList) {
        try (CSVWriter csvWriter = new CSVWriter(writer)) {
            // Header row
            String[] header = {"ID", "Temperature", "appTemp", "Snow", "solarRad"};
            csvWriter.writeNext(header);

            // Data rows
            for (WeatherData data : weatherDataList) {
                csvWriter.writeNext(new String[]{
                        String.valueOf(data.getId()),
                        String.valueOf(data.getTemp()),
                        String.valueOf(data.getAppTemp()),
                        String.valueOf(data.getSnow()),
                        String.valueOf(data.getSolarRad())
                });
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while writing CSV file", e);
        }
    }
}
