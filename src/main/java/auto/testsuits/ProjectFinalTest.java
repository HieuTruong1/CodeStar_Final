package auto.testsuits;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import auto.common.CommonBase;
import auto.constants.CT_ACCOUNT;
import auto.constants.Common_URL;
import auto.constants.TEST_DATA;
import auto.pages.RiseFunctions;

public class ProjectFinalTest extends CommonBase{
	
	private String userAdmin = TEST_DATA.RISE_USER_ADMIN;
	private String userClient = TEST_DATA.RISE_USER_CLIENT;
	private String password = TEST_DATA.RISE_PASS;
	private String projectTab = TEST_DATA.PROJECT_TAB;
	
	private String filtersItem = TEST_DATA.FILTERS;
	private String allProjectsItem = TEST_DATA.ALL_PROJECTS;
	private String completedItem = TEST_DATA.COMPLETED;
	private String highPriorItem = TEST_DATA.HIGH_PRIORITY;
	private String openItem = TEST_DATA.OPEN_PROJECTS;
	private String upComingItem = TEST_DATA.UPCOMING;
	
	private String allProjectsBTN = TEST_DATA.ALL_PROJECT_BTN;
	private String completedBTN = TEST_DATA.COMPLETED_BTN;
	private String highPriorBTN = TEST_DATA.HIGH_PRIORITY_BTN;
	private String openBTN = TEST_DATA.OPEN_PROJECTS_BTN;
	private String upComingBTN = TEST_DATA.UPCOMING_BTN;
	
	private String optionAll = TEST_DATA.OPTION_ALL;
	private String option100 = TEST_DATA.OPTION_100;
	private String option50 = TEST_DATA.OPTION_50;
	private String option25 = TEST_DATA.OPTION_25;
	private String option10 = TEST_DATA.OPTION_10;
	
	private String completeStatus = TEST_DATA.COMPLETE_STATUS;
	private String openStatus = TEST_DATA.OPEN_STATUS;
	private String highPriorTag = TEST_DATA.HIGH_PRIOR_TAG;
	private String upcomingTag = TEST_DATA.UPCOMING_TAG;
	
	
	@BeforeMethod
	@Parameters ("browserName")
	public void initBrowser (@Optional("firefox") String browserValue) throws InterruptedException {
		setupDriver(browserValue);
		driver.get(Common_URL.URLRise);
		RiseFunctions fc = new RiseFunctions(driver);
		fc.LoginFunction(userAdmin, password);
	}
	
	@AfterMethod
	public void closeBrowser () {
		driver.close();
	}
	
	@Test (priority = 1) //Filter projects with “All projects” tag
	public void fillterProjectsWithAllProjectsTag () throws InterruptedException {
		RiseFunctions fc = new RiseFunctions(driver);
		fc.getToFilterButton(allProjectsBTN);
		WebElement textDropbox = getElementInDOM(CT_ACCOUNT.RISE_PROJECTS_DROP_BOX);
		fc.showAllProject();
		
		int count = fc.splitProjectCount(CT_ACCOUNT.TABLE_COUNT_NUMBER);
		int tableRow = driver.findElements(CT_ACCOUNT.TABLE_ROW).size();
		
		System.out.println("The number that the website has show below : "+driver.findElement(CT_ACCOUNT.TABLE_COUNT_NUMBER).getText());
		System.out.println("Total row that driver has count: "+tableRow);
		
		// verify number of project shown in the table is matched with the total number show at the bottom of the table
		assertEquals(tableRow, count); 
		// verify the dropbox text matched with the current filter option
		assertEquals(textDropbox.getText(), "All projects");   
	}
	
	@Test (priority = 2) //Filter projects with “Completed” tag
	public void fillterProjectsWithCompletedTag () throws InterruptedException {
		RiseFunctions fc = new RiseFunctions(driver);
		fc.getToFilterButton(completedBTN);
		
		WebElement textDropbox = getElementInDOM(CT_ACCOUNT.RISE_PROJECTS_DROP_BOX);
		fc.showAllProject();
		
		int tableRow = driver.findElements(CT_ACCOUNT.TABLE_ROW).size();
		int completeCount = fc.countTag(CT_ACCOUNT.COMPLETE_PROJECTS, completeStatus);
		
		System.out.println("Total row that driver has count: "+tableRow);
		System.out.println("Total row that have status as 'Completed'  driver has count: "+completeCount);
		
		// verify number of projects that have status Complete compare to total of projects that was count by driver
		assertEquals(completeCount, tableRow); 
		// verify the dropbox text matched with the current filter option 
		assertEquals(textDropbox.getText(), "Completed");  
	}
	
	@Test (priority = 3) //Filter projects with “Open projects” tag
	public void fillterProjectsWithOpenTag () throws InterruptedException {
		RiseFunctions fc = new RiseFunctions(driver);
		fc.getToFilterButton(openBTN);
		
		WebElement textDropbox = getElementInDOM(CT_ACCOUNT.RISE_PROJECTS_DROP_BOX);
		fc.showAllProject();
		
		int tableRow = driver.findElements(CT_ACCOUNT.TABLE_ROW).size();
		int openCount = fc.countTag(CT_ACCOUNT.OPEN_PROJECTS, openStatus);
		
		System.out.println("Total row that driver has count: "+tableRow);
		System.out.println("Total row that have status as 'Completed'  driver has count: "+openCount);
		
		// verify number of projects that have status Open compare to total of projects that was count by driver
		assertEquals(openCount, tableRow); 
		// verify the dropbox text matched with the current filter option  
		assertEquals(textDropbox.getText(), "Open projects"); 
	}
	
	@Test (priority = 4) //Filter projects with “High Priority” tag
	public void fillterProjectsWithHightPriorityTag () throws InterruptedException {
		RiseFunctions fc = new RiseFunctions(driver);
		fc.getToFilterButton(highPriorBTN);
		
		WebElement textDropbox = getElementInDOM(CT_ACCOUNT.RISE_PROJECTS_DROP_BOX);
		fc.showAllProject();
		
		//int count = fc.splitProjectCount(CT_ACCOUNT.TABLE_COUNT_NUMBER);
		int tableRow = driver.findElements(CT_ACCOUNT.TABLE_ROW).size();
		int highPriorCount = fc.countTag(CT_ACCOUNT.HIGH_PRIOR_PROJECTS, highPriorTag);
		
		System.out.println("Total row that driver has count: "+tableRow);
		System.out.println("Total row that have tags as 'High Priority'  driver has count: "+highPriorCount);
		
		 // verify number of projects that have High Priority tag compare to total of projects that was count by driver
		assertEquals(highPriorCount, tableRow);
		// verify the dropbox text matched with the current filter option  
		assertEquals(textDropbox.getText(), "High Priority"); 
	}
	
	@Test (priority = 5) //Filter projects with “Upcoming” tag
	public void fillterProjectsWithUpcomingTag () throws InterruptedException {
		RiseFunctions fc = new RiseFunctions(driver);
		fc.getToFilterButton(upComingBTN);
		
		WebElement textDropbox = getElementInDOM(CT_ACCOUNT.RISE_PROJECTS_DROP_BOX);
		fc.showAllProject();
		
		//int count = fc.splitProjectCount(CT_ACCOUNT.TABLE_COUNT_NUMBER);
		int tableRow = driver.findElements(CT_ACCOUNT.TABLE_ROW).size();
		int upcomingCount = fc.countTag(CT_ACCOUNT.UPCOMING_PROJECTS, upcomingTag);
		
		System.out.println("Total row that driver has count: "+tableRow);
		System.out.println("Total row that have tags as 'Upcoming'  driver has count: "+upcomingCount);
		
		// verify number of projects that have High Priority tag compare to total of projects that was count by driver
		assertEquals(upcomingCount, tableRow); 
		// verify the dropbox text matched with the current filter option 
		assertEquals(textDropbox.getText(), "Upcoming");  
	}
	
	@Test (priority = 6) //Clear the current filter 
	public void clearFilterSuccessfully () throws InterruptedException {
		RiseFunctions fc = new RiseFunctions(driver);
		fc.getToFilterButton(upComingBTN);
		WebElement textDropbox = getElementInDOM(CT_ACCOUNT.RISE_PROJECTS_DROP_BOX);
		pauseInSecond(3500);
		fc.clearFilter();
		pauseInSecond(2000);
		fc.showAllProject();
		int count = fc.splitProjectCount(CT_ACCOUNT.TABLE_COUNT_NUMBER);
		int tableRow = driver.findElements(CT_ACCOUNT.TABLE_ROW).size();
		
		System.out.println("The number that the website has show below : "+driver.findElement(CT_ACCOUNT.TABLE_COUNT_NUMBER).getText());
		System.out.println("Total row that driver has count: "+tableRow);
		//verify the text of the filter drop down list is change back to text 'Filters' after clicked the clear button
		assertEquals(textDropbox.getText(), "Filters");
		// verify number of project shown in the table is matched with the total number show at the bottom of the table
		assertEquals(tableRow, count); 
	}
	
	@Test (priority = 7) //Search filter (Ex: Data = "High Priority")
	public void searchFilterSuccessfully () throws InterruptedException {
		RiseFunctions fc = new RiseFunctions(driver);
		clickElement(CT_ACCOUNT.SIDEBAR_MENU(projectTab));
		By dropBox = CT_ACCOUNT.RISE_PROJECTS_DROP_BOX;
		clickElementWithJS(dropBox);
		pauseInSecond(3000);
		By searchBar = CT_ACCOUNT.FILTERS_SEARCH_BOX;
		typeInElement(searchBar, highPriorItem);
		typeInElementEnter(searchBar);
		
		WebElement textDropbox = getElementInDOM(CT_ACCOUNT.RISE_PROJECTS_DROP_BOX);
		fc.showAllProject();
		
		//int count = fc.splitProjectCount(CT_ACCOUNT.TABLE_COUNT_NUMBER);
		int tableRow = driver.findElements(CT_ACCOUNT.TABLE_ROW).size();
		int highPriorCount = fc.countTag(CT_ACCOUNT.HIGH_PRIOR_PROJECTS, highPriorTag);
		
		System.out.println("Total row that driver has count: "+tableRow);
		System.out.println("Total row that have tags as 'High Priority'  driver has count: "+highPriorCount);
		
		 // verify number of projects that have High Priority tag compare to total of projects that was count by driver
		assertEquals(highPriorCount, tableRow);
		// verify the dropbox text matched with the current filter option  
		assertEquals(textDropbox.getText(), "High Priority");
		}	
}
