package guru.qa.files.utils;

import org.junit.jupiter.api.Assertions;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Utils {

    public File extractFileFromZipByExtension(String zipFilePath, String outputDirectoryPath, String fileExtension) throws IOException {
        System.out.println("Current working directory: " + System.getProperty("user.dir"));
        File outputDirectory = new File(outputDirectoryPath);
        outputDirectory.mkdirs();

        try (FileInputStream fis = new FileInputStream(zipFilePath);
             ZipInputStream zis = new ZipInputStream(fis)) {

            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                String fileName = entry.getName();
                if (fileName.toLowerCase().endsWith(fileExtension.toLowerCase())) {
                    File unzippedFile = new File(outputDirectoryPath + File.separator + fileName);
                    try (FileOutputStream output = new FileOutputStream(unzippedFile)) {
                        byte[] buffer = new byte[512];
                        int length;
                        while ((length = zis.read(buffer)) > 0) {
                            output.write(buffer, 0, length);
                        }
                        return unzippedFile;
                    }
                }
            }
        }
        Assertions.fail("No file with extension " + fileExtension + " found in the zip.");
        return null;
    }

    public void deleteFile(File file) {
        file.delete();
    }

}

