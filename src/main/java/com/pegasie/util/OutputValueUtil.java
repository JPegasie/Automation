package com.pegasie.util;

/**
 * This class sets or gets a static variable for global scope use.
 */
public class OutputValueUtil {

    public static String value, value1, value2, value3;
    /**
     * This method sets a value to one of the static class variables. It requires two parameters. See also 'TextBoxUtil.inputputValue`.
     * @param val the actual value to be stored for later use and available globally.
     * @param outputVar possible values are either @GetOutputValue1, @GetOutputValue2, @GetOutputValue3, @GetOutputValue1Increment, @GetOutputValue2Increment
     */
    public void setValue(String val, String outputVar) {
        Integer result;
        switch (outputVar) {
            case "@GetOutputValue1":
                value1 = val;
                break;
            case "@GetOutputValue2":
                value2 = val;
                break;
            case "@GetOutputValue3":
                value3 = val;
                break;
            case "@GetOutputValue1Increment":
                result = Integer.parseInt(val) + 1;
                value1 = result.toString();
                break;
            case "@GetOutputValue2Increment":
                result = Integer.parseInt(val) + 1;
                value2 = result.toString();
                break;
        }
    }

    /**
     * This method sets a value to a static class variable. It requires one parameter.
     * @param val the actual value to be stored for later use and available globally.
     */
    public void setValue(String val) {
        value = val;
    }

    /**
     * This method gets the current value held in one of the static variables.
     * @param val can be either @GetOutputValue1, @GetOutputValue2, @GetOutputValue3.
     * @return returns the set actual value associated to the given 'val'.
     */
    public String getValue(String val) {
        switch (val) {
            case "@GetOutputValue1":
                value = value1;
                break;
            case "@GetOutputValue2":
                value = value2;
                break;
            case "@GetOutputValue3":
                value = value3;
                break;
            default:
                value = val;
        }
        return value;
    }

    /**
     * This method gets the current value held in one of the static variables.     *
     * @return returns the last set actual value as when the setValue(String val) was last called.
     */
    public String getValue() {
        return value;
    }
}
