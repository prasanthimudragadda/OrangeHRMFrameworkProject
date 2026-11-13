package utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtil {

    public static Object[][] getData(String filePath, String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        int rows = sheet.getLastRowNum();
        int columns = sheet.getRow(0).getLastCellNum();
        Object[][] data = new Object[rows][columns];

        DataFormatter formatter = new DataFormatter();
        for (int i = 1; i <= rows; i++) {
            for (int j = 0; j < columns; j++) {
                data[i - 1][j] = formatter.formatCellValue(sheet.getRow(i).getCell(j));
            }
        }
        workbook.close();
        fis.close();
        return data;

    }
}
