package com.fundamentos.springboot.fundamentos.component;

import org.springframework.stereotype.Component;

@Component
public class ComponentImplement implements ComponentDependency {

    @Override
    public void fullName(String name, String lastName) {
        System.out.println("Mi nombre es: " + name + " " + lastName);
    }
}
