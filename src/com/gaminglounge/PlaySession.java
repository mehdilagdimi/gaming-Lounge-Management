package com.gaminglounge;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
//import java.util.Date;

public class PlaySession {
    Scanner scanner = new Scanner(System.in);
    private String firstName;
    private String lastName;
    private String code;
    private int pricePaid;
    public LocalDate date;
    public LocalTime startHour;
    public int duration;

    public Boolean isFinished = false;

    public Station station;

    public PlaySession(Station station, LocalTime startHour, String duration) {
        this.station = station;
//        this.pricePaid = setPricePaid(duration);
        this.date = LocalDate.now();
        this.duration = setDuration(duration);
        this.setFirstName();
        this.setLastName();
        this.startOnTime(startHour);
    }

//    public int getPricePaid() {
//        if(duration != 0){
//            return pricePaid * duration;
//        } else {
//            throw new NoSuchElementException("Duration value in not set");
//        }
//    }

    public int setDuration(String durationStr) {
        int duration;
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
        if(duration != 0 && pricePaid == 0){
            System.err.println("Something went wrong calculating paid amount");
        }
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

    private void startOnTime(LocalTime startHour) {
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
