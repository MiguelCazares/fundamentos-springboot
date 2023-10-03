package com.fundamentos.springboot.fundamentos.bean;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency{

    private MyOperation myOperation;

    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency(int number, int secondNumber) {
        int result = this.myOperation.sum(number, secondNumber);
        System.out.println("El resultado de la operación es: " + result);
        System.out.println("Hola desde la implementación de un bean con dependencia");
    }
}
