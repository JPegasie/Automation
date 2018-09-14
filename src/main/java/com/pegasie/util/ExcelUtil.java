package com.pegasie.util;

import com.pegasie.datamodel.TestCase;
import com.pegasie.datamodel.TestStep;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ExcelUtil {
    public static HashMap getConfigValueFromExcel(Sheet sheet) {
        HashMap<String, String> result = new HashMap<String, String>();
        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue;
            }
            if (row.getCell(1).getCellTypeEnum() == CellType.NUMERIC) {
                int value = (int) row.getCell(1).getNumericCellValue();
                result.put(row.getCell(0).getStringCellValue(), String.valueOf(value));
            } else {
                result.put(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue());
            }
        }
        return result;
    }

    public static List<TestCase> getActiveTestCaseFromExcel(Sheet sheet) {
        List<TestCase> testCaseList = new ArrayList<>();
        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue;
            }
            String test = row.getCell(2).getStringCellValue();
            if (row.getCell(2).getStringCellValue().equals("Oui")) {
                TestCase testCase = new TestCase((int) row.getCell(0).getNumericCellValue(), row.getCell(1).getStringCellValue(),
                        row.getCell(2).getStringCellValue());
                testCaseList.add(testCase);
            }
        }
        return testCaseList;
    }

    public static ArrayList<TestStep> getStepArrayFromExcel(Sheet sheet, String caseName, int argumentColumn) {
        ArrayList<TestStep> testStepArray = new ArrayList<>();
        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue;
            }
            if (row.getCell(1).getStringCellValue().equalsIgnoreCase(caseName)) {
                TestStep testStep = null;
                if (row.getCell(argumentColumn) == null) {
                    testStep = new TestStep((int) row.getCell(0).getNumericCellValue(), row.getCell(1).getStringCellValue(), row.getCell(2).getStringCellValue(),
                            row.getCell(3).getStringCellValue(), "");
                } else if (row.getCell(argumentColumn).getCellTypeEnum() == CellType.NUMERIC) {
                    testStep = new TestStep((int) row.getCell(0).getNumericCellValue(), row.getCell(1).getStringCellValue(), row.getCell(2).getStringCellValue(),
                            row.getCell(3).getStringCellValue(), String.valueOf(row.getCell(argumentColumn).getNumericCellValue()));
                } else {
                    testStep = new TestStep((int) row.getCell(0).getNumericCellValue(), row.getCell(1).getStringCellValue(), row.getCell(2).getStringCellValue(),
                            row.getCell(3).getStringCellValue(), row.getCell(argumentColumn).getStringCellValue());
                }
                testStepArray.add(testStep);
            }
        }
        return testStepArray;
    }

    public static String getAllContentFromExcel(File file) {
        String content = "";
        try (Workbook workbook = WorkbookFactory.create(file)) {
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                Sheet sheet = workbook.getSheetAt(i);
                String sheetName = sheet.getSheetName();
                for (Row row : sheet) {
                    for (Cell cell : row) {
                        if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                            int value = (int) cell.getNumericCellValue();
                            content += String.valueOf(value) + "/n";
                        } else {
                            content += cell.getStringCellValue() + "/n";
                        }
                    }
                }
            }
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}

