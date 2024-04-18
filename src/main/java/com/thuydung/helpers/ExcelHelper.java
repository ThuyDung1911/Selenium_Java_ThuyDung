package com.thuydung.helpers;

import com.thuydung.utils.LogUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class ExcelHelper {
    private FileInputStream fis;
    private FileOutputStream fileOut;
    private Workbook workbook;
    private Sheet sheet;
    private Cell cell;
    private Row row;
    private String excelFilePath;
    private Map<String, Integer> columns = new HashMap<>();

    public void setExcelFile(String excelPath, String sheetName) {
        LogUtils.info("----------- Chuẩn bị data test -----------");
        LogUtils.info("Set Excel File: " + excelPath);
        LogUtils.info("Sheet Name: " + sheetName);
        LogUtils.info("----------- Setup data test thành công -----------");

        try {
            File f = new File(excelPath);

            if (!f.exists()) {
                LogUtils.info("File Excel path không tìm thấy.");
                throw new FileNotFoundException("File Excel path không tìm thấy.");
            }
            if (sheetName.isEmpty()) {
                LogUtils.info("The Sheet Name trống.");
                throw new EmptyStackException();
            }

            fis = new FileInputStream(excelPath);
            workbook = WorkbookFactory.create(fis);
            sheet = workbook.getSheet(sheetName);
            //sh = wb.getSheetAt(0); //0 - index of 1st sheet
            if (sheet == null) {
                sheet = workbook.createSheet(sheetName);
            }

            this.excelFilePath = excelPath;

            //adding all the column header names to the map 'columns'
            sheet.getRow(0).forEach(cell -> {
                columns.put(cell.getStringCellValue(), cell.getColumnIndex());
            });

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getCellData(int rowNumber, int colNumber) {
        try {
            cell = sheet.getRow(rowNumber).getCell(colNumber);
            String CellData = null;
            switch (cell.getCellType()) {
                case STRING:
                    CellData = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        CellData = String.valueOf(cell.getDateCellValue());
                    } else {
                        CellData = String.valueOf((long) cell.getNumericCellValue());
                    }
                    break;
                case BOOLEAN:
                    CellData = Boolean.toString(cell.getBooleanCellValue());
                    break;
                case BLANK:
                    CellData = "";
                    break;
            }
            return CellData;
        } catch (Exception e) {
            return "";
        }
    }

    //Gọi ra hàm này nè
    public String getCellData(String columnName, int rowNumber) {
        return getCellData(rowNumber, columns.get(columnName));
    }

    //Get last row number (lấy vị trí dòng cuối cùng tính từ 0)
    public int getLastRowNum() {
        return sheet.getLastRowNum();
    }

    //Lấy số dòng có data đang sử dụng
    public int getPhysicalNumberOfRows() {
        return sheet.getPhysicalNumberOfRows();
    }

    // Write data to excel sheet

    //set by column index
    public void setCellData(String text, int rowNumber, int colNumber) {
        try {
            row = sheet.getRow(rowNumber);
            if (row == null) {
                row = sheet.createRow(rowNumber);
            }
            cell = row.getCell(colNumber);

            if (cell == null) {
                cell = row.createCell(colNumber);
            }
            cell.setCellValue(text);

            XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.WHITE.getIndex());

            text = text.trim().toLowerCase();
            if (text.equals("pass") || text.equals("passed")) {
                style.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
            }
            if (text.equals("fail") || text.equals("failed")) {
                style.setFillForegroundColor(IndexedColors.RED.getIndex());
            }
            style.setFillPattern(FillPatternType.NO_FILL);
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);

            cell.setCellStyle(style);

            fileOut = new FileOutputStream(excelFilePath);
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //set by column name
    public void setCellData(String text, int rowNumber, String columnName) {
        try {
            row = sheet.getRow(rowNumber);
            if (row == null) {
                row = sheet.createRow(rowNumber);
            }
            cell = row.getCell(columns.get(columnName));

            if (cell == null) {
                cell = row.createCell(columns.get(columnName));
            }
            cell.setCellValue(text);

            XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.WHITE.getIndex());

            text = text.trim().toLowerCase();
            if (text.equals("pass") || text.equals("passed")) {
                style.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
            }
            if (text.equals("fail") || text.equals("failed")) {
                style.setFillForegroundColor(IndexedColors.RED.getIndex());
            }

            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);

            cell.setCellStyle(style);

            fileOut = new FileOutputStream(excelFilePath);
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    //Hàm này dùng cho trường hợp ít Field trong File Excel
    public Object[][] getExcelData(String fileName, String sheetName) {
        Object[][] data = null;
        Workbook workbook = null;
        try {
            FileInputStream fis = new FileInputStream(fileName);
            String fileExtensionName = fileName.substring(fileName.indexOf("."));

            //workbook = new HSSFWorkbook(fis); //read file excel .xls
            workbook = new XSSFWorkbook(fis); //read file excel .xlsx

            // load the sheet
            Sheet sheet = workbook.getSheet(sheetName);

            // load the row
            Row row = sheet.getRow(0);


            int noOfRows = sheet.getPhysicalNumberOfRows();
            int noOfCols = row.getLastCellNum();

            System.out.println(noOfRows + " - " + noOfCols);

            Cell cell;
            data = new Object[noOfRows - 1][noOfCols];

            //Vòng lặp FOR chạy từ 1 để bỏ dòng tiêu đề (dòng tiêu đề là 0)
            for (int i = 1; i < noOfRows; i++) {
                for (int j = 0; j < noOfCols; j++) {
                    row = sheet.getRow(i);
                    cell = row.getCell(j);

                    //Này dùng để xác định kiểu dữ liệu từ ô trong excel rồi chuển về String luôn cho tiện đọc
                    switch (cell.getCellType()) {
                        case STRING:
                            data[i - 1][j] = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            data[i - 1][j] = String.valueOf(cell.getNumericCellValue());
                            break;
                        case BLANK:
                            data[i - 1][j] = "";
                            break;
                        default:
                            data[i - 1][j] = null;
                            break;
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    public int getColumns() {
        try {
            row = sheet.getRow(0);
            return row.getLastCellNum();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw (e);
        }
    }

    //Hàm này dùng cho trường hợp nhiều Field trong File Excel
    public Object[][] getExcelDataHashTable(String excelPath, String sheetName, int startRow, int endRow) {
        System.out.println("Excel Path: " + excelPath);
        Object[][] data = null;

        try {
            File f = new File(excelPath);
            if (!f.exists()) {
                System.out.println("File Excel path không tìm thấy.");
                throw new IOException("File Excel path không tìm thấy.");
            }

            fis = new FileInputStream(excelPath);

            workbook = new XSSFWorkbook(fis);

            sheet = workbook.getSheet(sheetName);

            int rows = getLastRowNum();
            int columns = getColumns();

            System.out.println("Row: " + rows + " - Column: " + columns);
            System.out.println("StartRow: " + startRow + " - EndRow: " + endRow);

            data = new Object[(endRow - startRow) + 1][1];
            Hashtable<String, String> table = null;
            for (int rowNums = startRow; rowNums <= endRow; rowNums++) {
                table = new Hashtable<>();
                for (int colNum = 0; colNum < columns; colNum++) {
                    table.put(getCellData(0, colNum), getCellData(rowNums, colNum));
                }
                data[rowNums - startRow][0] = table;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return data;
    }

    public Object[][] getDataHashTable(String excelPath, String sheetName, int startRow, int endRow) {
        System.out.println("Excel Path: " + excelPath);
        Object[][] data = null;

        try {
            File f = new File(excelPath);
            if (!f.exists()) {
                System.out.println("File Excel path không tìm thấy.");
                throw new IOException("File Excel path không tìm thấy.");
            }

            fis = new FileInputStream(excelPath);

            workbook = new XSSFWorkbook(fis);

            sheet = workbook.getSheet(sheetName);

            int rows = getLastRowNum();
            int columns = getColumns();

            System.out.println("Row: " + rows + " - Column: " + columns);
            System.out.println("StartRow: " + startRow + " - EndRow: " + endRow);

            data = new Object[(endRow - startRow) + 1][1];
            Hashtable<String, String> table = null;
            for (int rowNums = startRow; rowNums <= endRow; rowNums++) {
                table = new Hashtable<>();
                for (int colNum = 0; colNum < columns; colNum++) {
                    table.put(getCellData(0, colNum), getCellData(rowNums, colNum));
                }
                data[rowNums - startRow][0] = table;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return data;
    }
}
