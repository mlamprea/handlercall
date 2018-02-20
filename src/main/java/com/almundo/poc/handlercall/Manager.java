package com.almundo.poc.handlercall;

import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

public class Manager extends Dispatcher {
    private static final Logger logger = Logger.getLogger(Manager.class.getName());

    @Override
    public void dispatchCall(AbstractCall call) throws InterruptedException, ExecutionException {
        logger.info("Processing call by Manager - Dispatching call " + call.getId());
        super.dispatchCall(call);
    }
}

