package com.recolytics.controller;

import com.recolytics.service.CsvReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RestController
@RequestMapping("/api/csv")
@RequiredArgsConstructor
public class CsvController {

    private final CsvReportService csvReportService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadCsv(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a valid CSV file.");
        }

        var report = csvReportService.generateReport(file);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/download-summary")
    public ResponseEntity<StreamingResponseBody> downloadSummary() {
        StreamingResponseBody stream = csvReportService.downloadSummaryCsv();

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=summary.csv")
                .header("Content-Type", "text/csv")
                .body(stream);
    }
}
