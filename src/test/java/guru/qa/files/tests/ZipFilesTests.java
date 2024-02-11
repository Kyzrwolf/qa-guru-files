package guru.qa.files.tests;

import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import guru.qa.files.utils.Utils;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;


import com.codeborne.pdftest.PDF;

public class ZipFilesTests {
    private static final ClassLoader cl = ZipFilesTests.class.getClassLoader();
    static String zipArchivePath = "src/test/resources/files/documents.zip";
    static String outputDirectoryPath = "src/test/resources/unzippedFiles/";
    static Utils utils = new Utils();


    @Test
    void PDFTest() throws IOException {
        File pdfFile = utils.extractFileFromZipByExtension(zipArchivePath,outputDirectoryPath,".pdf");
        PDF pdf = new PDF(pdfFile);
        Assertions.assertTrue(pdf.text.contains("THE NATURAL HISTORY OF THE CAT"));
        utils.deleteFile(pdfFile);
    }

    @Test
    void XLSTest() throws IOException {
        File xlsFile = utils.extractFileFromZipByExtension(zipArchivePath,outputDirectoryPath,".xls");
        XLS xls = new XLS(xlsFile);
        Assertions.assertEquals(
                "ABBOTT, TONY (CATS)",
                xls.excel.getSheet("Authors-Illustrators")
                        .getRow(20)
                        .getCell(2)
                        .getStringCellValue());
        utils.deleteFile(xlsFile);
    }

    @Test
    void CSVTest() throws Exception {
        utils.extractFileFromZipByExtension(zipArchivePath,outputDirectoryPath,".csv");
        String csvFilePath = "unzippedFiles/cats.csv";
        try (InputStream is = cl.getResourceAsStream(csvFilePath)) {
            try (CSVReader csvReader = new CSVReader(new InputStreamReader(is))) {
               List<String[]> content = csvReader.readAll();
               SoftAssertions softly = new SoftAssertions();
               softly.assertThat(new String[] {"Owner","Name"}).isEqualTo(content.get(0));
               softly.assertThat(new String[] {"Jeff","Kitty"}).isEqualTo(content.get(1));
            }
        }
        utils.deleteFile(new File(csvFilePath));
    }
}
