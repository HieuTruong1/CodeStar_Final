package auto.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import auto.common.CommonBase;

public class RiseFunctions{
	public int initwaitTime = 10;
	private WebDriver driver;

	public RiseFunctions(WebDriver d) {
		this.driver = d;
	}
	
	public void LoginFunction(String email, String pass) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(initwaitTime));
		wait.until(ExpectedConditions.elementToBeClickable(CT_ACCOUNT.RISE_SUBMIT));
		
		WebElement txt_user = driver.findElement(CT_ACCOUNT.RISE_USER);
		WebElement txt_pass = driver.findElement(CT_ACCOUNT.RISE_PASS);
		WebElement btn_submit = driver.findElement(CT_ACCOUNT.RISE_SUBMIT);
		txt_user.clear();
		txt_user.sendKeys(email);
		txt_pass.clear();
		txt_pass.sendKeys(pass);
		btn_submit.click();
	}
	
	
}
