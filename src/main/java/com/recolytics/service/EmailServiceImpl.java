// src/main/java/com/recolytics/service/EmailServiceImpl.java
package com.recolytics.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    // Optional: Run weekly (e.g., Monday at 9AM)
    @Scheduled(cron = "0 0 9 * * MON")
    public void sendWeeklyReport() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("receiver@example.com"); // Replace with actual email
        message.setSubject("Weekly Summary Report");
        message.setText("Your weekly report is ready. Total: X, Rows Processed: Y");

        mailSender.send(message);
        log.info("Weekly report email sent successfully.");
    }

    @Override
    public void sendSummaryReport() {

    }
}
