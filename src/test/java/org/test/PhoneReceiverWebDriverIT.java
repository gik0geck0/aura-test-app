package org.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ui.twitter.app.TwitterApp;

/**
 * Verify app loads using WebDriver
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(TwitterApp.class)
@WebIntegrationTest(randomPort=true)
public class PhoneReceiverWebDriverIT {

	@Value("${local.server.port}")
	int serverPort;
	
    @Test
    public void testPhoneReceiverHappyPath() throws Exception {
		WebDriver driver =  new FirefoxDriver();
		WebDriverWait wait = new WebDriverWait(driver, 20);

    	try {
			driver.get("http://localhost:"+serverPort+"/phoneTest/phoneApp.app");
			AuthenticationUtil.login(driver);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".uiOutputText")));

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
