package com.gaminglounge;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Lounge {
    public static void main(String[] args) {
        int totalRevenue;
        int menuOption;
        int stationNum;
//        int durationOption;
        String start;
        String console, genre, game;
        String startTime;
        String duration;

        boolean repeat = true;
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to The Gaming Lounge Platform!");
        System.out.println("Add a new session? : Y/N");

        while (repeat) {
            start = input.next();
            if (start.compareToIgnoreCase("Y") == 0) {
                System.out.printf("Choose station N° :  \n");
                System.out.printf("\t--- Stations ---\n");
                System.out.printf("Station N° \t | \tTV Screen \n");
                for (int num : LoungeConstants.STATIONS.keySet()) {
                    //            System.out.println("\n num" + num);
                    System.out.printf("%d \t| \t %s\n", num, LoungeConstants.STATIONS.get(num));
                }
                menuOption = input.nextInt();
                stationNum = LoungeConstants.STATIONS.keySet().toArray(new Integer[0])[menuOption];

                System.out.printf("Choose console N° :  \n");
                for (int i = 0; i < LoungeConstants.CONSOLES.size(); i++) {
                    //            System.out.println("\n num" + num);
                    System.out.printf("%d \t| \t %s\n", i, LoungeConstants.CONSOLES.keySet().toArray(new String[0])[i]);
                }
                menuOption = input.nextInt();
                console = LoungeConstants.CONSOLES.keySet().toArray(new String[0])[menuOption];

                System.out.println("Choose genre N° : ");

                for (int i = 0; i < LoungeConstants.GAMES.size(); i++) {
//                    String genre = LoungeConstants.GAMES.keySet().toArray(new String[0])[i];
                    System.out.printf("%d \t| \t %s\n", i, LoungeConstants.GAMES.keySet().toArray(new String[0])[i]);
                }
                menuOption = input.nextInt();
                genre = LoungeConstants.GAMES.keySet().toArray(new String[0])[menuOption];

                System.out.println("Choose game N° : ");
                for (int i = 0; i < LoungeConstants.GAMES.get(genre).size(); i++) {
                    System.out.printf("%d \t| \t %s\n", i, LoungeConstants.GAMES.get(genre).toArray(new String[0])[i]);
                }
                menuOption = input.nextInt();
                game = LoungeConstants.GAMES.get(genre).toArray(new String[0])[menuOption];

                System.out.printf("Enter start time : (Format HH:MM, Ex: 14:20) \n");
                startTime = input.next();
                startTime += ":00";
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

//                 switch(timeOption){
//                     case 0:
//
//                         break;
//                     case 1:
//
//                         break;
//                     case 2:
//
//                         break;
//                     case 3:
//
//                         break;
//                     case 4:
//
//                         break;
//                 }
                System.out.println("time :" + startTime);
                System.out.println("time formatted:" + LocalTime.parse(startTime, DateTimeFormatter.ISO_TIME));
                Station stationObj = new Station(stationNum, console);
                playSession client = new playSession(stationObj, LocalTime.parse(startTime, DateTimeFormatter.ISO_TIME), duration);
//                repeat = false;


            }
        }
    }

}
