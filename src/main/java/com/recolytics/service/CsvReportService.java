package com.recolytics.service;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.Map;

public interface CsvReportService {

    /**
     * Processes a CSV file and returns a summary map (e.g., total amount and row count).
     */
    Map<String, Object> generateReport(MultipartFile file);

    /**
     * Returns a downloadable CSV summary as a response stream.
     */
    StreamingResponseBody downloadSummaryCsv();

    /**
     * Returns the CSV summary as a byte array, useful for attaching in emails.
     */
    byte[] generateSummaryCsvBytes();
}
