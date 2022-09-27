package util;

import com.gaminglounge.LoungeConstants;
import com.gaminglounge.PlaySession;
//import util.interfaces.QueueInterface;

public class Queue<T> {
    private T[] playSessionsArr;


    public Queue(T[] playSession) {
        this.playSessionsArr = playSession;
    }

    public Queue<T> add(T session) {
        int size = this.playSessionsArr.length;

        this.playSessionsArr[size - 1] = session;

        return this;
    }

    public Queue<T> remove(T session) {
        int size = this.playSessionsArr.length;
        for(int i = 0; i < size - 1; i++ ){
            this.playSessionsArr[i] = this.playSessionsArr[i + 1];
        }
        this.playSessionsArr[size - 1] = null;
        return this;
    }



}
