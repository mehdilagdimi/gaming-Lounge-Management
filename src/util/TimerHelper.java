package util;

import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class TimerHelper {

    public Timer timer = new Timer();
    public LocalTime finishDate;
    public boolean state = true;

    public TimerHelper(int duration) {
        this.startTimer(duration);
    }
    public LocalTime getStartTime () {
        return LocalTime.now();
    }

//   WILL THIS METHOD WORK CORRECTLY?? UPDATE THIS METHOD TO USE ASYNC CODING OR USE  ScheduledExecutorService https://stackoverflow.com/questions/1321620/waiting-for-a-timer-to-finish-in-java
    public boolean startTimer (int duration) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                state = !state;
            };
        };
        this.timer.schedule(task, duration);
        return state;
    }

}
