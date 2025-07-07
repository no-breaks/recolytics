package com.recolytics.controller;

import com.recolytics.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ReportEmailController {

    private final EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendReport() {
        try {
            emailService.sendSummaryReport(); // This uses your existing summary logic
            return ResponseEntity.ok("Report sent successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to send report: " + e.getMessage());
        }
    }
}
