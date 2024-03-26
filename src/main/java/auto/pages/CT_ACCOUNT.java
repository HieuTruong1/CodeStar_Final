package auto.pages;

import org.openqa.selenium.By;

public class CT_ACCOUNT {
	public static By RISE_USER = By.xpath("//input[@id='email']");
	public static By RISE_PASS = By.xpath("//input[@id='password']");
	public static By RISE_SUBMIT = By.xpath("//button[text()='Sign in']");
	
	
	public static By SIDEBAR_MENU (String item) {
		return By.xpath("//ul[@id = 'sidebar-menu']/child::li//descendant::span[text() = '"+item+"']");
	}
}
