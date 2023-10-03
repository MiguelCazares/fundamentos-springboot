package com.fundamentos.springboot.fundamentos.bean;

public class MyOperationImplement implements MyOperation {
    @Override
    public int sum(int number, int number2) {
        int res = number + number2;
        return res;
    }
}
