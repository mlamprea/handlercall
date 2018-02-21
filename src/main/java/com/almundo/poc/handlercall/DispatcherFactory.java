package com.almundo.poc.handlercall;

/**
 * Abstracción de muchas formas posibles de construir un despachador de llamadas
 * @author Milton Lamprea
 */
public abstract class DispatcherFactory {

    public abstract Dispatcher createDisptacher(int capacity);

    /**
     * Finaliza el disptacher esperando que finalicen las llamadas en procesamiento de todos los empleados
     */
    public void finishDispatcher(Dispatcher dispatcher) {
        if (dispatcher == null) return;
        dispatcher.getExecutorService().shutdown();
        finishDispatcher(dispatcher.getSuccesor());
    }

    /**
     * Interrumpe todas las llamadas de todos los empleados y apaga el dispatcher
     */
    public void shutDownDispatcher(Dispatcher dispatcher) {
        if (dispatcher == null) return;
        dispatcher.getExecutorService().shutdownNow();
        finishDispatcher(dispatcher.getSuccesor());
    }
}
