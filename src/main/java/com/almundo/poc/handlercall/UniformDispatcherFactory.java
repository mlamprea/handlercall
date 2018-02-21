package com.almundo.poc.handlercall;

import java.util.concurrent.Executors;

/**
 * Fabrica de despachadores donde la division de carga entre workers es  homogenea o uniforme*
 * @author Milton Lamprea
 */

public class UniformDispatcherFactory extends DispatcherFactory {
    @Override

/**
 * @param capacity capacidad de llamadas concurrentes para procesar
 *  Construye un despachador dividiendo la capacidad todal de llamadas concurrentes entre los tres tipos de empleado: operador, supervisor y director.
 *
 */
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

        /**
         define la capacidad de procesamiento para cada empleado
         */
        operator.setId("Operator");
        supervisor.setId("Supervisor");
        manager.setId("Manager");

        operator.setExecutorService(Executors.newFixedThreadPool(operators));
        supervisor.setExecutorService(Executors.newFixedThreadPool(supervisors));
        manager.setExecutorService(Executors.newFixedThreadPool(managers));

        /**
         define el orden de atencion de las llamadas
         */
        operator.setSuccesor(supervisor);
        supervisor.setSuccesor(manager);
        manager.setSuccesor(null);
        return operator;
    }
}
