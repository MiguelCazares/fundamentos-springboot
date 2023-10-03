package com.fundamentos.springboot.fundamentos.component;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class ComponentTwoImplement implements ComponentDependency {
    @Override
    public void fullName(String name, String lastName) {
        System.out.println("Mi nombre es: " + name + " " + lastName + " (desde el componente 2)");
    }
}
