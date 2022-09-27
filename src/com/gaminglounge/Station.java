package com.gaminglounge;

import java.io.Serializable;

public class Station implements Serializable {
    public int stationNum;
    public String screen;

    public String gamingConsole;
    public Boolean isTurnedOn;

    public String getScreen() {
        return screen;
    }

    public Boolean isOccupied;

    public Station(String screen) {
        this.screen = screen;
    }

    public void setStationNum (String console) {
        this.stationNum = stationNum;
    }
    public void setConsole (String console) {
        this.gamingConsole = console;
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
