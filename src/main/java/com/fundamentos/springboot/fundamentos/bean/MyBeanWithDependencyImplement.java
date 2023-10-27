package com.fundamentos.springboot.fundamentos.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency{

    Log LOGGER = LogFactory.getLog(MyBeanWithDependencyImplement.class);
    private final MyOperation myOperation;

    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency(int number, int secondNumber) {
        LOGGER.info("Ingresamos al método printWithDependency");
        int result = this.myOperation.sum(number, secondNumber);
        LOGGER.debug("El resultado de la operación es: " + result);
        System.out.println("El resultado de la operación es: " + result);
        System.out.println("Hola desde la implementación de un bean con dependencia");
        LOGGER.info("El resultado de la operación es: " + result);
    }
}
