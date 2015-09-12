package com.example.ex2015_07_26.core;

public class Converter {

    private static String decConvertTo(long num, int toSystem) {
        boolean negative = false;
        if (num < 0) {
            num /= -1;
            negative = true;
        }
        StringBuffer result = new StringBuffer();
        //region while(convert)
        while (num > 0) {
            long remainder = num % toSystem;
            if (remainder <= 10) {
                result.append(remainder);
            } else if (remainder == 11){
                result.append("A");
            } else if (remainder == 12){
                result.append("B");
            } else if (remainder == 13){
                result.append("C");
            } else if (remainder == 14){
                result.append("D");
            } else if (remainder == 15){
                result.append("E");
            } else if (remainder == 16){
                result.append("F");
            } else if (remainder == 17){
                result.append("G");
            } else if (remainder == 18){
                result.append("H");
            } else if (remainder == 19){
                result.append("I");
            } else if (remainder == 20){
                result.append("J");
            } else if (remainder == 21){
                result.append("K");
            } else if (remainder == 22){
                result.append("L");
            } else if (remainder == 23){
                result.append("M");
            } else if (remainder == 24){
                result.append("N");
            } else if (remainder == 25){
                result.append("O");
            } else if (remainder == 26){
                result.append("P");
            } else if (remainder == 27){
                result.append("Q");
            } else if (remainder == 28){
                result.append("R");
            } else if (remainder == 29){
                result.append("S");
            } else if (remainder == 30){
                result.append("T");
            } else if (remainder == 31){
                result.append("U");
            } else if (remainder == 32){
                result.append("V");
            } else if (remainder == 33){
                result.append("W");
            } else if (remainder == 34){
                result.append("X");
            } else if (remainder == 35){
                result.append("Y");
            } else if (remainder == 36){
                result.append("Z");
            }
            num = num / toSystem;
        }
        //endregion
        if (negative) {
            result.append("-", 0, 1);
        }
        return String.valueOf(result.reverse());
    }

    private static long inConvertToDec(long num, int fromSystem) {
        String string = String.valueOf(num);
        char firstChar = string.charAt(0);
        int offset = (firstChar == '-' || firstChar == '+') ? 1 : 0;
        boolean negative = firstChar == '-';
        int result = 0;
        int length = string.length();
        while (offset < length) {
            int digit = Character.digit(string.charAt(offset++), fromSystem);
            result = result * fromSystem - digit;
        }
        if (!negative) {
            result = -result;
        }
        return result;
    }

    public static String convert(int numberToConvert, int fromSystem, int toSystem) {
        return decConvertTo(inConvertToDec(numberToConvert, fromSystem), toSystem);
    }
}
