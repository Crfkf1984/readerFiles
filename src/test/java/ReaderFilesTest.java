import dev.failsafe.internal.util.Assert;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
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
    public void readerCsvZipTest() throws Exception {

        ZipEntry entryRead;
        try (ZipInputStream zipread = new ZipInputStream(new FileInputStream("src/test/resources/top_secrets.zip"));) {
            String expected = "top_secrets/Test_1.zip";
            while ((entryRead = zipread.getNextEntry()) != null) {
                String nameFile = entryRead.getName();
                if (!nameFile.equals(expected)) {
                    continue;
                }
                zipread.closeEntry();
                Assertions.assertEquals(nameFile, expected);
            }
        }
    }


        @Test
        public void readerJsonTest () {

        }

        @Test
        public void readerJsonAndParsToClassTest () {

        }
    }