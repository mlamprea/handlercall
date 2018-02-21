package com.almundo.poc.handlercall;

import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

/**
 * Abstracción de empleado Operador
 * @author Milton Lamprea
 */
public class Operator extends Dispatcher {
    private static final Logger logger = Logger.getLogger(Operator.class.getName());

    @Override
    public void dispatchCall(AbstractCall call) throws InterruptedException, ExecutionException {
        logger.info("Processing call by Operator - Dispatching call " + call.getId());
        super.dispatchCall(call);
    }
}
