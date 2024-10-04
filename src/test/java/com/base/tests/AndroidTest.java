package com.base.tests;

import mobile.core.business.pageObjects.MainMobilePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AndroidTest extends BaseMobileTest {


    @BeforeClass
    public void beforeClass() {
        MainMobilePage mainMobilePage = new MainMobilePage(driverManager);
    }


    @Test
    public void test() {
       System.out.println("Test");
    }
}
