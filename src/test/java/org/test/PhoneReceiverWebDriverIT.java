package org.test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Verify app loads using WebDriver
 */
public class PhoneReceiverWebDriverIT {
	
    @Test
    public void testPhoneReceiverHappyPath() throws Exception {
		WebDriver driver =  new FirefoxDriver();

    	try {
			driver.get("http://localhost:8080/phoneTest/phoneApp.app");

			// Validate initial state
			Assert.assertEquals("No Phone Number", driver.findElement(By.cssSelector(".uiOutputText")).getText());
			
			// Expect that entering text into the box will get shown in the outputText
			final String testText = "Test Text";
			driver.findElement(By.cssSelector("input")).sendKeys(testText);
			
			driver.findElement(By.cssSelector("button")).click();
			
			WebDriverWait driverWait = new WebDriverWait(driver, 10);
			driverWait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".uiOutputText"), testText));
    	} finally {
			driver.close();
    	}
    }
}
