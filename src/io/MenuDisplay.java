package io;

import com.gaminglounge.Console;
import com.gaminglounge.Game;
import util.interfaces.GetGameConsolesInterface;
import util.interfaces.GenericGetGameNameInterface;
import util.interfaces.GetScreenFromStation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class MenuDisplay {


    public static<T1, T2> void display (Map<T1, T2> map) {
        for (T1 num : map.keySet()) {
            //            System.out.println("\n num" + num);
            System.out.printf("%d \t| \t %s\n", num, map.get(num));
        }
    }

    public static<T1, T2> void displayKeySetArr (Map<T1, T2> map, Supplier<T1[]> factory) {
        T1[] t = factory.get();

        for (int i = 0; i < map.size(); i++) {
            System.out.printf("%d \t| \t %s\n", i, map.keySet().toArray(t)[i]);
        }
    }

    public static<T1 extends Game, T2> void displayValuesArr (List<T2> list, GenericGetGameNameInterface<T1, T2> getGameFunc) {
        //use getGameName function from GenericGetGameNameInterface interface because of the use of generic types
        //list can be list of games as an example
        for (int i = 0; i < list.size(); i++){
            System.out.printf("%d \t| \t %s\n", i, getGameFunc.getGame(list.get(i)).getGameName());
        }
    }
    public static<T1,T2> void displayObjValuesArr (Map<T1, T2> map, GetScreenFromStation getScreenFunc) {
        for (T1 num : map.keySet()) {
            T2 stationObj = map.get(num);
            System.out.printf("%d \t| \t %s\n", num, getScreenFunc.getScreen(stationObj));
        }
    }


    public static <T1, T2> T1 getChoiceValueKeySet (Map<T1, T2> map, int menuOption, Supplier<T1[]> factory) {
        T1[] t = factory.get();
        return map.keySet().toArray(t)[menuOption];
    }

    public static <T1, T2> T2 getStation (Map<T1, T2> map, int menuOption) {
        return map.get(menuOption);
    }

    public static <T1, T2> T1 getChoiceValue (List<T2> list, int menuOption, GenericGetGameNameInterface<T1, T2> getGameFunc) {
        T1 gameStr = getGameFunc.getGame(list.get(menuOption));
        return gameStr;
    }

    public static <T1 extends Console, T2 extends Game> void displayConsoles (T2 game, int menuOption, GetGameConsolesInterface<T1, T2> getGameConsoles) {
        //T2 == Game
        Console console;
        List<Console> consolesList = game.getAvailableOn();
        for (int i = 0; i < consolesList.size(); i++){
            console = consolesList.get(i);
            System.out.printf("%d \t| \t %s\n", i, console.getConsole());
        }
    }
//    public static <T1 extends Console, T2> void displayConsoles (List<T2> list, int menuOption, GetGameConsolesInterface<T1, T2> getGameConsoles) {
//        //T2 == Game
//        T1 console;
//        T2 gameObj = list.get(menuOption);
//        List<T1> consolesList = getGameConsoles.getConsoles(gameObj);
//        for (int i = 0; i < consolesList.size(); i++){
//            console = consolesList.get(i);
//            System.out.printf("%d \t| \t %s\n", i, console.getConsole());
//        }
//    }

    public static <T1, T2> T1 getMenuChoice (Map<T1, T2> map, int menuOption, Supplier<T1[]> factory) {
        T1[] t = factory.get();
        return map.keySet().toArray(t)[menuOption];
    }
    public void returnPossibleDurations () {
    //display only possible durations
    }

}
