package com.almundo.poc.handlercall;


import org.junit.Assert;
import org.junit.Test;


import java.util.concurrent.ThreadPoolExecutor;

/**
 * Pruebas unitarias para verificar el comportamiento cuando hay más llamadas entrantes que la capacidad de empleados para atender.
 * @author Milton Lamprea
 */
public class AppTest {

    @Test
    public void prueba10LLamadasConcurrentes() {

        DispatcherFactory dispatcherFactory = new UniformDispatcherFactory();
        int numOfCalls = 10;
        int capacity = 10;

        Dispatcher dispatcher = dispatcherFactory.createDisptacher(capacity);
        Dispatcher supervisor = dispatcher.getSuccesor();
        Dispatcher manager = supervisor.getSuccesor();

        testConcurrentCall(dispatcher, numOfCalls);

        int numOfRunningCalls = ((ThreadPoolExecutor) (dispatcher.getExecutorService())).getActiveCount()
                + ((ThreadPoolExecutor) (supervisor.getExecutorService())).getActiveCount()
                + ((ThreadPoolExecutor) (manager.getExecutorService())).getActiveCount();
        System.out.println("Total llamadas concurrentes: " + numOfRunningCalls);
        Assert.assertTrue(numOfRunningCalls == numOfCalls);
    }

    /**
     * Prueba con 15 llamadas.
     * Se espera tener 10 llamadas concurrentes y 5 en espera
     */
    @Test
    public void prueba15LlamadasConcurrentes() {

        DispatcherFactory dispatcherFactory = new UniformDispatcherFactory();
        int numOfCalls = 15;
        int capacity = 10;

        Dispatcher dispatcher = dispatcherFactory.createDisptacher(capacity);
        Dispatcher supervisor = dispatcher.getSuccesor();
        Dispatcher manager = supervisor.getSuccesor();

        testConcurrentCall(dispatcher, numOfCalls);

        int numOfRunningCalls = ((ThreadPoolExecutor) (dispatcher.getExecutorService())).getActiveCount()
                + ((ThreadPoolExecutor) (supervisor.getExecutorService())).getActiveCount()
                + ((ThreadPoolExecutor) (manager.getExecutorService())).getActiveCount();
        System.out.println("Total llamadas concurrentes: " + numOfRunningCalls);


        int numOfPendingCalls = ((ThreadPoolExecutor) (manager.getExecutorService())).getQueue().size();
        System.out.println("Total llamadas en espera: " + numOfPendingCalls);
        Assert.assertTrue(numOfCalls - capacity == numOfPendingCalls);
    }


    public void testConcurrentCall(Dispatcher dispatcher, int numOfcalls) {

        try {
            for (int i = 0; i < numOfcalls; i++) {
                AbstractCall call = new Call();
                call.setId(i + "");
                dispatcher.dispatchCall(call);
            }

        } catch (Exception e) {

        }
    }
}

