package tests.files;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.apache.commons.io.input.BOMInputStream;
import org.apache.xmlbeans.impl.common.IOUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.testdata.TestData;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import tools.jackson.databind.node.ArrayNode;
import utils.ZipHelper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;


public class FirstParsingTest {
    private static final ObjectMapper mapper = new ObjectMapper();
    TestData testData = new TestData();

    @Test
    @DisplayName("Чтение и проверка содержимого excel файла")
    void zipFileParsingTest() throws Exception {
        byte[] bytes = ZipHelper.extractFromZip("1.xlsx");
        XLS xls = new XLS(new ByteArrayInputStream(bytes));

        String actual = xls.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue();

        Assertions.assertEquals(testData.contentExcelFile, actual);

    }

    @Test
    @DisplayName("Чтение и проверка содержимого PDF файла")
    void PdfFileParsingTest() throws Exception {
        byte[] bytes = ZipHelper.extractFromZip("alfacard.pdf");
        PDF pdf = new PDF(new ByteArrayInputStream(bytes));

        String actual = pdf.creator;

        Assertions.assertEquals(testData.contentPdfFile, actual);

    }

    @Test
    @DisplayName("Чтение и проверка содержимого CSV файла")
    void CsvFileParsingTest() throws Exception {
        byte[] bytes = ZipHelper.extractFromZip("2.csv");

        BOMInputStream bomIn = new BOMInputStream(new ByteArrayInputStream(bytes));
        InputStreamReader reader = new InputStreamReader(bomIn, StandardCharsets.UTF_8);
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> actual = csvReader.readAll();

        Assertions.assertEquals(6, actual.size());
        Assertions.assertArrayEquals(
                new String[]{"Италия", " Итальянское вино"},
                actual.get(0)
        );

    }

    @Test
    @DisplayName("Читаем JSON-файл")
    void jsonFileParsingTest() throws Exception {
        try (InputStream is = getClass().getResourceAsStream("/test.json")) {
            JsonNode root = mapper.readTree(is);
            JsonNode array = root.get("array");
            String string = root.get("string").asString();
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
