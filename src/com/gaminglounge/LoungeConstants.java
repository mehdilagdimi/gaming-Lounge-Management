package com.gaminglounge;

import java.lang.reflect.Array;
import java.util.ArrayList;
//import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public final class LoungeConstants {
    private static final String DATA_FILE = "Gaming_Lounge_Data.txt";
    private static final String DATA_SERIALIZE_FILE = "Gaming_Lounge.ser";
    public static final int NUM_STATIONS = 9;
    public static final int ACTIVE_CAPACITY = 9;
    public static final int WAITING_CAPACITY = 9; //Make it 8 just to in case a station is empty but no ones chooses it in the waiting list

    //    public static final String[] consoles = {"PS5", "PS5", "XBOX", "Xbox","Switch", "Switch"};

    public static final Map<String, Integer> CONSOLES;
    public static final Map<String, Integer> SCREENS;
    public static Map<Integer, String> STATIONS;
    public static Map<String, Integer> DURATIONS;
    public static Map<String, ArrayList<String>> GAMES;

    public static short[] prices = {5, 10, 18, 40, 65};

    private LoungeConstants() {
        throw new AssertionError();
    } // make constructor private to prevent instantiation

    public static String getDataFile(){
        return DATA_FILE;
    }

    static {
        CONSOLES = new TreeMap<String, Integer>(Map.ofEntries(
                Map.entry("PS5", 3),
                Map.entry("XBOX", 4),
                Map.entry("SWITCH", 2)
        ));

        SCREENS = new TreeMap<String, Integer>(Map.ofEntries(
                Map.entry("DELL", 3),
                Map.entry("HP", 1),
                Map.entry("ASUS", 3),
                Map.entry("SAMSUNG", 2)
        ));

        ArrayList<String> football = new ArrayList<String>(){
            {
                add("FIFA");
                add("PES");
            }
        };

        ArrayList<String> war = new ArrayList<String>(){
            {
                add("Counter-Strike");
                add("Assassin's Creed");
            }
        };
        GAMES = new TreeMap<String, ArrayList<String>>(Map.ofEntries(
                Map.entry("WAR", war),
                Map.entry("FOOTBALL", football)
        ));
        DURATIONS = new TreeMap<String, Integer>(Map.ofEntries(
                Map.entry("1/2h", 5),
                Map.entry("1h", 10),
                Map.entry("2h", 18),
                Map.entry("5h", 40),
                Map.entry("9h", 65)
        ));

        STATIONS = new TreeMap<Integer, String>();
        int j = -1;
        for (String screen : SCREENS.keySet()) {
//            System.out.println("\nscreen in cstsn : " + screen + ", num of screens : " + SCREENS.get(screen));
            int numScreens = SCREENS.get(screen);
//            j = k;
//            int k = j + numScreens;
            int k = j + 1;
            for (; j + 1 < k + numScreens; j++) {
                STATIONS.put(j + 1, screen);
            }
//            k = j;
        }

    }
}