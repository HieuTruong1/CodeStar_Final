package auto.pages;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import auto.common.CommonBase;
import auto.constants.CT_ACCOUNT;

public class RiseFunctions{
	public int initwaitTime = 10;
	private WebDriver driver;
	private String projectTab = "Projects";
	private String optionAll = "All";

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
	
	public String[] splitMonthYear(WebElement e) {
		String monthYear = e.getText();
		String[] split = monthYear.split("\\s");
		return split;
	}
	
	public String getElementMonth (String[] ls) {
		String a = ls[0];
		return a;
	}
	
	public String getElementYear (String[] ls) {
		String a = ls[1];
		return a;
	}
	
	public String[] splitInput (String s) {
		String[] a = s.split("-");
		return a;
	}
	
	public void checkCalendar (WebElement e, String date) {
		WebElement nextBtn = driver.findElement(CT_ACCOUNT.NEXT);
		WebElement prepBtn = driver.findElement(CT_ACCOUNT.PREP);
		int eMonth = convertMonthToNumber(getElementMonth(splitMonthYear(e))) ;
		int eYear = Integer.parseInt(getElementYear(splitMonthYear(e))) ;
		
		String[] splitInputDate = splitInput(date);
		int month = Integer.parseInt(splitInputDate[1]);
		int year = Integer.parseInt(splitInputDate[2]) ;
		
		if (year < eYear) {
			do {
					prepBtn.click();
				 	eMonth = convertMonthToNumber(getElementMonth(splitMonthYear(e)));
					eYear = Integer.parseInt(getElementYear(splitMonthYear(e))) ;
					System.out.println("eMonth: "+eMonth+" eYear"+eYear);
			} while (!(month==eMonth && year == eYear));
		}
		else if (year > eYear) {
			do {
				nextBtn.click();
				eMonth = convertMonthToNumber(getElementMonth(splitMonthYear(e)));
				eYear = Integer.parseInt(getElementYear(splitMonthYear(e))) ;
			} while (!(month==eMonth && year == eYear));
		}
		else {
			if (month < eMonth){
				do {
					prepBtn.click();
				 	eMonth = convertMonthToNumber(getElementMonth(splitMonthYear(e)));
					System.out.println("eMonth: "+eMonth);
				} while (month!=eMonth);
			}
			else if (month > eMonth) {
				do {
					nextBtn.click();
					eMonth = convertMonthToNumber(getElementMonth(splitMonthYear(e)));
				} while (month!=eMonth);
			}
		}
	}
	
	public void pickDateFunction (String date) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(initwaitTime));
		WebElement pickTable = driver.findElement(CT_ACCOUNT.DATE_PICKER);
		WebElement dateMY = driver.findElement(CT_ACCOUNT.MONTH_YEAR);
		checkCalendar(dateMY, date);
		
		String[] splitInputDate = splitInput(date);
		String inputDay = splitInputDate[0];
		WebElement day = driver.findElement(CT_ACCOUNT.DAY(inputDay));
		wait.until(ExpectedConditions.elementToBeClickable(day));
		day.click();
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
		wait.until(ExpectedConditions.elementToBeClickable(CT_ACCOUNT.SIDEBAR_MENU(projectTab)));
		WebElement sideBar = driver.findElement(CT_ACCOUNT.SIDEBAR_MENU(projectTab));
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
		
		WebElement all = driver.findElement(CT_ACCOUNT.SHOW_OPTION(optionAll));
		wait.until(ExpectedConditions.elementToBeClickable(all));
		all.click();
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
	
	public int convertMonthToNumber (String month) {
		int a = 0 ;
		switch(month.trim().toLowerCase()) {
        case "january":
        case "jan":
            a = 1;
        break;

        case "february":
        case "feb":
            a = 2;
        break;

        case "march":
        case "mar":
            a = 3;
        break;

        case "april":
        case "apr":
            a = 4;
        break;

        case "may":
            a = 5;
        break;

        case "june":
        case "jun":
            a = 6;
        break;

        case "july":
        case "jul":
            a = 7;
        break;

        case "august":
        case "aug":
            a = 8;
        break;

        case "september":
        case "sep":
        case "sept":
            a = 9;
        break;

        case "october":
        case "oct":
            a = 10;
        break;

        case "november":
        case "nov":
            a = 11;
        break;

        case "december":
        case "dec":
            a = 12;
        break;
        }
		return a;
	}
	
	
}
