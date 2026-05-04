package utils;

import tests.files.FirstParsingTest;

import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipHelper {
    private static ClassLoader cl = FirstParsingTest.class.getClassLoader();//чтение файла из папки resources
    public static byte[] extractFromZip(String fileName) {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("Загрузки.zip")
        )) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals(fileName)) {
                    return zis.readAllBytes();
                }
                zis.closeEntry();
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Файл не найден в архиве: " + fileName);
        }
        return new byte[0];
    }
}
