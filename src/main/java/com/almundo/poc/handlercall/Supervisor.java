package com.almundo.poc.handlercall;

import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

public class Supervisor extends Dispatcher {
    private static final Logger logger = Logger.getLogger(Supervisor.class.getName());

    @Override
    public void dispatchCall(AbstractCall call) throws InterruptedException, ExecutionException {
        logger.info("Processing call by Supervisor - Dispatching call " + call.getId());
        super.dispatchCall(call);
    }
}

