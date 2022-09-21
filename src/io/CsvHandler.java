package io;

import java.io.FileWriter;
import java.io.IOException;

public class CsvHandler {

    FileWriter csvFile;
    String fileName;
    String filePath;
    CsvHandler(String fileName) {
        this.fileName = fileName;
        this.createFile(fileName);
    }

    private void createFile(String fileName){
        try{
            this.csvFile = new FileWriter(fileName);

        } catch(IOException e){
            System.out.println("Error occured");
            e.printStackTrace();
        }
    }

    public void writeToFile(String data){
        try{
            this.csvFile.write(data);
            this.csvFile.close();
        } catch(IOException e){
            System.out.println("Error occured");
            e.printStackTrace();
        }

    }
}
