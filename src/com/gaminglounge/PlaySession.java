package com.gaminglounge;

import io.AppStateManager;
import io.CsvHandler;
import util.StringHandler;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.io.Serializable;

public class PlaySession implements Serializable{
    transient Scanner scanner = new Scanner(System.in);
    transient CsvHandler csvHandler;
    private String firstName;
    private String lastName;
    private String code;

    private Game game;
    private int pricePaid;
    public LocalDate date;
    public LocalTime startHour;
    public int duration;

    public Boolean isFinished = false;

    public Station station;

    private String[] dataRecord = new String[8];



    public PlaySession(Station station, Game game, String duration) {
        this.station = station;
        //occupy station
        this.station.occupy();
        this.game = game;
        this.pricePaid = setPricePaid(duration);
        this.date = LocalDate.now();
        this.duration = setDuration(duration);
        this.setFirstName();
        this.setLastName();
        this.startOnTime();
        this.concatenateData();
    }


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
        this.firstName = scanner.nextLine();
    }
    public void setLastName(){
        System.out.printf("Enter Last Name N° :  \n");
        this.lastName = scanner.nextLine();
    }

    private void concatenateData() {
        //add all data into an array
        String startHourStr = this.startHour.toString();
        String formattedDate = this.date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        StringHandler.addAll(this.dataRecord, this.firstName, this.lastName, Integer.toString(this.station.stationNum), this.game.getGameName(), startHourStr, Integer.toString(this.duration), Integer.toString(pricePaid), formattedDate);
    }

    public void saveSession(String fileName){
        //concatenate array elements into one single string with ',' delimiter
        String dataRecordStr = String.join(",", this.dataRecord) + ";";
        this.csvHandler = new CsvHandler(fileName);
        //write data to file
        this.csvHandler.writeToFile(dataRecordStr);
    }
    public void readSession(String fileName){
        //concatenate array elements into one single string with ',' delimiter
        this.csvHandler = new CsvHandler(fileName);

        //write data to file
        this.csvHandler.readFromFile();
    }

    private void startOnTime() {
        this.startHour = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
        //Time the function execution
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                terminateSession();
            };
        };
        //convert minutes to seconds by *60
        timer.schedule(task, this.duration * 60);
        // after terminate the session
        this.terminateSession();
    }

    public void terminateSession(){
        this.isFinished = true;
        //wait 20 seconds before turning it off

        //turn off the station
        this.station.turnOff();

        //then remove current session object from array of ACTIVE SESSIONS
        AppStateManager.WaitingSessions.remove(this);
        //

    }
}
