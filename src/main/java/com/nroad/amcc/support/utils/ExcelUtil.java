package com.nroad.amcc.support.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {

    /**
     * 总行数
     */
    private int totalRows = 0;

    public List<String> readProfessionDetails(InputStream inputStream) throws IOException {
        Workbook wb = new XSSFWorkbook(inputStream);
        Sheet sheet = wb.getSheetAt(0);
        totalRows = sheet.getPhysicalNumberOfRows();

        List<String> list = new ArrayList<>();

        for (int i = 1; i < totalRows; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < 15; j++) {
                Cell cell = row.getCell(j);
                if (cell == null) {
                    list.add("");
                } else {
                    cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                    list.add(cell.getStringCellValue());
                }
            }
        }
        return list;
    }

    public List<String> readProfessionDetailsArea(InputStream inputStream) throws IOException {
        Workbook wb = new XSSFWorkbook(inputStream);
        Sheet sheet = wb.getSheetAt(0);
        totalRows = sheet.getPhysicalNumberOfRows();

        List<String> list = new ArrayList<>();

        for (int i = 1; i < totalRows; i++) {
            Row row = sheet.getRow(i);
//            if (row == null) {
//                continue;
//            }

            for (int j = 0; j < 3; j++) {
                Cell cell = row.getCell(j);
                if (cell == null) {
                    list.add("");
                    continue;
                } else {
                    cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                    list.add(cell.getStringCellValue());
                }
            }
        }
        return list;
    }

    public List<String> readProfession(InputStream inputStream) throws IOException {

        Workbook wb = new XSSFWorkbook(inputStream);
        Sheet sheet = wb.getSheetAt(0);// 获取文件的指定工作表默认的第一个
        totalRows = sheet.getPhysicalNumberOfRows();

        List<String> list = new ArrayList<>();

        for (int i = 1; i < totalRows; i++) {
            Row row = sheet.getRow(i);  //获取第i行
            for (int j = 0; j < 10; j++) { // 第j个单元格
                Cell cell = row.getCell(j); // 读单元格
                if (cell == null) { // 空判断
                    list.add("");
                } else {
                    cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                    list.add(cell.getStringCellValue());
                }
            }
        }
        return list;
    }

    public List<String> readCommonQuestion(InputStream inputStream) throws IOException {

        Workbook wb = new XSSFWorkbook(inputStream);
        Sheet sheet = wb.getSheetAt(0);// 获取文件的指定工作表默认的第一个
        totalRows = sheet.getPhysicalNumberOfRows();

        List<String> list = new ArrayList<>();

        for (int i = 1; i < totalRows; i++) {
            Row row = sheet.getRow(i);  //获取第i行
            for (int j = 0; j < 3; j++) { // 第j个单元格
                Cell cell = row.getCell(j); // 读单元格
                if (cell == null) { // 空判断
                    list.add("");
                } else {
                    cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                    list.add(cell.getStringCellValue());
                }
            }
        }
        return list;
    }

    public List<String> readLastYearScore(InputStream inputStream) throws IOException {

        Workbook wb = new XSSFWorkbook(inputStream);
        Sheet sheet = wb.getSheetAt(0);// 获取文件的指定工作表默认的第一个
        totalRows = sheet.getPhysicalNumberOfRows();

        List<String> list = new ArrayList<>();

        for (int i = 1; i < totalRows; i++) {
            Row row = sheet.getRow(i);  //获取第i行
            for (int j = 0; j < 4; j++) { // 第j个单元格
                Cell cell = row.getCell(j); // 读单元格
                if (cell == null) { // 空判断
                    list.add("");
                } else {
                    cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                    list.add(cell.getStringCellValue());
                }
            }
        }
        return list;
    }

    public List<String> readAreaAdmitNumber(InputStream inputStream) throws IOException {

        Workbook wb = new XSSFWorkbook(inputStream);
        Sheet sheet = wb.getSheetAt(0);// 获取文件的指定工作表默认的第一个
        totalRows = sheet.getPhysicalNumberOfRows();

        List<String> list = new ArrayList<>();

        for (int i = 1; i < totalRows; i++) {
            Row row = sheet.getRow(i);  //获取第i行
            for (int j = 0; j < 5; j++) { // 第j个单元格
                Cell cell = row.getCell(j); // 读单元格
                if (cell == null) { // 空判断
                    list.add("");
                } else {
                    cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                    list.add(cell.getStringCellValue());
                }
            }
        }
        return list;
    }

    public List<String> readGraduateArea(InputStream inputStream) throws IOException {

        Workbook wb = new XSSFWorkbook(inputStream);
        Sheet sheet = wb.getSheetAt(0);// 获取文件的指定工作表默认的第一个
        totalRows = sheet.getPhysicalNumberOfRows();

        List<String> list = new ArrayList<>();

        for (int i = 1; i < totalRows; i++) {
            Row row = sheet.getRow(i);  //获取第i行
            for (int j = 0; j < 5; j++) { // 第j个单元格
                Cell cell = row.getCell(j); // 读单元格
                if (cell == null) { // 空判断
                    list.add("");
                } else {
                    cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                    list.add(cell.getStringCellValue());
                }
            }
        }
        return list;
    }


    public List<String> readAdmissionPolicy(InputStream inputStream) throws IOException {

        Workbook wb = new XSSFWorkbook(inputStream);
        Sheet sheet = wb.getSheetAt(0);// 获取文件的指定工作表默认的第一个
        totalRows = sheet.getPhysicalNumberOfRows();

        List<String> list = new ArrayList<>();

        for (int i = 1; i < totalRows; i++) {
            Row row = sheet.getRow(i);  //获取第i行
            for (int j = 0; j < 4; j++) { // 第j个单元格
                Cell cell = row.getCell(j); // 读单元格
                if (cell == null) { // 空判断
                    list.add("");
                } else {
                    cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                    list.add(cell.getStringCellValue());
                }
            }
        }
        return list;
    }

    /**
     * <li>Description:[正确地处理整数后自动加零的情况]</li>
     *
     * @param sNum
     * @return
     */
    private String getRightStr(String sNum) {
        DecimalFormat decimalFormat = new DecimalFormat("#.000000");
        String resultStr = decimalFormat.format(new Double(sNum));
        if (resultStr.matches("^[-+]?\\d+\\.[0]+$")) {
            resultStr = resultStr.substring(0, resultStr.indexOf("."));
        }
        return resultStr;
    }
}

