package com.gaminglounge;

import java.io.Serializable;

public class Station implements Serializable {
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
        //SET A COUNT DOWN before turning the station off

        isTurnedOn = false;
    }
}
