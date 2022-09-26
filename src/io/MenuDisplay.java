package io;

import com.gaminglounge.Game;
import com.gaminglounge.LoungeConstants;
import util.interfaces.GetGameNameInterface;
import util.interfaces.GenericGetGameNameInterface;

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

    public static<T1> void displayValuesArr (List<T1> games, GenericGetGameNameInterface getGameFunc) {
        //use getGameName function from GenericGetGameNameInterface interface because of the use of generic types
        for (int i = 0; i < games.size(); i++){
            System.out.printf("%d \t| \t %s\n", i, getGameFunc.getGameName(games.get(i)));
        }

    }
//    public static<T1,T2 extends GetGameNameInterface> void displayObjValuesArr (Map<T1, T2> map, GenericGetGameNameInterface getGameFunc) {
//        for (T1 num : map.keySet()) {
//            T2 gameObj = map.get(num);
//            System.out.printf("%d \t| \t %s\n", num, getGameFunc.getGameName(gameObj));
//        }
//    }


    public static <T1, T2> T1 getChoiceValueKeySet (Map<T1, T2> map, int menuOption, Supplier<T1[]> factory) {
        T1[] t = factory.get();
        return map.keySet().toArray(t)[menuOption];
    }

    public static <T1, T2> void getChoiceValue (Map<T1, T2> map, T1 key, int menuOption, GenericGetGameNameInterface getGameFunc, Supplier<T2[]> factory) {
        T2[] t = factory.get();
        T2 games = map.get(key);
        String gameStr = getGameFunc.getGameName(games.get(menuOption));
        return ;
    }

    public static <T1, T2> T1 getMenuChoice (Map<T1, T2> map, int menuOption, Supplier<T1[]> factory) {
        T1[] t = factory.get();
        return map.keySet().toArray(t)[menuOption];
    }
    public void returnPossibleDurations () {
    //display only possible durations
    }

}
