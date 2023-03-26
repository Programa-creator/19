package org.example;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class SomeTestClass {

    @BeforeSuite
    public static void beforeSuite() {
        System.out.println("BeforeSuite method");
    }

//    @Test(priority = 1)
    public void testMethod1() {
        System.out.println("Running testMethod1");
    }

//    @Test(priority = 2)
    public static void testMethod2() {
        System.out.println("Running testMethod2");
    }

//    @Test(priority = 3)
    public static void testMethod3() {
        System.out.println("Running testMethod3");
    }

    @AfterSuite
    public static void afterSuite() {
        System.out.println("AfterSuite method");
    }
}
