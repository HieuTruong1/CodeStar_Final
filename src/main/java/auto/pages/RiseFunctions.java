package auto.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import auto.common.CommonBase;
import auto.constants.CT_ACCOUNT;
import auto.constants.TEST_DATA;

public class RiseFunctions{
	public int initwaitTime = 10;
	private WebDriver driver;

	public RiseFunctions(WebDriver d) {
		this.driver = d;
	}
	
	public int splitProjectCount (By locator) { //get the total item of the project table 
		int a = 0;
		String projectsCount = driver.findElement(locator).getText();
		String[] slit = projectsCount.split("\\s"); //split the String ignore a space between characters
		
		for (int i = 0 ; i < slit.length ; i++) {
			if (i==2) {
				a =  Integer.parseInt(slit[i]);
			}
		}
		return a;
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
	
	public void getToFilterButton (String button) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(initwaitTime));
		wait.until(ExpectedConditions.elementToBeClickable(CT_ACCOUNT.SIDEBAR_MENU(TEST_DATA.PROJECT_TAB)));
		WebElement sideBar = driver.findElement(CT_ACCOUNT.SIDEBAR_MENU(TEST_DATA.PROJECT_TAB));
		sideBar.click();
		
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.elementToBeClickable(CT_ACCOUNT.RISE_FILTER_ITEMS(button)));
		WebElement filterButton = driver.findElement(CT_ACCOUNT.RISE_FILTER_ITEMS(button));
		filterButton.click();
	}
	
	public void showAllProject() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(initwaitTime));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement drop = driver.findElement(CT_ACCOUNT.TABLE_SHOW_SELECTION_DROP);
		
		js.executeScript("arguments[0].scrollIntoView(true);", drop);
		wait.until(ExpectedConditions.elementToBeClickable(drop));
		drop.click();
		
		WebElement optionAll = driver.findElement(CT_ACCOUNT.SHOW_OPTION(TEST_DATA.OPTION_ALL));
		wait.until(ExpectedConditions.elementToBeClickable(optionAll));
		optionAll.click();
		Thread.sleep(3000);
	}
	
	public void clearFilter () {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(initwaitTime));
		WebElement dropBox = driver.findElement(CT_ACCOUNT.RISE_PROJECTS_DROP_BOX);
		wait.until(ExpectedConditions.elementToBeClickable(dropBox));
		dropBox.click();
		
		WebElement clearBtn = driver.findElement(CT_ACCOUNT.CLEAR_BTN);
		wait.until(ExpectedConditions.elementToBeClickable(clearBtn));
		clearBtn.click();
	}
	
	public int countTag(By locator , String tagName) {
		int a = 0;
		List<WebElement> ls = driver.findElements(locator);
		for (WebElement e : ls){
			if(e.getText().equalsIgnoreCase(tagName)) {
				a++;
			}
		}
		return a;
	}
}
