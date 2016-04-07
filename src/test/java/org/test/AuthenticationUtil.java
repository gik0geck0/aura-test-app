package org.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthenticationUtil {

	public static String USERNAME_INPUT = "input[name=\"username\"]";
	public static String PASSWORD_INPUT = "input[name=\"password\"]";
	public static String LOGIN_BUTTON = "input[name=\"submit\"]";

	/*
	 * Start your page load, then call this method to login.
	 * Will assert that the login page loads,
	 * and it is up to the caller to wait for the post-login page to load
	 */
	public static void login(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 20);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(USERNAME_INPUT)));

		driver.findElement(By.cssSelector(USERNAME_INPUT)).sendKeys("user");
		driver.findElement(By.cssSelector(PASSWORD_INPUT)).sendKeys("password");
		driver.findElement(By.cssSelector(LOGIN_BUTTON)).click();
	}
}
