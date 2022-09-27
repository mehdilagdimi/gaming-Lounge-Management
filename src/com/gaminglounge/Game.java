package com.gaminglounge;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Game implements Serializable {
    public String game;
    List<Console> availableOn;


    public Game (String game, List<Console> availableOn) {
        this.game = game;
        this.availableOn = availableOn;
    }

    public List<Console> getAvailableOn() {
        return availableOn;
    }

    public String getGameName() {
        return this.game;
    }
    public Game getGame() {
        return this;
    }
}
