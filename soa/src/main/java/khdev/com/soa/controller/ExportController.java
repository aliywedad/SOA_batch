package khdev.com.soa.controller;

import java.io.ByteArrayInputStream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Component;

import khdev.com.soa.services.ExportService;

@RestController
@RequestMapping("/api/export")
@Component("exportController")  // Explicitly name the bean to avoid conflicts
public class ExportController {

    private final ExportService exportService;

    public ExportController(ExportService exportService) {
        this.exportService = exportService;
    }

    // Export to CSV Endpoint
    @GetMapping("/csv")
    public ResponseEntity<byte[]> exportToCsv() {
        ByteArrayInputStream byteArrayInputStream = exportService.exportToCsv();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=weather_data.csv")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(byteArrayInputStream.readAllBytes());
    }

    // Export to Excel Endpoint
    @GetMapping("/excel")
    public ResponseEntity<byte[]> exportToExcel() {
        ByteArrayInputStream byteArrayInputStream = exportService.exportToExcel();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=weather_data.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(byteArrayInputStream.readAllBytes());
    }
}
