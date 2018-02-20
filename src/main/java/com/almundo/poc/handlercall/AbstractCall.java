package com.almundo.poc.handlercall;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public abstract class AbstractCall implements Runnable {
    private Long duration;
    private String id;

    @Override
    public String toString() {
        return "ID Call: " + id + ". Duration: " + duration;
    }

    /*
    By default set duration between 5 to 10 seconds
     */
    public long getDuration() {
        int max = 10000;
        int min = 5000;
        return (new Random().nextInt(max - min + 1) + min);
    }

    /*
    Set duration in seconds
     */
    public void setDuration(long duration) {
        this.duration = duration * 1000;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
