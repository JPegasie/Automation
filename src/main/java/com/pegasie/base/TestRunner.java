package com.pegasie.base;

import com.pegasie.datamodel.TestStep;
import com.pegasie.util.CSVUtil;
import com.pegasie.util.StringUtil;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Properties;

public class TestRunner {
    private Properties properties;
    private Logger logger;

    public TestRunner(Properties properties, Logger logger) {
        this.properties = properties;
        this.logger = logger;
    }

    public boolean runTestCase(ArrayList<TestStep> testSteps) {
        String testPlan = System.getProperty("TestPlan");
        String packageLocation = properties.getProperty("PagePackageLocation");
        String testExecutionDetailFile = System.getProperty("TestExecutionDetailFile");
        boolean testResult = true;

        for (TestStep testStep : testSteps) {
            int testId = testStep.testId;
            String testName = testStep.testName;
            String page = testStep.page;
            String action = testStep.stepAction;
            String argument = testStep.stepArgument;
            String[] parameters;
            String errorMessage = null;
            Object pageInstance = null;
            Class pageClass = null;
            Method method;

            try {
                if (doesClassExist(packageLocation + page)) {
                    pageClass = Class.forName(packageLocation + page);
                    pageInstance = pageClass.newInstance();

                    int methodParameterCount = -1;
                    Method[] methods = pageClass.getMethods();
                    for (Method m : methods) {
                        if (m.getName().equals(action)) {
                            methodParameterCount = m.getParameterCount();
                            break;
                        }
                    }
                    switch (methodParameterCount) {
                        case 0:
                            method = pageClass.getMethod(action);
                            if (method.getReturnType().equals(boolean.class)) {
                                testResult = (Boolean) method.invoke(pageInstance, null);
                                errorMessage = "Verification of " + action + " returned " + String.valueOf(testResult) + "\n";
                            } else {
                                method.invoke(pageInstance, null);
                            }
                            break;
                        case 1:
                            method = pageClass.getMethod(action, String.class);
                            argument = argument.contains("%%") ? StringUtil.getParameterRealValue(argument) : argument;
                            if (method.getReturnType().equals(boolean.class)) {
                                testResult = (Boolean) method.invoke(pageInstance, argument);
                                errorMessage = "Verification of " + action + " returned " + String.valueOf(testResult) + "\n";
                            } else {
                                method.invoke(pageInstance, argument);
                            }
                            break;
                        case 2:
                            method = pageClass.getMethod(action, String.class, String.class);
                            parameters = argument.contains(" || ") ? StringUtil.getStringArrayParameter(argument) : null;
                            parameters = StringUtil.getParameterValueFromProperties(parameters);
                            argument = parameters[0] + " || " + parameters[1];
                            if (method.getReturnType().equals(boolean.class)) {
                                testResult = (Boolean) method.invoke(pageInstance, parameters[0], parameters[1]);
                                errorMessage = "Verification of " + action + " returned " + String.valueOf(testResult) + "\n";
                            } else {
                                method.invoke(pageInstance, parameters[0], parameters[1]);
                            }
                            break;
                        case 3:
                            method = pageClass.getMethod(action, String.class, String.class, String.class);
                            parameters = argument.contains(" || ") ? StringUtil.getStringArrayParameter(argument) : null;
                            parameters = StringUtil.getParameterValueFromProperties(parameters);
                            argument = parameters[0] + " || " + parameters[1] + " || " + parameters[2];
                            if (method.getReturnType().equals(boolean.class)) {
                                testResult = (Boolean) method.invoke(pageInstance, parameters[0], parameters[1], parameters[2]);
                                errorMessage = "Verification of " + action + " returned " + String.valueOf(testResult) + "\n";
                            } else {
                                method.invoke(pageInstance, parameters[0], parameters[1], parameters[2]);
                            }
                        case 4:
                            method = pageClass.getMethod(action, String.class, String.class, String.class, String.class);
                            parameters = argument.contains(" || ") ? StringUtil.getStringArrayParameter(argument) : null;
                            parameters = StringUtil.getParameterValueFromProperties(parameters);
                            argument = parameters[0] + " || " + parameters[1] + " || " + parameters[2] + " || " + parameters[3];
                            if (method.getReturnType().equals(boolean.class)) {
                                testResult = (Boolean) method.invoke(pageInstance, parameters[0], parameters[1], parameters[2], parameters[3]);
                                errorMessage = "Verification of " + action + " returned " + String.valueOf(testResult) + "\n";
                            } else {
                                method.invoke(pageInstance, parameters[0], parameters[1], parameters[2], parameters[3]);
                            }
                            break;
                        case 5:
                            method = pageClass.getMethod(action, String.class, String.class, String.class, String.class, String.class);
                            parameters = argument.contains(" || ") ? StringUtil.getStringArrayParameter(argument) : null;
                            parameters = StringUtil.getParameterValueFromProperties(parameters);
                            argument = parameters[0] + " || " + parameters[1] + " || " + parameters[2] + " || " + parameters[3] + " || " + parameters[4];
                            if (method.getReturnType().equals(boolean.class)) {
                                testResult = (Boolean) method.invoke(pageInstance, parameters[0], parameters[1], parameters[2], parameters[3], parameters[4]);
                                errorMessage = "Verification of " + action + " returned " + String.valueOf(testResult) + "\n";
                            } else {
                                method.invoke(pageInstance, parameters[0], parameters[1], parameters[2], parameters[3], parameters[4]);
                            }
                            break;
                        case 6:
                            method = pageClass.getMethod(action, String.class, String.class, String.class, String.class, String.class, String.class);
                            parameters = argument.contains(" || ") ? StringUtil.getStringArrayParameter(argument) : null;
                            parameters = StringUtil.getParameterValueFromProperties(parameters);
                            argument = parameters[0] + " || " + parameters[1] + " || " + parameters[2] + " || " + parameters[3] + " || " + parameters[4] + " || " + parameters[5];
                            if (method.getReturnType().equals(boolean.class)) {
                                testResult = (Boolean) method.invoke(pageInstance, parameters[0], parameters[1], parameters[2], parameters[3], parameters[4], parameters[5]);
                                errorMessage = "Verification of " + action + " returned " + String.valueOf(testResult) + "\n";
                            } else {
                                method.invoke(pageInstance, parameters[0], parameters[1], parameters[2], parameters[3], parameters[4], parameters[5]);
                            }
                            break;
                        default:
                            testResult = false;
                            logger.error("Method doesn't exist");
                            errorMessage = "Method doesn't exist\n";
                    }
                } else {
                    testResult = false;
                    logger.error("The page class doesn't exist.");
                    break;
                }
            } catch (ClassNotFoundException e) {
                errorMessage = e.getCause().getMessage();
                testResult = false;
                logger.error("Error description", e);
            } catch (IllegalAccessException e) {
                errorMessage = e.getCause().getMessage();
                testResult = false;
                logger.error("Error description", e);
            } catch (InstantiationException e) {
                errorMessage = e.getCause().getMessage();
                testResult = false;
                logger.error("Error description", e);
            } catch (NoSuchMethodException e) {
                errorMessage = e.getCause().getMessage();
                testResult = false;
                logger.error("Error description", e);
            } catch (InvocationTargetException e) {
                errorMessage = e.getCause().getMessage();
                testResult = false;
                logger.error("Error description", e);
            } catch (ConcurrentModificationException e) {
                errorMessage = "ConcurrentModificationException\n";
//                errorMessage = e.getCause().getMessage();
                testResult = false;
                logger.error(e);
            } catch (Exception e) {
                errorMessage = e.getCause().getMessage();
                testResult = false;
                logger.error(e);
            }

            String testStepResult = testResult ? "Passed" : "Failed";
            logger.info("Test Id: " + testId + " Test Name: " + testName + " Page: " + page + " Action: " + action
                    + " Arguments: " + argument + " Test Step Result: " + testStepResult);
            if (!testResult) {
                errorMessage = errorMessage.substring(0, errorMessage.indexOf("\n"));
                testStepResult = "Failed: " + errorMessage;
            }
            CSVUtil.insertToExecutionDetailFile(testExecutionDetailFile, testPlan, String.valueOf(testId), testName, page, action, argument, testStepResult);

            if (!testResult) {
                try {
                    //pageClass = Class.forName(properties.getProperty("TestBaseClassLocation"));
                    method = pageClass.getMethod("close");
                    method.invoke(pageInstance, null);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        return testResult;
    }

    private boolean doesClassExist(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
