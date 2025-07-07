package com.recolytics.service;

import com.recolytics.model.Transaction;
import com.recolytics.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class CsvReportServiceImpl implements CsvReportService {

    private final TransactionRepository transactionRepository;

    private double total = 0.0;
    private int rowCount = 0;

    @Override
    public Map<String, Object> generateReport(MultipartFile file) {
        total = 0.0;
        rowCount = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CSVParser parser = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .parse(reader);

            for (CSVRecord record : parser) {
                rowCount++;
                try {
                    String name = record.get("name");
                    String amountStr = record.get("amount");
                    String dateStr = record.get("date");

                    double amount = Double.parseDouble(amountStr);
                    LocalDate date = LocalDate.parse(dateStr); // Format: yyyy-MM-dd

                    Transaction transaction = Transaction.builder()
                            .name(name)
                            .amount(amount)
                            .date(date)
                            .build();

                    transactionRepository.save(transaction);
                    total += amount;

                } catch (Exception e) {
                    log.warn("Skipping malformed row (row {}): {}", rowCount, e.getMessage());
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Error reading CSV file", e);
        }

        Map<String, Object> report = new HashMap<>();
        report.put("total_amount", total);
        report.put("rows_processed", rowCount);

        return report;
    }

    @Override
    public byte[] generateSummaryCsvBytes() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream));

        writer.println("Metric,Value");
        writer.println("Total Amount," + total);
        writer.println("Rows Processed," + rowCount);
        writer.flush();

        return outputStream.toByteArray();
    }

    @Override
    public StreamingResponseBody downloadSummaryCsv() {
        return outputStream -> {
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream));
            writer.println("Metric,Value");
            writer.println("Total Amount," + total);
            writer.println("Rows Processed," + rowCount);
            writer.flush();
        };
    }
}
