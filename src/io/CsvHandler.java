package io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CsvHandler {

    FileWriter csvFile;
    String fileName;
    String filePath;

    File file;

    public CsvHandler(String fileName) {
        this.fileName = fileName;
        this.createFile(fileName);
    }

    private void createFile(String fileName) {
        try {
            this.file = new File(fileName);
            if (!this.file.isFile()) {
                this.csvFile = new FileWriter(fileName);
            }

        } catch (IOException e) {
            System.out.println("Error occured");
            e.printStackTrace();
        }
    }

    public void writeToFile(String data) {
        try {
            this.csvFile.write(data);
            this.csvFile.close();
        } catch (IOException e) {
            System.out.println("Error occured");
            e.printStackTrace();
        }

    }
}
