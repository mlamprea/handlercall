package com.almundo.poc.handlercall;

import java.util.logging.Logger;

public class Call extends AbstractCall {

    private static final Logger logger = Logger.getLogger(Call.class.getName());

    @Override
    public void run() {
        try {
            logger.info("Process: "+Thread.currentThread().getName()+". Executing Call_ID: " + getId() + " " + getDuration() / 1000 + " seconds ...");
            Thread.sleep(getDuration());
            logger.info("Process: "+Thread.currentThread().getName()+". Call_ID " + getId() + " finished");
        } catch (InterruptedException e) {
            logger.severe(e.getMessage());
        }
    }
}

