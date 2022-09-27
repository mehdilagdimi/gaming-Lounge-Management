package com.gaminglounge;

import java.io.Serializable;

public class Console  implements Serializable {
    public String console;
    public int units;

    public Console(String console, int units){
        this.console = console;
        this.units = units;
    }

    public String getConsole() {
        return this.console;
    }

    public Boolean isTurnedOn = false;


}
