package com.pegasie.util;

public class StringUtil {
    /**
     * This method converts a string into a string array from test plan excel file.
     * @param parameters A string contains multiple parameters, separated by ' || '.
     * @return A string array contains test data.
     * @since 2018-07-26
     */
    public static String[] getStringArrayParameter(String parameters) {
        return parameters.split(" \\|\\| ");
    }

    /**
     * This method assigns every parameter a real value.
     * If the pass in parameter contains '%%', then it will get the real value from properties.
     * @param parameters A string array contains multiple parameters.
     * @return A string array of parameter contains real values.
     * @since 2018-07-26
     */
    public static String[] getParameterValueFromProperties(String[] parameters) {
        int size = parameters.length;
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            if (parameters[i].contains("%%")) {
                result[i] = getParameterRealValue(parameters[i]);
            } else {
                result[i] = parameters[i];
            }
        }
        return result;
    }

    /**
     * This method retrieve real value from properties.
     * @param parameter A string likes this format'%%xxxxxxxxxx%%'  .
     * @return A string contains real value.
     * @since 2018-07-26
     */
    public static String getParameterRealValue(String parameter) {
        int index = parameter.indexOf("%%");
        String firstPart = parameter.substring(0, index);
        parameter = parameter.substring(index + 2);
        index = parameter.indexOf("%%");
        String secondPart = parameter.substring(0, index);
        secondPart = System.getProperty(secondPart);
        String thirdPart = parameter.substring(index + 2);
        return firstPart + secondPart + thirdPart;
    }

    /**
     * This method converts a text into a integer.
     * @param text A string likes '123'.
     * @return An integer likes 123.
     * @since 2018-07-26
     */
    public static int convertToInt(String text) {
        if (text.contains(".")) {
            return Integer.valueOf(text.substring(0, text.indexOf(".")));
        } else {
            return Integer.valueOf(text);
        }
    }
}
