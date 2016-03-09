package org.test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Verify app loads using WebDriver
 */
public class AppWebDriverIT {

    @Test
    public void testApp() throws Exception {
        WebDriver driver =  new FirefoxDriver();
        driver.get("http://localhost:8080/auraTestApp/auraTestApp.app");
        Assert.assertTrue("Hello response is wrong", driver.getPageSource().contains("Hello web, from the Aura sample app aura-test-app"));
        driver.close();
    }
}
