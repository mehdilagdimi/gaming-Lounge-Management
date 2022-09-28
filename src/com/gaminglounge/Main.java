package com.gaminglounge;

import io.AppStateManager;
import io.MenuDisplay;
import util.TimerHelper;
import util.interfaces.GenericGetGameNameInterface;
import util.interfaces.GetGameConsolesInterface;
import util.interfaces.GetScreenFromStation;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //static array for active sessions, no need for queue because order doesn't matter
//        PlaySession[] ActiveSessions = new PlaySession[LoungeConstants.ACTIVE_CAPACITY];

//        //queue for active sessions
////        Queue<PlaySession> queueActiveSessions = new Queue<PlaySession>(ActiveSessions);
//
//        //queues for waiting player
//        PlaySession[] WaitingSessions = new PlaySession[LoungeConstants.WAITING_CAPACITY];
//        Queue<PlaySession> queueWaitingSessions = new Queue<PlaySession>(WaitingSessions);
//        //Queues
//        Queue<PlaySession> queueOnHoldSessions = new Queue<PlaySession>(WaitingSessions);



        //number of active clients
        int numOfClients = 0;

        int totalRevenue = 0;
        int menuOption;
        int stationNum;

        String start;
        String console, genre;
        String startTime;
        String duration;

        Game game;

        boolean repeat = true;
        boolean chooseFromOccupied = false;


        Scanner input = new Scanner(System.in);

        //lambda functions for getting property of objects when calling static generic methods
        GetScreenFromStation<String, Station> lambdaFuncGetScreen = (stationObj) -> stationObj.getScreen();


        //use serialization of objects to save the state of the app
        System.out.println("Welcome to The Gaming Lounge Platform!");
        while (repeat) {
            System.out.println("Add a new session? : Y/N");
            start = input.next();
            if (start.compareToIgnoreCase("Y") == 0) {

                if(!AppStateManager.ActiveSessions.isEmpty()){
                    //DISPLAY OCCUPIED STATIONS
                    for(PlaySession session : AppStateManager.ActiveSessions){
                        System.out.printf("\t--- STATIONS OCCUPIED---\n");
                        System.out.printf("Station N° \t | \tTV Screen \t | \tConsole \n");
                        System.out.printf("%d \t\t | \t\t %s \t\t | \t\t %s \n", session.station.stationNum, session.station.getScreen(), session.station.console);
                    }

                    //DISPLAY UNOCCUPIED STATIONS
                    System.out.printf("\t--- STATIONS AVAILABLE---\n");
                    System.out.printf("\t--- Stations ---\n");
                    System.out.printf("Station N° \t | \tTV Screen \n");
                    MenuDisplay.<Integer, Station>displayObjValuesArr(LoungeConstants.STATIONS, lambdaFuncGetScreen);

                    //CHOOSE FROM OCCUPIED OR NOT STATIONS
                    System.out.println("Choose from available posts or occupied ones? : Y for available /N for occupied");
                    start = input.next();

                } else { start = "Y";}

                if (start.compareToIgnoreCase("Y") == 0) {
                    chooseFromOccupied = false;
                    //CHOOSE STATION
                    System.out.printf("Choose station N° :  \n");
                    System.out.printf("\t--- Stations ---\n");
                    System.out.printf("Station N° \t | \tTV Screen \n");
                    MenuDisplay.<Integer, Station>displayObjValuesArr(LoungeConstants.STATIONS, lambdaFuncGetScreen);
                } else {
                    chooseFromOccupied = true;
                    for(PlaySession session : AppStateManager.ActiveSessions){
                            System.out.printf("\t--- STATIONS OCCUPIED---\n");
                            System.out.printf("Station N° \t | \tTV Screen \t | \tConsole \n");
                            System.out.printf("%d \t\t | \t\t %s \t\t | \t\t %s \n", session.station.stationNum, session.station.getScreen(), session.station.console);
                        }
                    }
                }

                    menuOption = input.nextInt();
                    stationNum = LoungeConstants.STATIONS.keySet().toArray(new Integer[0])[menuOption];

                    //GENRE OF GAME
                    System.out.println("Choose genre N° : ");
                    MenuDisplay.<String, List<Game>>displayKeySetArr(LoungeConstants.GAMES, () -> new String[0]);
                    menuOption = input.nextInt();
                    genre = MenuDisplay.<String, List<Game>>getChoiceValueKeySet(LoungeConstants.GAMES, menuOption, () -> new String[0]);


                    //CHOOSE GAME
                    System.out.println("Choose game N° : ");
                    //Defining lambda function to pass as a param in MenuDisplay so to display each game name property
                    GenericGetGameNameInterface<Game, Game> lambdaGameNameFunc = (gameObj) -> gameObj.getGame();
                    MenuDisplay.<Game, Game>displayValuesArr(LoungeConstants.GAMES.get(genre), lambdaGameNameFunc);
                    menuOption = input.nextInt();
                    game = MenuDisplay.<Game, Game>getChoiceValue(LoungeConstants.GAMES.get(genre), menuOption, lambdaGameNameFunc);


                    //CHOOSE CONSOLE
                    System.out.printf("Choose console N° :  \n");
                    GetGameConsolesInterface<Console, Game> lambdaGameConsoles = (gameObj) -> gameObj.getAvailableOn();
                    MenuDisplay.<Console, Game>displayConsoles(game, menuOption, lambdaGameConsoles);

                    menuOption = input.nextInt();
                    console = MenuDisplay.getChoiceValueKeySet(LoungeConstants.CONSOLES, menuOption, () -> new String[0]);
                    LoungeConstants.STATIONS.get(stationNum).setConsole(console);

                    // CHOOSE DURATION
                    MenuDisplay.displayDurations(LoungeConstants.DURATIONS);
                    menuOption = input.nextInt();
                    duration = LoungeConstants.DURATIONS.keySet().toArray(new String[0])[menuOption];


                    //set file path for serialization
                    AppStateManager.setPath(LoungeConstants.getStationSerializeFile());


                    //save/serialize created station object
                    AppStateManager.<Station>serialize(LoungeConstants.STATIONS.get(stationNum));


                    //create session object
                    PlaySession clientSession = new PlaySession(LoungeConstants.STATIONS.get(stationNum), game, duration);
                    numOfClients++;


                    //ADD TO ACTIVE SESSIONS OR WAITING SESSIONS
                    if(chooseFromOccupied && AppStateManager.WaitingSessions.size() < LoungeConstants.WAITING_CAPACITY){
                        AppStateManager.WaitingSessions.add(clientSession);
                    } else if (!chooseFromOccupied && AppStateManager.ActiveSessions.size() < LoungeConstants.ACTIVE_CAPACITY){
                        AppStateManager.ActiveSessions.add(clientSession);
                    } else  {
                        System.out.println("!!!\tGaming Lounge is FULL\t!!!");
                        continue;
                    }


                    //set file path for serialization
                    AppStateManager.setPath(LoungeConstants.getSessionSerializeFile());
                    //save/serialize created station object
                    AppStateManager.<PlaySession>serialize(clientSession);


                    //save session to data file
                    clientSession.saveSession(LoungeConstants.getDataFile());
                    //read sessions from data file
                    clientSession.readSession(LoungeConstants.getDataFile());


                    //add price paid to total revenue and print it out
                    totalRevenue += clientSession.getPricePaid();
                    System.out.printf("Accumulated Revenue for the day : %dDH \n", totalRevenue);


            }
        }
    }
}
