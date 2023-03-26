package org.example;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestRunner {
    public static void start(Class<?> clazz) throws Exception {
        Method[] methods = clazz.getDeclaredMethods();

        List<Method> testMethods = new ArrayList<>();
        Method beforeSuiteMethod = null;
        Method afterSuiteMethod = null;
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                testMethods.add(method);
            }
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                if (beforeSuiteMethod == null) {
                    beforeSuiteMethod = method;
                } else {
                    throw new RuntimeException("Multiple methods with @BeforeSuite annotation");
                }
            }
            if (method.isAnnotationPresent(AfterSuite.class)) {
                if (afterSuiteMethod == null) {
                    afterSuiteMethod = method;
                } else {
                    throw new RuntimeException("Multiple methods with @AfterSuite annotation");
                }
            }
        }

        // Sort test methods by priority
        testMethods.sort(Comparator.comparingInt(m -> m.getAnnotation(Test.class).priority()));

        // Invoke methods
        if (beforeSuiteMethod != null) {
            beforeSuiteMethod.invoke(null);
        }
        for (Method method : testMethods) {
            method.invoke(null);
        }
        if (afterSuiteMethod != null) {
            afterSuiteMethod.invoke(null);
        }
    }
}