package com.venkat.fp;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Supplier;

public class MethodReference {

    public static void main(String[] args) {
        List<String> courses = null;//List.of("Spring", "Spring Boot", "API", "Microservices", "AWS", "PCF", "Azure", "Docker","Kubernetes");

        courses.stream()
                //.map(str -> str.toUpperCase())
                //.forEach(str -> System.out.println(str));
                .map(String::toUpperCase)
                .forEach(System.out :: println);

        Supplier<String> supplier2 = () -> new String();

        Supplier<String> supplier = String :: new;

        if(supplier.get() instanceof String){
            System.out.println("It is string...");
        }

        //var intValue = Optional.<Integer>empty();
        Optional<Integer> intValue = Optional.<Integer>empty();
        intValue = Optional.of(23);
        System.out.println(intValue);
    }
}
