package tests.files;

import com.codeborne.xlstest.XLS;
import org.apache.xmlbeans.impl.common.IOUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import tools.jackson.databind.node.ArrayNode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;


public class FirstParsingTest {
    private ClassLoader cl = FirstParsingTest.class.getClassLoader();
    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("Чтение и проверка содержимого каждого файла из архива")
    void zipFileParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("Загрузки.zip")
        )) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                String actual = null;
                System.out.println(entry.getName());
                if (entry.isDirectory() || !entry.getName().toLowerCase().endsWith(".xlsx")) {
                    zis.closeEntry();
                    continue;
                }
                File tempFile = null;
                try {
                    //Создаем временный файл
                    tempFile = File.createTempFile("xls-", ".xls");
                    tempFile.deleteOnExit();
                    try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                        //копируем файл из архива в временный файл
                        IOUtils.copy(zis, fos);
                    }
                    try (FileInputStream fis = new FileInputStream(tempFile)) {
                        XLS xls = new XLS(fis);
                        actual = xls.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue();
                        System.out.println(actual);
                    }
                    zis.closeEntry();
                } finally {
                    if (tempFile != null && tempFile.exists()) {
                        tempFile.delete();
                    }
                }
               Assertions.assertEquals("x", actual);
            }
        }
    }

    @Test
    @DisplayName("Читаем JSON-файл")
    void jsonFileParsingTest() throws Exception {
        try (InputStream is = getClass().getResourceAsStream("/test.json")) {
            JsonNode root = mapper.readTree(is);
            JsonNode array = root.get("array");
            String string = root.get("string").asText();
            int number = root.get("number").asInt();
            int array_0 = array.get(0).asInt();
            int array_1 = array.get(1).asInt();
            int array_2 = array.get(2).asInt();

            Assertions.assertEquals("Hello World", string);
            Assertions.assertEquals(123, number);
            Assertions.assertEquals(1, array_0);
            Assertions.assertEquals(2, array_1);
            Assertions.assertEquals(3, array_2);
        }
    }
}
