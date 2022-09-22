package com.gaminglounge;

import io.CsvHandler;
import util.StringHandler;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
//import java.util.Date;

public class PlaySession implements java.io.Serializable{
    Scanner scanner = new Scanner(System.in);
    CsvHandler csvHandler;
    private String firstName;
    private String lastName;
    private String code;

    private String game;
    private int pricePaid;
    public LocalDate date;
    public LocalTime startHour;
    public int duration;

    public Boolean isFinished = false;

    public Station station;

    private String[] dataRecord = new String[8];



    public PlaySession(Station station, String game, LocalTime startHour, String duration) {
        this.station = station;
        this.game = game;
        this.pricePaid = setPricePaid(duration);
        this.date = LocalDate.now();
        this.duration = setDuration(duration);
        this.setFirstName();
        this.setLastName();
        this.startOnTime(startHour);
        this.concatenateData();
    }

//    public int getPricePaid() {
//        if(duration != 0){
//            return pricePaid * duration;
//        } else {
//            throw new NoSuchElementException("Duration value in not set");
//        }
//    }

    public int setDuration(String durationStr) {
        final Map<String, Integer> MAP = new HashMap<String, Integer>(Map.ofEntries(
                Map.entry("1/2h", 30),
                Map.entry("1h", 60),
                Map.entry("2h", 120),
                Map.entry("5h", 300),
                Map.entry("9h", 540)
        ));
        return MAP.get(durationStr);
    }

    public int getPricePaid() {
        System.out.println("duraiton type :" + this.duration);
        if(this.duration != 0 && this.pricePaid == 0){
            System.err.println("Something went wrong calculating paid amount");
        }
        //use switch case to give price reductions
        return this.pricePaid;
    }
    public int setPricePaid(String durationStr) {
        return LoungeConstants.DURATIONS.get(durationStr);
    }

    public void setFirstName(){
        System.out.printf("Enter First Name N° :  \n");
        this.firstName = scanner.next();
    }
    public void setLastName(){
        System.out.printf("Enter Last Name N° :  \n");
        this.lastName = scanner.next();
    }

    private void concatenateData() {
        //add all data into an array
//        LocalTime startHourNoMin = this.startHour.truncatedTo(ChronoUnit.MINUTES);
//        String formattedStartHour = startHourNoMin.toString();
        String startHourStr = this.startHour.toString();
        String formattedDate = this.date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        StringHandler.addAll(this.dataRecord, this.firstName, this.lastName, Integer.toString(this.station.stationNum), this.game, startHourStr, Integer.toString(this.duration), Integer.toString(pricePaid), formattedDate);
    }

    public void saveSession(String fileName){
        //concatenate array elements into one single string with ',' delimiter
        String dataRecordStr = String.join(",", this.dataRecord) + ";";
        this.csvHandler = new CsvHandler(fileName);
        //write data to file
        this.csvHandler.writeToFile(dataRecordStr);
    }

    private void startOnTime(LocalTime startHour) {
        this.startHour = startHour;
            //Time the function execution
        // after terminate the session
        this.terminateSession();
    }

    public void terminateSession(){
        this.isFinished = true;
        //turn off the station
        this.station.turnOff();
        //then remove session from array of ACTIVE SESSIONS

    }
}
