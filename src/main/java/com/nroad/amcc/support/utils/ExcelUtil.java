package com.nroad.amcc.support.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
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
            if (row == null) {
                continue;
            }

            for (int j = 0; j < 3; j++) {
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
}

