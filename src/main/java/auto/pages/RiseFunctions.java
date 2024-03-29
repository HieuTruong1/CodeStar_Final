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
	private String openStatus = "Open";

	public RiseFunctions(WebDriver d) {
		this.driver = d;
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
		CommonBase cm = new CommonBase(driver);
		cm.typeInElement(CT_ACCOUNT.RISE_USER, email);
		cm.typeInElement(CT_ACCOUNT.RISE_PASS, pass);
		cm.clickElement(CT_ACCOUNT.RISE_SUBMIT);
	}
	
	public void getToFilterButton (String button) throws InterruptedException {
		CommonBase cm = new CommonBase(driver);
		cm.clickElement(CT_ACCOUNT.SIDEBAR_MENU(projectTab));
		cm.pauseInSecond(1500); // for firefox
		cm.clickElement(CT_ACCOUNT.RISE_FILTER_ITEMS(button));
	}
	
	public void createFilterWithLabel (String labelName, String startDate, String deadLine) throws InterruptedException {
		CommonBase cm = new CommonBase(driver);
		cm.clickElement(CT_ACCOUNT.SIDEBAR_MENU(projectTab));
		cm.pauseInSecond(1500); //for firefox
		cm.clickElement(CT_ACCOUNT.CREATE_CUSTOM_FILTER_BTN);
		cm.clickElement(CT_ACCOUNT.LABEL_DROP);
		
		WebElement labelSearch = driver.findElement(CT_ACCOUNT.LABEL_SEARCH);
		labelSearch.sendKeys(labelName);
		//cm.typeInElement(CT_ACCOUNT.LABEL_SEARCH, labelName);
		cm.typeInElementEnter(CT_ACCOUNT.LABEL_SEARCH);
		
		cm.clickElement(CT_ACCOUNT.START_FROM_BTN);
		pickDateFunction(startDate);
		cm.clickElement(CT_ACCOUNT.DEADLINE_BTN);
		pickDateFunction(deadLine);
		
		cm.clickElement(CT_ACCOUNT.ACCEPT_BTN);
	}
	
	public void createFilterWithStatus (String statuslName, String startDate, String deadLine) throws InterruptedException {
		CommonBase cm = new CommonBase(driver);
		cm.clickElement(CT_ACCOUNT.SIDEBAR_MENU(projectTab));
		cm.pauseInSecond(3000);
		cm.clickElement(CT_ACCOUNT.CREATE_CUSTOM_FILTER_BTN);
		cm.clickElement(CT_ACCOUNT.STATUS_DROP);
		
		if (statuslName.equalsIgnoreCase(openStatus)) {	
			//open status is active by default
		}
		else {
			//turn of open status 
			cm.clickElement(CT_ACCOUNT.STATUS_ITEMS(openStatus));
			cm.pauseInSecond(1000);
			cm.clickElement(CT_ACCOUNT.STATUS_ITEMS(statuslName));
		}
		cm.clickElement(CT_ACCOUNT.ACCEPT_BTN);
	}
	
	public void createFilterName (String name) {
		CommonBase cm = new CommonBase(driver);
		cm.typeInElement(CT_ACCOUNT.CUSTTOM_TITLE_INPUT, name);
		if (!(driver.findElement(CT_ACCOUNT.BOOKMARK_CHECKBOX).isSelected())) {
			cm.clickElement(CT_ACCOUNT.BOOKMARK_CHECKBOX);
		}
		cm.clickElement(CT_ACCOUNT.SAVE_NAME_BTN);
	}
	
	public void showAllProject() throws InterruptedException {
		CommonBase cm = new CommonBase(driver);
		cm.pauseInSecond(300);
		cm.scrollToElement(CT_ACCOUNT.TABLE_SHOW_SELECTION_DROP);
		cm.clickElement(CT_ACCOUNT.TABLE_SHOW_SELECTION_DROP);
		cm.clickElement(CT_ACCOUNT.SHOW_OPTION(optionAll));
		cm.pauseInSecond(2000);
	}
	
	public void clearFilter () {
		CommonBase cm = new CommonBase(driver);
		cm.clickElement(CT_ACCOUNT.FILTER_PROJECTS_DROP_BOX);
		cm.clickElement(CT_ACCOUNT.CLEAR_BTN);
	}
	
	public void searchFilter (String filterName) {
		CommonBase cm = new CommonBase(driver);
		cm.clickElement(CT_ACCOUNT.SIDEBAR_MENU(projectTab));
		By dropBox = CT_ACCOUNT.FILTER_PROJECTS_DROP_BOX;
		cm.clickElementWithJS(dropBox);
		cm.pauseInSecond(3000);
		By searchBar = CT_ACCOUNT.FILTERS_SEARCH_BOX;
		cm.typeInElement(searchBar, filterName);
		cm.typeInElementEnter(searchBar);
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
	
	public boolean checkActive (String s) {
		boolean check = s.contains("active");
		return check;
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
