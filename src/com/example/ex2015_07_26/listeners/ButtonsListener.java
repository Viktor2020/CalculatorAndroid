package com.example.ex2015_07_26.listeners;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.ex2015_07_26.core.Calculator;

import java.util.HashMap;

public class ButtonsListener implements View.OnClickListener {

    private static final String TAG = ButtonsListener.class.toString();
    private static final String POINT = ".";
    private final StringBuffer expression;
    private final ChangeTextDisplayListener textDisplayListener;
    private final ProgressBarListener progressBarListener;
    private final Calculator calculator;
    private final HashMap<String, Double> params = new HashMap<>();
    private final String regexOperators = "[\\+-/*]";// +-*/
    private String bufEndExpression = "";

    public ButtonsListener(final Calculator calculator,
                           final StringBuffer expression,
                           final ChangeTextDisplayListener textDisplayListener, ProgressBarListener progressBarListener) {
        this.calculator = calculator;
        this.expression = expression;
        this.textDisplayListener = textDisplayListener;
        this.progressBarListener = progressBarListener;
    }

    private void changeDisplay() {
        textDisplayListener.changeTextDisplay(String.valueOf(expression));
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        Log.wtf(TAG, button.getText().toString());
        String btnText = button.getText().toString();

        String regexNumberBracket = "[0-9\\(\\)]";
        if (btnText.equals("=")) {
            result();
        } else if (btnText.matches(regexNumberBracket)) {
            addNumber(btnText);
        } else if (btnText.matches(regexOperators)) {
            addOperator(btnText);
        } else if (btnText.equals("Pi")) {
            addNumber("pi(0)");
        } else if (btnText.equals(POINT)) {
            addPoint();
        } else if (btnText.equals("sin")) {
            addFunc("sin");
        } else if (btnText.equals("cos")) {
            addFunc("cos");
        } else if (btnText.equals("tan")) {
            addFunc("tan");
        } else if (btnText.equals("x^(1/3)")) {
            addFunc("cube");
        } else if (btnText.equals("log")) {
            addFunc("log");
        } else if (btnText.equals("x^2")) {
            addFunc("pow");
        } else if (btnText.equals("exp")) {
            addFunc("exp");
        } else if (btnText.equals("x!")) {
            addFunc("fact");
        } else if (btnText.equals("x^y")) {//todo
            addFuncParam("pow(x,y)");
        } else if (btnText.equals("√")) {
            addFunc("sqrt");
        } else if (btnText.equals("ln")) {
            addFunc("ln");
        } else if (btnText.equals("+/-")) {
            reversNumber();
        } else if (btnText.equals("C")) {
            clearExpression();
        } else if (btnText.equals("←")) {
            clearOneCharExpression();
        }

        changeDisplay();
    }

    private void addFuncParam(String fact) {

    }

    private void addOperator(String operator) {
        if (isCorrectOperator(operator)) {
            addNumber(operator);
        }
    }

    private boolean isFirstChar() {
        return expression.length() == 0;
    }

    private boolean isCorrectOperator(String operator) {
        if (isFirstChar()) {
            return operator.matches("[\\+-]");
        } else if (!isBackCharOperator()) {
            return true;
        }
        return false;
    }

    private boolean isBackCharOperator() {
        return expression.length() > 0 && expression.substring(expression.length() - 1).matches(regexOperators);
    }

    private void addPoint() {
        if (!isPoint()) {
            addNumber(POINT);
        }
    }

    private boolean isPoint() {
        return (getLastNumber().indexOf(POINT)) > 0;
    }

    private void addNumber(String number) {
        expression.append(number);
    }

    private String getLastNumber() {
        String expr = new String(expression);
        if (expr.length() > 0) {
            String[] numbers = expr.split("[^-?0-9\\.]+");// all numbers in expression// todo test regexp 1.10.25.0
            String lastNumber = numbers[numbers.length - 1];
            Log.wtf(TAG, "Last number = " + lastNumber);
            return lastNumber;
        } else return "";
    }

    private void removeLastNumber(String lastNumber) {
        int firstIndex = expression.lastIndexOf(lastNumber);
        int lastIndex = lastNumber.length() + firstIndex;

        expression.delete(firstIndex, lastIndex);//remove last number in expression
        popEndExpression(firstIndex);//putting an "end expressions" to the buffer

    }

    /**
     * Putting an end to the expressions to the buffer
     *
     * @param firstIndex where to get the Index
     */
    private void popEndExpression(int firstIndex) {
        bufEndExpression = new String(expression).substring(firstIndex, expression.length());
        expression.delete(firstIndex, expression.length());
    }

    /**
     * get last number in expression and remove last number in expression
     *
     * @return last number
     */
    private double popLastNumber() {
        try {
            String lastNumber = getLastNumber();//get last number in expression

            if (expression.length() > 0) {
                removeLastNumber(lastNumber);
            }
            return Double.parseDouble(lastNumber);
        } catch (java.lang.NumberFormatException e) {
            Log.e(TAG, "ButtonsListener.popLastNumber expression = " + expression, e);
        }
        return 0;
    }

    private void reversNumber() {
        double num = popLastNumber();
        num = num / (-1);
        expression.append(num);
        pushEndExpression();
    }

    private void addFunc(String name) {
        double num = popLastNumber();
        expression.append(name);
        expression.append("(");
        expression.append(num);
        expression.append(")");
        pushEndExpression();
    }

    /**
     * Take end of the expression from the buffer and put in an expression
     */
    private void pushEndExpression() {
        expression.append(bufEndExpression);
        bufEndExpression = "";
    }


    private void result() {
        progressBarListener.runProgress();
        double res = calculator.calculate(String.valueOf(expression));
        Log.wtf(TAG, expression.toString() + " = " + res);
        clearExpression();
        expression.append(res);
    }

    private void clearExpression() {
        bufEndExpression = "";
        expression.delete(0, expression.length());
    }

    private void clearOneCharExpression() {
        if (expression.length() > 0) {
            expression.delete(expression.length() - 1, expression.length());
        }
    }

}
