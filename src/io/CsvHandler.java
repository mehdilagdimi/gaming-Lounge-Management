package io;

import com.gaminglounge.PlaySession;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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
//            if (!this.file.isFile()) {
                this.csvFile = new FileWriter(fileName, true);
//            }
//            else {
//                this.csvFile = new FileWriter(fileName, true);
//            }

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
    public void readFromFile() {
        try {
            Scanner sc = new Scanner(new File(this.fileName));
            //parsing a CSV file into the constructor of Scanner class
            sc.useDelimiter(",");
            //setting comma as delimiter pattern
            while (sc.hasNext()) {
                System.out.println("DATA : " + sc.next());
            }
            sc.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
    public void readFromFileByMonth(String fileName) {
        //
    }
    public void readFromFileByDay(String fileName) {
        //
    }
}
