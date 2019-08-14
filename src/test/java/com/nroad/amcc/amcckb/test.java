package com.nroad.amcc.amcckb;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class test {
//    private static int totalRows = 0;
//
//    @Test
//    public void tests() throws IOException {
//        Workbook wb = new XSSFWorkbook(new BufferedInputStream(new FileInputStream(new File("D:\\data\\专业历史分数线(往三届).xlsx"))));
//        Sheet sheet = wb.getSheetAt(0);// 获取文件的指定工作表默认的第一个
//        totalRows = sheet.getPhysicalNumberOfRows();
//
//        List<String> list = new ArrayList<>();
//
//        for (int i = 1; i < totalRows; i++) {
//            Row row = sheet.getRow(i);  //获取第i行
//            for (int j = 0; j < 10; j++) { // 第j个单元格
//                Cell cell = row.getCell(j); // 读单元格
//
//                if (cell == null) { // 空判断
//                    list.add(" ");
//                    continue;
//                }else {
//                    cell.setCellType(XSSFCell.CELL_TYPE_STRING);
//                    list.add(cell.getStringCellValue());
//                }
//
//            }
//        }
//
//        System.out.println(list.get(9));
//    }
}