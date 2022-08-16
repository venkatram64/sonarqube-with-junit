package com.venkat.service;

public class LoggerService {

    public void log(String s){
        System.out.println(s);
    }

    public void logToErr(String s){
        System.err.println(s);
    }
}
