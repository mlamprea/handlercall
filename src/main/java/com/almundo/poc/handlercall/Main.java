package com.almundo.poc.handlercall;

import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        /*
        Genera un dispatcher con capacidad de 10  llamadas concurrentes
         */
        int totalCallsAllowed = 10;
        DispatcherFactory dispatcherFactory = new UniformDispatcherFactory();
        Dispatcher dispatcher = dispatcherFactory.createDisptacher(totalCallsAllowed);

        /*
        Genera diez llamadas.
        Cada llamada tiene una duracion aleaotoria entre 5 y 10 segundos
         */
        int totalCallsRequested = 10;
        try {
            for (int i = 0; i < totalCallsRequested; i++) {
                AbstractCall call = new Call();
                call.setId(i + "");
                dispatcher.dispatchCall(call);
            }
            dispatcherFactory.finishDispatcher(dispatcher);
        } catch (Exception e) {
            logger.severe("Error: " + e.getStackTrace());
        }
    }
}
