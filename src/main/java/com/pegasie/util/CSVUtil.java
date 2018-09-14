package com.pegasie.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class CSVUtil {
    public static void initTestResultFile(String filePath) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath),StandardCharsets.ISO_8859_1, StandardOpenOption.CREATE_NEW);
            StringBuilder sb = new StringBuilder();
            sb.append("Test Plan");
            sb.append("\t");
            sb.append("Test Id");
            sb.append("\t");
            sb.append("Test Name");
            sb.append("\t");
            sb.append("Language");
            sb.append("\t");
            sb.append("Test Result");
            sb.append('\n');
            writer.write(sb.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void insertToResultFile(String filePath, String testPlan, String testId, String testName, String language, String testResult) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath),StandardCharsets.ISO_8859_1,StandardOpenOption.APPEND);
            StringBuilder sb = new StringBuilder();
            sb.append(testPlan);
            sb.append("\t");
            sb.append(testId);
            sb.append("\t");
            sb.append(testName);
            sb.append("\t");
            sb.append(language);
            sb.append("\t");
            sb.append(testResult);
            sb.append('\n');
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initExecutionDetailFile(String filePath) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath),StandardCharsets.ISO_8859_1, StandardOpenOption.CREATE_NEW);
            StringBuilder sb = new StringBuilder();
            sb.append("Test Plan");
            sb.append("\t");
            sb.append("Test Id");
            sb.append("\t");
            sb.append("Test Name");
            sb.append("\t");
            sb.append("Page");
            sb.append("\t");
            sb.append("Operation");
            sb.append("\t");
            sb.append("Parametres");
            sb.append("\t");
            sb.append("Message");
            sb.append('\n');
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void insertToExecutionDetailFile(String filePath, String testPlan, String testId, String testName, String page, String operation, String parametres, String message) {
        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath),StandardCharsets.ISO_8859_1,StandardOpenOption.APPEND);
            StringBuilder sb = new StringBuilder();
            sb.append(testPlan);
            sb.append("\t");
            sb.append(testId);
            sb.append("\t");
            sb.append(testName);
            sb.append("\t");
            sb.append(page);
            sb.append("\t");
            sb.append(operation);
            sb.append("\t");
            sb.append(parametres);
            sb.append("\t");
            sb.append(message);
            sb.append('\n');
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
