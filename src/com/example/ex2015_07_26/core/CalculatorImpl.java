package com.example.ex2015_07_26.core;


import com.example.ex2015_07_26.core.matchParser.MatchParser;

import java.util.HashMap;
import java.util.Map;

public class CalculatorImpl implements Calculator {

    private MatchParser p = new MatchParser();

    @Override
    public double calculate(String expression) {
        try {
            return p.parse(expression);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Double.NaN;
    }

    @Override
    public double calculate(String expression, HashMap<String, Double> params) {
        try {
            if (params != null) {
                for (Map.Entry<String, Double> s : params.entrySet()) {
                    p.setVariable(s.getKey(), s.getValue());
                }
            }
            return p.parse(expression);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Double.NaN;
    }
}
