package com.almundo.poc.handlercall;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Logger;

/**
 * Encargado de asignar el procesamiento de una llamadas al worker o empleado disponible.
 *  @author Milton Lamprea
 */
public class Dispatcher {

    private Dispatcher succesor = null;
    private ExecutorService executorService;
    private String id;
    private static final Logger logger = Logger.getLogger(Class.class.getName());

    /**
     * Procesa la llamada si tiene empleados disponibles
     * Cuando no tiene empleados disponibles delega el procesamiento a  un empleado sucesor
     * La cantidad de empleados disponibles esta dado por el tamaño del pool de  Hilos de ExecutorService
     * @param call
     * @throws InterruptedException
     * @throws ExecutionException
     */

    public void dispatchCall(AbstractCall call) throws InterruptedException, ExecutionException {
        ThreadPoolExecutor pool = (ThreadPoolExecutor) executorService;
        int callsActived = pool.getActiveCount();
        int callsAllowed = pool.getMaximumPoolSize();

        //logger.info(id + " --> Total call process available: " + callsAllowed);
        //logger.info(id + " -> Currently calls executing: " + callsActived);

        if (callsActived >= callsAllowed) {
            logger.info(id + " --> There is no workers available ");
            if (succesor != null) {
                logger.info(id + " --> Trying to be processed by " + succesor.getId());
                succesor.dispatchCall(call);
            }
            /*
             Solution proposed when there is no employees available.
             Wait in a queue to be process by employee available
              */
            else {
                logger.info(id + " --> There is no employees available at the moment.");
                logger.info(id + " --> Call ID: " + call.getId() + ".The Call will be suspended and queued.");
                executorService.submit(call);
            }
        } else {
            executorService.submit(call);
        }
    }

    /**
     *
     * Metodos de encapsulamiento
     *
     */

    public void setSuccesor(Dispatcher succesor) {
        this.succesor = succesor;
    }

    public Dispatcher getSuccesor() {
        return succesor;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
