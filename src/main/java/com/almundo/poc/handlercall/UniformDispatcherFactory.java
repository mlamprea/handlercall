package com.almundo.poc.handlercall;

import java.util.concurrent.Executors;

public class UniformDispatcherFactory extends DispatcherFactory {
    @Override

    public Dispatcher createDisptacher(int capacity) {
        int operators = 0;
        int supervisors = 0;
        int managers = 0;

        /*
        divide la capacidad requerida en tres partes homogoneas
        cuando la cantidad requerida es par se asigna un operador adicional
         */
        int portion = capacity / 3;
        supervisors = managers = portion;

        if (capacity == 2) {
            operators = supervisors = 1;
            managers = 0;
        }

        if (capacity < 2) {
            operators = capacity;
            supervisors = managers = 0;
        }

        if (capacity % 2 == 0)
            operators = portion + 1;
        else
            operators = portion;

        Dispatcher operator = new Operator();
        Dispatcher supervisor = new Supervisor();
        Dispatcher manager = new Manager();

        /*
        define la capacidad de procesamiento para cada empleado
         */
        operator.setId("Operator");
        supervisor.setId("Supervisor");
        manager.setId("Manager");

        operator.setExecutorService(Executors.newFixedThreadPool(operators));
        supervisor.setExecutorService(Executors.newFixedThreadPool(supervisors));
        manager.setExecutorService(Executors.newFixedThreadPool(managers));

        /*
        define el orden de atencion de las llamadas
         */
        operator.setSuccesor(supervisor);
        supervisor.setSuccesor(manager);
        manager.setSuccesor(null);
        return operator;
    }
}
