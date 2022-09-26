package util.interfaces;

import com.gaminglounge.Console;
import com.gaminglounge.Game;

import java.util.ArrayList;

public interface GetGameConsolesInterface<T1, T2> {
    ArrayList<T1> getConsoles(T2 gameObj);
}
