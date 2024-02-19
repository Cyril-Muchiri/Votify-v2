package com.votifysoft;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class App {
    @BeforeSuite
    void testBeforeSuite() {
        System.out.println("This is executed!!");
    }

    @Test
    void testActual() {
        System.out.println("Second to be executed!!");
    }

}
