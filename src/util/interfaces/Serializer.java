package util.interfaces;

import com.gaminglounge.PlaySession;

public interface Serializer {
    public void serialize(PlaySession obj);
    public void deserialize(PlaySession obj);
}
