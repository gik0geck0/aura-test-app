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
public class AppWebDriverIT {
	@Value("${local.server.port}")
	int serverPort;

    @Test
    public void testAppLoginRedirect() throws Exception {
        WebDriver driver =  new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, 20);

        driver.get("http://localhost:"+serverPort+"/auraTestApp/auraTestApp.app");
        AuthenticationUtil.login(driver);
        // Login should redirect to the originally requested URL
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("body"), "Hello web, from the Aura sample app aura-test-app"));

        driver.close();
    }
}
