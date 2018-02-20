package com.almundo.poc.handlercall;

public abstract class DispatcherFactory {
    public abstract Dispatcher createDisptacher(int capacity);

    /*
    Finaliza el disptacher esperando que finalicen las llamadas en procesamiento
     */
    public void finishDispatcher(Dispatcher dispatcher) {
        dispatcher.getExecutorService().shutdown();
    }

    /*
     Interrumpe todas las llamadas  y apaga el dispatcher
  */
    public void shutDownDispatcher(Dispatcher dispatcher) {
        dispatcher.getExecutorService().shutdown();
    }
}
