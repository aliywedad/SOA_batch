package khdev.com.soa.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import khdev.com.soa.Entities.WeatherData;
import khdev.com.soa.repositories.WeatherDataRepository;

@Service
public class ExportService {

    private final WeatherDataRepository weatherDataRepository;

    public ExportService(WeatherDataRepository weatherDataRepository) {
        this.weatherDataRepository = weatherDataRepository;
    }

    // Export WeatherData to CSV
    public ByteArrayInputStream exportToCsv() {
        List<WeatherData> weatherDataList = weatherDataRepository.findAll();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(out);

        // Write CSV Header
        writer.println("ID,Datetime,Temp,AppTemp,Snow,SolarRad,WindGustSpd,Pop,Ozone,CloudsHi,CloudsLow");

        // Write Data
        for (WeatherData data : weatherDataList) {
            writer.println(String.format(
                    "%d,%s,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%d,%d",
                    data.getId(), data.getDatetime(), data.getTemp(), data.getAppTemp(), data.getSnow(),
                    data.getSolarRad(), data.getWindGustSpd(), data.getPop(), data.getOzone(),
                    data.getCloudsHi(), data.getCloudsLow()
            ));
        }

        writer.flush();
        return new ByteArrayInputStream(out.toByteArray());
    }

    // Export WeatherData to Excel
    public ByteArrayInputStream exportToExcel() {
        List<WeatherData> weatherDataList = weatherDataRepository.findAll();
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Weather Data");

            // Create Header Row
            Row headerRow = sheet.createRow(0);
            String[] headers = {
                    "ID", "Datetime", "Temp", "AppTemp", "Snow", "SolarRad", "WindGustSpd", "Pop", "Ozone", "CloudsHi", "CloudsLow"
            };

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Write Data Rows
            int rowIdx = 1;
            for (WeatherData data : weatherDataList) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(data.getId());
                row.createCell(1).setCellValue(data.getDatetime());
                row.createCell(2).setCellValue(data.getTemp());
                row.createCell(3).setCellValue(data.getAppTemp());
                row.createCell(4).setCellValue(data.getSnow());
                row.createCell(5).setCellValue(data.getSolarRad());
                row.createCell(6).setCellValue(data.getWindGustSpd());
                row.createCell(7).setCellValue(data.getPop());
                row.createCell(8).setCellValue(data.getOzone());
                row.createCell(9).setCellValue(data.getCloudsHi());
                row.createCell(10).setCellValue(data.getCloudsLow());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("Failed to export data to Excel file", e);
        }
    }
}
