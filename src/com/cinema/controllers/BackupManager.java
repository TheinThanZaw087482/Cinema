package com.cinema.controllers;

import java.io.*;
import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

public class BackupManager {
    private static final String DB_NAME = "cinema_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "yourpassword";
    private static final String BACKUP_DIR = "backup/";
    private Connection connection;

    public BackupManager(Connection connection) {
        this.connection = connection;
        new File(BACKUP_DIR).mkdirs();
    }

    private String nowTimestampFileName(String prefix) {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm"));
        return BACKUP_DIR + prefix + "_" + date + ".sql";
    }

    private void runCommand(String command) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", command);
        pb.redirectErrorStream(true);
        pb.start();
    }

    private LocalDateTime getLastBackupTime(String type) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT last_backup_date FROM last_backup WHERE type = ?");
        ps.setString(1, type);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getTimestamp("last_backup_date").toLocalDateTime();
        }
        return null;
    }

    private void updateLastBackupTime(String type, LocalDateTime now) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("UPDATE last_backup SET last_backup_date = ? WHERE type = ?");
        ps.setTimestamp(1, Timestamp.valueOf(now));
        ps.setString(2, type);
        ps.executeUpdate();
    }

    private void exportTable(String table, String dateColumn, String dateAfter, String filenamePrefix) throws SQLException, IOException {
        String fileName = nowTimestampFileName(filenamePrefix);
        String query = String.format("SELECT * FROM %s WHERE %s > '%s'", table, dateColumn, dateAfter);

        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        FileWriter writer = new FileWriter(fileName);
        ResultSetMetaData md = rs.getMetaData();
        int cols = md.getColumnCount();

        while (rs.next()) {
            StringBuilder sb = new StringBuilder("INSERT INTO " + table + " VALUES(");
            for (int i = 1; i <= cols; i++) {
                sb.append("'").append(rs.getString(i)).append("'");
                if (i != cols) sb.append(",");
            }
            sb.append(");\n");
            writer.write(sb.toString());
        }

        writer.close();
    }

    public void performFullBackup() {
        try {
            String file = nowTimestampFileName("full");
            String command = String.format("mysqldump -u%s -p%s %s -r %s", DB_USER, DB_PASSWORD, DB_NAME, file);
            runCommand(command);
            updateLastBackupTime("full", LocalDateTime.now());
            System.out.println("Full backup completed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void performDifferentialBackup() {
        try {
            LocalDateTime lastFull = getLastBackupTime("full");
            String dateStr = Timestamp.valueOf(lastFull).toString();

            exportTable("booking", "BookDate", dateStr, "diff_booking");
            exportTable("sale", "SaleDate", dateStr, "diff_sale");

            System.out.println("Differential backup completed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void performIncrementalBackup() {
        try {
            LocalDateTime lastInc = getLastBackupTime("incremental");
            String dateStr = Timestamp.valueOf(lastInc).toString();

            exportTable("booking", "BookDate", dateStr, "inc_booking");
            exportTable("sale", "SaleDate", dateStr, "inc_sale");

            updateLastBackupTime("incremental", LocalDateTime.now());
            System.out.println("Incremental backup completed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startScheduler(BackupManager backupManager) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        scheduler.scheduleAtFixedRate(() -> {
            LocalDateTime now = LocalDateTime.now();
            int hour = now.getHour();
            DayOfWeek day = now.getDayOfWeek();
            int dom = now.getDayOfMonth();

            if (hour == 21) {
                if (dom == 1) {
                    backupManager.performFullBackup();
                } else if (day == DayOfWeek.SUNDAY) {
                    backupManager.performDifferentialBackup();
                } else {
                    backupManager.performIncrementalBackup();
                }
            }
        }, 0, 1, TimeUnit.HOURS);
    }
} 

