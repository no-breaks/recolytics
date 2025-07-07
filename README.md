# Recolytics 📊

**Recolytics** is a Spring Boot-based backend service for uploading CSV files, summarizing transactions, sending email reports, and managing user authentication. It offers data ingestion, processing, and report generation capabilities to help users analyze transaction trends.

---

## 🌟 Features

- 📥 Upload CSV transaction files with details like name, amount, and date
- 📊 Automatic summary generation (total amount, rows processed)
- 📧 Weekly email summary report using Gmail SMTP
- 🔐 JWT-based authentication for secure endpoints
- 💾 PostgreSQL database support
- 🛠️ Modular, clean architecture with Spring Boot

---

## 📂 CSV Format

Ensure your CSV follows this format:

```csv
name,amount,date
John Doe,1200.50,2024-07-01
Jane Smith,800.00,2024-07-02
