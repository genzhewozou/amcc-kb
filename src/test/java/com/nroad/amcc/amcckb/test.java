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
import java.util.Random;

public class test {
    private static int totalRows = 0;

    @Test
    public void tests() throws IOException {
        Workbook wb = new XSSFWorkbook(new BufferedInputStream(new FileInputStream(new File("D:\\data\\专业历史分数线(往三届).xlsx"))));
        Sheet sheet = wb.getSheetAt(0);// 获取文件的指定工作表默认的第一个
        totalRows = sheet.getPhysicalNumberOfRows();

        List<String> list = new ArrayList<>();

        for (int i = 1; i < totalRows; i++) {
            Row row = sheet.getRow(i);  //获取第i行
            for (int j = 0; j < 10; j++) { // 第j个单元格
                Cell cell = row.getCell(j); // 读单元格

                if (cell == null) { // 空判断
                    list.add(" ");
                    continue;
                }else {
                    cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                    list.add(cell.getStringCellValue());
                }

            }
        }

        System.out.println(list.get(9));
    }

    @Test
    public void randomm() {
        String password = "12345678";
        int[] pwdindex = {0, 1, 2, 3, 4, 5, 6, 7};

        char[] numbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        char[] upperLetters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                'U', 'V', 'W', 'X', 'Y', 'Z'};

        char[] lowerLetters = {'a', 'b', 'c', 'd',
                'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
                'y', 'z'};


        char[] allCharacters = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
                'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
                'y', 'z'};


        //1. 随机生成特殊字符，随机 放到密码2-7位置中(特殊字符不出现在开头或结尾)
        //System.out.println("随机生成特殊字符，随机 放到密码2-7位置中");
        int aindex = new Random().nextInt(5);
        //System.out.println(specialCharacters[aindex]);
        int aposition = new Random().nextInt(6) + 1;
        //System.out.println(password.charAt(aposition));
        password = password.replace(password.charAt(aposition), allCharacters[aindex]);
        //System.out.println(password);

        //2. 随机生成数据，随机放到1-8位置中（除去第1步占用的位置）
        int bindex = new Random().nextInt(10);
//System.out.println(numbers[bindex]);
        int bposition = 0;
        do {
            bposition = new Random().nextInt(8);
        } while (bposition == aposition);
        //System.out.println(password.charAt(bposition));
        password = password.replace(password.charAt(bposition), numbers[bindex]);
        //System.out.println(password);

//3. 随机生成大写字母，随机放到1-8位置中（除去第1、2步占用的位置）
        int cindex = new Random().nextInt(26);
//System.out.println(upperLetters[cindex]);
        int cposition = 0;
        do {
            cposition = new Random().nextInt(8);
        } while (cposition == aposition || cposition == bposition);
        //System.out.println(password.charAt(bposition));
        password = password.replace(password.charAt(cposition), upperLetters[cindex]);
// System.out.println(password);

//4. 随机生成小写字母，随机放到1-8位置中（除去第1、2、3步占用的位置）
        int dindex = new Random().nextInt(26);
//System.out.println(lowerLetters[dindex]);
        int dposition = 0;
        do {
            dposition = new Random().nextInt(8);
        } while (dposition == aposition || dposition == cposition || dposition == bposition);
        //System.out.println(password.charAt(bposition));
        password = password.replace(password.charAt(dposition), lowerLetters[dindex]);
        //System.out.println(password);

//前4步保证密码包含（特殊字符&大写字母&小写字母&数字  且位置是随机的）
//5. 随机生成数字大小写字母，随机放到1-8位置中（除去第1、2、3、4步占用的位置，余下四位）
        for (int i = 0; i < pwdindex.length; i++) {
            if (pwdindex[i] != aposition && pwdindex[i] != bposition
                    && pwdindex[i] != cposition && pwdindex[i] != dposition) {
                int eindex = new Random().nextInt(62);
                password = password.replace(password.charAt(pwdindex[i]), allCharacters[eindex]);
            }
        }
        System.out.println(password);
    }
}