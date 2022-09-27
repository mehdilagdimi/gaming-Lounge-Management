package util.interfaces;

import com.gaminglounge.Console;
import com.gaminglounge.Game;

import java.util.ArrayList;
import java.util.List;

public interface GetGameConsolesInterface<T1, T2> {
    List<T1> getConsoles(T2 gameObj);
}
