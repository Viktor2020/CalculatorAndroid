package com.example.ex2015_07_26.core;


import java.util.HashMap;

public interface Calculator {
    double calculate(String expression) ;
    double calculate(String expression,HashMap<String, Double> params) ;
}
