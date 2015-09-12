package com.example.ex2015_07_26.core;

public enum NumeralSystem {
    DECIMAL(10),
    OCTAL(8),
    BINARY(2);

    private int code;

    NumeralSystem(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
