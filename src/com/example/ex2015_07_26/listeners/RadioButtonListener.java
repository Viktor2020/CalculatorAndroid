package com.example.ex2015_07_26.listeners;


import android.util.Log;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.example.ex2015_07_26.core.Converter;
import com.example.ex2015_07_26.core.NumeralSystem;

public class RadioButtonListener implements CompoundButton.OnCheckedChangeListener {

    private static final String TAG = RadioButtonListener.class.toString();
    private NumeralSystem inSystem = NumeralSystem.DECIMAL; // default decimal system
    private NumeralSystem toSystem = NumeralSystem.DECIMAL;
    private final StringBuffer expression;
    private final ChangeTextDisplayListener textDisplayListener;
    private final ProgressBarListener progressBarListener;

    public RadioButtonListener(StringBuffer expression,
                               ChangeTextDisplayListener textDisplayListener,
                               ProgressBarListener progressBarListener) {
        this.expression = expression;
        this.textDisplayListener = textDisplayListener;
        this.progressBarListener = progressBarListener;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        try {
            if (isChecked) {
                RadioButton button = (RadioButton) buttonView;
                int toSys = Integer.parseInt(button.getText().toString());
                int num = (int) Double.parseDouble(getLastNumber());
                changeToSystem(toSys);

                Log.wtf(TAG, "number to convert  = " + num);
                Log.wtf(TAG, "convert is = " + inSystem.getCode());
                Log.wtf(TAG, "convert to = " + toSystem.getCode());

                String  convertNum = Converter.convert(num, inSystem.getCode(), toSystem.getCode());

                Log.wtf(TAG, "number have  = " + convertNum);

                clearExpression();
                setExpression(convertNum);
                changeInSystem();
                changeDisplay();
            }
        } catch (RuntimeException e) {
            Log.w(TAG, " ошибочка :) ", e);
        }
    }

    public void setExpression(String  expression) {
        this.expression.append(expression);
    }

    private void changeDisplay() {
        progressBarListener.runProgress();
        textDisplayListener.changeTextDisplay(String.valueOf(expression));
    }

    private void clearExpression() {
        expression.delete(0, expression.length());
    }

    private void changeInSystem() {
         inSystem = toSystem;
    }

    private void changeToSystem(int toSys) {
        if (toSys == 2) {
            toSystem = NumeralSystem.BINARY;
        } else if (toSys == 8) {
            toSystem = NumeralSystem.OCTAL;
        } else if (toSys == 10) {
            toSystem = NumeralSystem.DECIMAL;
        } else {
            toSystem = NumeralSystem.DECIMAL;// default
        }
    }

    private String getLastNumber() {
        String expr = new String(expression);
        if (expr.length() > 0) {
            String[] numbers = expr.split("[^-?0-9\\.]+");// all numbers in expression //todo test regexp
            String lastNumber = numbers[numbers.length - 1];
            Log.wtf(TAG, "Last number = " + lastNumber);
            return lastNumber;
        } else return "0.0";
    }

}
