import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class ReadXlsxFile {
    public static void main(String[] args) throws IOException {
        try {
            //obtaining input bytes from a file
            FileInputStream fis = new FileInputStream(new File("I:\\KZS 01.09.2021_0.xlsx"));
            //creating workbook instance that refers to .xls file
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            //creating a Sheet object to retrieve the object
            XSSFSheet sheet = wb.getSheetAt(0);
            //iterating over excel file
            for (Row row : sheet) {
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case STRING:
                            System.out.print(cell.getRichStringCellValue() + "\t\t\t");
                            break;
                        case NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "\t\t\t");
                            break;
                        default:
                    }
                }
                System.out.println("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
