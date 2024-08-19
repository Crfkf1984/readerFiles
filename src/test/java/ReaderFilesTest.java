import dev.failsafe.internal.util.Assert;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ReaderFilesTest {

    private final ClassLoader classLoader = ReaderFilesTest.class.getClassLoader();

    @Test
    public void readerPdfAndPackInZipTest() {

    }

    @Test
    public void readerXlsAndPackInZipTest() {

    }

    @Test
    public void readerCsvAndPackInZipTest() throws Exception {
        ZipEntry entry = new ZipEntry("folders/findByCountForAllureOrJunitTest.csv");
        ZipEntry entryRead;
        try (ZipOutputStream zip = new ZipOutputStream(new FileOutputStream("src/test/resources/foldersZip/csv.zip"));
             FileInputStream fis= new FileInputStream(("src/test/resources/folders/findByCountForAllureOrJunitTest.csv"));) {
            zip.putNextEntry(entry);
            byte[] buffer = new byte[fis.available()];
            zip.write(buffer);
            zip.closeEntry();
        }

        try (ZipInputStream zipread = new ZipInputStream(classLoader.getResourceAsStream("foldersZip/csv.zip"));) {
            String nameFile = null;
            while ((entryRead = zipread.getNextEntry()) != null) {
                 nameFile = entryRead.getName();
                zipread.closeEntry();
            }
            Assertions.assertEquals(nameFile, "folders/findByCountForAllureOrJunitTest.csv");

        }
    }

    @Test
    public void readerJsonTest() {

    }

    @Test
    public void readerJsonAndParsToClassTest() {

    }
}