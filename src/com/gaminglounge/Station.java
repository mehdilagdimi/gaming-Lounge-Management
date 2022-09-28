package com.gaminglounge;

import java.io.Serializable;

public class Station implements Serializable {
    public int stationNum;
    public String screen;

    public String console;
    public Boolean isTurnedOn;

    public String getScreen() {
        return screen;
    }

    public Boolean isOccupied = false;

    public Station(String screen, int stationNum) {
        this.screen = screen;
        this.stationNum = stationNum;
    }

    public void setStationNum (String console) {
        this.stationNum = stationNum;
    }
    public void setConsole (String console) {

        this.console = console;
        //DECREASE NUMBER OF AVAILABLE CONSOLE
        int numOfAvailableConsoles = LoungeConstants.CONSOLES.get(console);
        LoungeConstants.CONSOLES.put(console, numOfAvailableConsoles - 1);
    }

    public void turnOn(){
        isTurnedOn = true;
    }
    public void occupy(){
        isOccupied = true;
        this.turnOn();
    }
    public void turnOff(){
        //SET A COUNT DOWN before turning the station off
        isOccupied = false;
        isTurnedOn = false;
    }
}
