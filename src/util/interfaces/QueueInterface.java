package util.interfaces;

import com.gaminglounge.PlaySession;
import util.Queue;

public interface QueueInterface {
    Queue<PlaySession> add(PlaySession session);
    Queue<PlaySession> remove(PlaySession session);
}
