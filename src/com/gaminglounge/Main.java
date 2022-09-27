package com.gaminglounge;

import io.AppStateManager;
import io.MenuDisplay;
import util.Timer;
import util.interfaces.GenericGetGameNameInterface;
import util.interfaces.GetGameConsolesInterface;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //static array for active sessions, no need for queue because order doesn't matter
        PlaySession[] ActiveSessions = new PlaySession[LoungeConstants.ACTIVE_CAPACITY];

//        //queue for active sessions
////        Queue<PlaySession> queueActiveSessions = new Queue<PlaySession>(ActiveSessions);
//
//        //queues for waiting player
//        PlaySession[] WaitingSessions = new PlaySession[LoungeConstants.WAITING_CAPACITY];
//        Queue<PlaySession> queueWaitingSessions = new Queue<PlaySession>(WaitingSessions);
//        //Queues
//        Queue<PlaySession> queueOnHoldSessions = new Queue<PlaySession>(WaitingSessions);

        //Queue
        Queue<PlaySession> WaitingSessions = new LinkedList<>();

        //number of active clients
        int numOfClients = 0;

        int totalRevenue = 0;
        int menuOption;
        int stationNum;

        String start;
        String console, genre, game;
        String startTime;
        String duration;

        boolean repeat = true;


        Scanner input = new Scanner(System.in);

        //use serialization of objects to save the state of the app
        System.out.println("Welcome to The Gaming Lounge Platform!");
        while (repeat) {
            System.out.println("Add a new session? : Y/N");
            start = input.next();
            if (start.compareToIgnoreCase("Y") == 0) {

                //CHOOSE STATION
                System.out.printf("Choose station N° :  \n");
                System.out.printf("\t--- Stations ---\n");
                System.out.printf("Station N° \t | \tTV Screen \n");
                MenuDisplay.<Integer, String>display(LoungeConstants.STATIONS);
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
                GenericGetGameNameInterface<String, Game> lambdaGameNameFunc = (gameObj) -> gameObj.getGame();
                MenuDisplay.displayValuesArr(LoungeConstants.GAMES.get(genre), lambdaGameNameFunc);
                menuOption = input.nextInt();
                game = MenuDisplay.<String, Game>getChoiceValue(LoungeConstants.GAMES.get(genre), menuOption, lambdaGameNameFunc);


                //CHOOSE CONSOLE
                System.out.printf("Choose console N° :  \n");
                for (int i = 0; i < LoungeConstants.CONSOLES.size(); i++) {
                    //            System.out.println("\n num" + num);
                    System.out.printf("%d \t| \t %s\n", i, LoungeConstants.CONSOLES.keySet().toArray(new String[0])[i]);
                }
                GetGameConsolesInterface<Console, Game> lambdaGameConsoles = (gameObj) -> gameObj.getAvailableOn();
                MenuDisplay.<Console, Game>displayConsoles(LoungeConstants.GAMES.get(genre), menuOption, lambdaGameConsoles);
                menuOption = input.nextInt();
                console = LoungeConstants.CONSOLES.keySet().toArray(new String[0])[menuOption];


                //REMEVOE THIS
//                System.out.printf("Enter start time : (Format HH:MM, Ex: 14:20) \n");
//                startTime = input.next();
//                startTime += ":00";
//
                //get starting time based on occupied stations chosen
                Timer timer = new Timer();
                timer.getStartTime();

                System.out.printf("Choose play time N° :  \n");
                System.out.printf("N° \t | \tPlay Time | \tPrice\n");
                int i = 0;
                for (String num : LoungeConstants.DURATIONS.keySet()) {
                    System.out.printf("%d \t| \t %s \t| \t %dDH\n", i, num, LoungeConstants.DURATIONS.get(num));
                    i++;
                }
                menuOption = input.nextInt();
//                durationOption = menuOption;
                duration = LoungeConstants.DURATIONS.keySet().toArray(new String[0])[menuOption];


//                System.out.printf("Confirm? : Y/N \n");
//                start = input.next();
//                if (input.next().compareToIgnoreCase("Y") == 0) {

//                System.out.println("time formatted:" + LocalTime.parse(startTime, DateTimeFormatter.ISO_TIME));
                Station stationObj = new Station(stationNum, console);

                //set file path for serialization
                AppStateManager.setPath(LoungeConstants.getStationSerializeFile());

                //save/serialize created station object
                AppStateManager.<Station>serialize(stationObj);

                //create session object
                PlaySession clientSession = new PlaySession(stationObj, game, LocalTime.parse(startTime, DateTimeFormatter.ISO_TIME), duration);
                numOfClients++;

                if(numOfClients < LoungeConstants.ACTIVE_CAPACITY){
                    ActiveSessions[stationNum] = clientSession;
                } else {
                    WaitingSessions.add(clientSession);
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
