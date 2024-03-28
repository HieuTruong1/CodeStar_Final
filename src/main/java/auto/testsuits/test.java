package auto.testsuits;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import auto.common.CommonBase;
import auto.constants.CT_ACCOUNT;
import auto.constants.Common_URL;
import auto.pages.RiseFunctions;

public class test extends CommonBase{
	private String userAdmin = "admin@demo.com";
	private String userClient = "client@demo.com";
	private String password = "riseDemo";
	private String projectTab = "Projects";
	
	//Drop down box item
	private String filtersItem = "Filters";
	private String allProjectsItem = "All projects";
	private String completedItem = "Completed";
	private String highPriorItem = "High Priority";
	private String openItem = "Open projects";
	private String upComingItem = "Upcoming";
	
	//Filter bar items
	private String allProjectsBTN = "ecmkusuchl";
	private String completedBTN = "tiyhayaffv";
	private String highPriorBTN = "nkuyhedude";
	private String openBTN = "ohenbtrdgc";
	private String upComingBTN = "gncqxkcoto";
	
	//Show option
	private String optionAll = "All";
	private String option100 = "100";
	private String option50 = "50";
	private String option25 = "25";
	private String option10 = "10";
	
	//Project status
	private String completeStatus = "Completed";
	private String openStatus = "Open";
	private String highPriorTag = "High Priority";
	private String upcomingTag = "Upcoming";
	private String startDate = "21-09-2020";
	private String deadLine = "10-03-2027";
	
	@BeforeMethod
	@Parameters ("browserName")
	public void initBrowser (@Optional("edge") String browserValue) throws InterruptedException {
		setupDriver(browserValue);
		driver.get(Common_URL.URLRise);
		RiseFunctions fc = new RiseFunctions(driver);
		fc.LoginFunction(userAdmin, password);
	}
	
	@AfterMethod
	public void closeBrowser () {
		driver.close();
	}
	
	@Test 
	public void test12() throws InterruptedException {
		RiseFunctions fc = new RiseFunctions(driver);
		clickElement(CT_ACCOUNT.SIDEBAR_MENU(projectTab));
		clickElement(CT_ACCOUNT.CREATE_CUSTOM_FILTER_BTN);
		clickElement(CT_ACCOUNT.START_FROM_BTN);
		fc.pickDateFunction(startDate);
		clickElement(CT_ACCOUNT.DEADLINE_BTN);
		fc.pickDateFunction(deadLine);
		pauseInSecond(5000);
	}
}
