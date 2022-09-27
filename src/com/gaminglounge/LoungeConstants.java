package com.gaminglounge;

import java.lang.reflect.Array;
import java.util.*;
import java.util.Arrays;
//import java.util.HashMap;


public final class LoungeConstants {
    private static final String DATA_FILE = "Gaming_Lounge_Data.txt";
//    private static final String DATA_SERIALIZE_FILE = "C:\\Users\\Youcode\\Documents\\GitHub\\JAVA Angular\\Gaming Lounge Management\\tmp.txt";
    private static final String SESSION_SERIALIZE_FILE = "C:\\Users\\Youcode\\Documents\\GitHub\\JAVA Angular\\Gaming Lounge Management\\Session_tmp.ser";
    private static final String STATION_SERIALIZE_FILE = "C:\\Users\\Youcode\\Documents\\GitHub\\JAVA Angular\\Gaming Lounge Management\\Station_tmp.ser";
    public static final int NUM_STATIONS = 9;
    public static final int ACTIVE_CAPACITY = 9;
    public static final int WAITING_CAPACITY = 9; //Make it 8 just to in case a station is empty but no ones chooses it in the waiting list

    //    public static final String[] consoles = {"PS5", "PS5", "XBOX", "Xbox","Switch", "Switch"};

    //Queue

    public static final Map<String, Integer> CONSOLES;
    public static final Map<String, Integer> SCREENS;
    public static Map<Integer, Station> STATIONS;
    public static Map<String, Integer> DURATIONS;
    public static Map<String, List<Game>> GAMES;

    public static short[] prices = {5, 10, 18, 40, 65};


    //initialize consoles as objects
    public static Console PS5 = new Console("PS5", 3);
    public static Console XBOX = new Console("XBOX", 4);
    public static Console SWITCH = new Console("SWITCH", 2);


    //initialize consoles as objects
    public static Station ASUS0 = new Station("ASUS");
    public static Station ASUS1 = new Station("ASUS");
    public static Station ASUS2 = new Station("ASUS");
    public static Station DELL0 = new Station("DELL");
    public static Station DELL1 = new Station("DELL");
    public static Station DELL2 = new Station("DELL");
    public static Station SAMSUNG0 = new Station("SAMSUNG");
    public static Station SAMSUNG1 = new Station("SAMSUNG");
    public static Station HP0 = new Station("HP");



    //initialize games
    public static Game FIFA = new Game("FIFA", Arrays.asList(PS5, XBOX));
    public static Game ASSASSINS_CREED = new Game("Assassin's Creed",  Arrays.asList(PS5, XBOX));
    public static Game PES  = new Game("PES",  Arrays.asList(PS5));
    public static Game COUNTER_STRIKE  = new Game("Counter-Strike", Arrays.asList(XBOX));
    public static Game MARIO_KART  = new Game("Mario Kart", Arrays.asList(SWITCH));



    //Constructor
    private LoungeConstants() {
        throw new AssertionError();
    } // make constructor private to prevent instantiation


    //Methods
    public static String getDataFile(){
        return DATA_FILE;
    }
    public static String getSessionSerializeFile(){
        return SESSION_SERIALIZE_FILE;
    }
    public static String getStationSerializeFile(){
        return STATION_SERIALIZE_FILE;
    }


    //USING MAPS FOR MANAGING GAMES, SCREENS and STATIONS
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

        List<Game> football = new ArrayList<Game>(){
            {
                add(FIFA);
                add(PES);
            }
        };

        List<Game> war = new ArrayList<Game>(){
            {
                add(COUNTER_STRIKE);
                add(ASSASSINS_CREED);
                add(MARIO_KART);
            }
        };

        GAMES = new TreeMap<String, List<Game>>(Map.ofEntries(
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

        //Keys are station numbers and values are stations
        STATIONS = new TreeMap<Integer, Station>(Map.ofEntries(
                Map.entry(0, ASUS0),
                Map.entry(1, ASUS1),
                Map.entry(2, ASUS2),
                Map.entry(3, DELL0),
                Map.entry(4, DELL1),
                Map.entry(5, DELL2),
                Map.entry(6, SAMSUNG0),
                Map.entry(7, SAMSUNG1),
                Map.entry(8, HP0)
        ));


//        STATIONS = new TreeMap<Integer, String>();
//        int j = -1;
//        for (String screen : SCREENS.keySet()) {
////            System.out.println("\nscreen in cstsn : " + screen + ", num of screens : " + SCREENS.get(screen));
//            int numScreens = SCREENS.get(screen);
////            j = k;
////            int k = j + numScreens;
//            int k = j + 1;
//            for (; j + 1 < k + numScreens; j++) {
//                STATIONS.put(j + 1, screen);
//            }
////            k = j;
//        }

    }
}