package com.gaminglounge;

public class Station {
    public final int stationNum;
    public String gamingConsole;
    public Boolean isTurnedOn;
    public Boolean isOccupied;

    public Station(int stationNum, String gamingConsole) {
        this.stationNum = stationNum;
        this.gamingConsole = gamingConsole;
    }

    public void turnOn(){
        isTurnedOn = true;
    }
    public void occupy(){
        isOccupied = true;
    }
    public void turnOff(){
        isTurnedOn = false;
    }
}
