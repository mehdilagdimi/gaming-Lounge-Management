package util.interfaces;

public interface GenericGetGameNameInterface<T1, T2> {

        //this method will be used for game objects
//        String getGameName(T bj);
        T1 getGame(T2 obj);
    }
