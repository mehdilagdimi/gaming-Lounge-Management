package com.gaminglounge;

import java.util.ArrayList;

public class Game {
    public String game;
    ArrayList<Console> availableOn;

    public ArrayList<Console> getAvailableOn() {
        return availableOn;
    }

    public Game (String game, ArrayList<Console> availableOn) {
        this.game = game;
        this.availableOn = availableOn;
    }

    public String getGame() {
        return this.game;
    }
}
