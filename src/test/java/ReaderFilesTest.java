import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import entity.Menu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ReaderFilesTest {

    private final ClassLoader classLoader = ReaderFilesTest.class.getClassLoader();
    private static final ObjectMapper json = new ObjectMapper();

    @Test
    public void readerPdfTest() throws Exception {

        ZipEntry entryRead;
        try (ZipInputStream zipread = new ZipInputStream(new FileInputStream("src/test/resources/top_secrets.zip"));) {
            String expected = "top_secrets/Test_2.pdf";
            while ((entryRead = zipread.getNextEntry()) != null) {
                String nameFile = entryRead.getName();
                if (!nameFile.equals(expected)) {
                    continue;
                }
                PDF pdf = new PDF(zipread);
                zipread.closeEntry();
                Assertions.assertEquals(nameFile, expected);
                Assertions.assertEquals("Евгений Зуев", pdf.author);
            }
        }

    }

    @Test
    public void readerXlsTest() throws Exception {

        ZipEntry entryRead;
        try (ZipInputStream zipread = new ZipInputStream(new FileInputStream("src/test/resources/top_secrets.zip"));) {
            String expected = "top_secrets/Test_3.xls";
            while ((entryRead = zipread.getNextEntry()) != null) {
                String nameFile = entryRead.getName();
                if (!nameFile.equals(expected)) {
                    continue;
                }

                XLS xls = new XLS(zipread);
                String cellValue = xls.excel.getSheetAt(0).getRow(5).getCell(0).getStringCellValue();
                zipread.closeEntry();
                Assertions.assertEquals(nameFile, expected);
                Assertions.assertEquals("Важный тест!", cellValue);
            }
        }
    }

    @Test
    public void readerCsvZipTest() throws Exception {

        ZipEntry entryRead;
        try (ZipInputStream zipread = new ZipInputStream(new FileInputStream("src/test/resources/top_secrets.zip"));) {
            String expected = "top_secrets/Test_1.csv";
            while ((entryRead = zipread.getNextEntry()) != null) {
                String nameFile = entryRead.getName();
                if (!nameFile.equals(expected)) {
                    continue;
                }

                CSVReader reader = new CSVReader(new InputStreamReader(zipread));
                List<String[]> res = reader.readAll();
                zipread.closeEntry();
                Assertions.assertEquals(nameFile, expected);
                Assertions.assertArrayEquals(new String[]{"Allure", "https://habr.com"}, res.get(0));
                Assertions.assertArrayEquals(new String[]{"Junit 5", "https://junit.org/junit5/"}, res.get(1));
            }
        }
    }

    @Test
    public void readerJsonAndParsToClassTest() throws Exception {
        try (Reader reader = new InputStreamReader(classLoader.getResourceAsStream("jsonTest.json"));) {
            Menu menu = json.readValue(reader, Menu.class);
            Assertions.assertEquals("Ligth", menu.getBear());
            Assertions.assertEquals("Cool", menu.getBrendi());
        }

    }
}