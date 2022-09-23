package util.interfaces;

import com.gaminglounge.PlaySession;

public interface AppStateManagerInterface {
    public void serialize(PlaySession obj);
    public void deserialize(PlaySession obj);
}
