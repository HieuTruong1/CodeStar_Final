package auto.testsuits;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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
import auto.pages.RiseFunctions;

public class ProjectFinalTest extends CommonBase{
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
	private String holdStatus = "Hold";
	private String cancelStatus = "Canceled";
	
	//Tags
	private String onTrackTag = "On track";
	private String urgentTag = "Urgent";
	private String perfectTag = "Perfect";
	private String highPriorTag = "High Priority";
	private String upcomingTag = "Upcoming";
	private String startDate = "21-09-2020";
	private String deadLine = "10-03-2027";
	
	
	@BeforeMethod
	@Parameters ("browserName")
	public void initBrowser (@Optional("c") String browserValue) throws InterruptedException {
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
		WebElement textDropbox = getElementInDOM(CT_ACCOUNT.FILTER_PROJECTS_DROP_BOX);
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
		
		WebElement textDropbox = getElementInDOM(CT_ACCOUNT.FILTER_PROJECTS_DROP_BOX);
		fc.showAllProject();
		
		int tableRow = driver.findElements(CT_ACCOUNT.TABLE_ROW).size();
		int completeCount = fc.countTag(CT_ACCOUNT.STATUS_PROJECTS(completeStatus), completeStatus);
		
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
		
		WebElement textDropbox = getElementInDOM(CT_ACCOUNT.FILTER_PROJECTS_DROP_BOX);
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
		
		WebElement textDropbox = getElementInDOM(CT_ACCOUNT.FILTER_PROJECTS_DROP_BOX);
		fc.showAllProject();
		
		//int count = fc.splitProjectCount(CT_ACCOUNT.TABLE_COUNT_NUMBER);
		int tableRow = driver.findElements(CT_ACCOUNT.TABLE_ROW).size();
		int highPriorCount = fc.countTag(CT_ACCOUNT.PROJECT_TAGS(highPriorTag), highPriorTag);
		
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
		
		WebElement textDropbox = getElementInDOM(CT_ACCOUNT.FILTER_PROJECTS_DROP_BOX);
		fc.showAllProject();
		
		//int count = fc.splitProjectCount(CT_ACCOUNT.TABLE_COUNT_NUMBER);
		int tableRow = driver.findElements(CT_ACCOUNT.TABLE_ROW).size();
		int upcomingCount = fc.countTag(CT_ACCOUNT.PROJECT_TAGS(upcomingTag), upcomingTag);
		
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
		WebElement textDropbox = getElementInDOM(CT_ACCOUNT.FILTER_PROJECTS_DROP_BOX);
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
		fc.searchFilter(highPriorItem);
		
		WebElement textDropbox = getElementInDOM(CT_ACCOUNT.FILTER_PROJECTS_DROP_BOX);
		fc.showAllProject();
		
		//int count = fc.splitProjectCount(CT_ACCOUNT.TABLE_COUNT_NUMBER);
		int tableRow = driver.findElements(CT_ACCOUNT.TABLE_ROW).size();
		int highPriorCount = fc.countTag(CT_ACCOUNT.PROJECT_TAGS(highPriorTag), highPriorTag);
		
		System.out.println("Total row that driver has count: "+tableRow);
		System.out.println("Total row that have tags as 'High Priority'  driver has count: "+highPriorCount);
		
		 // verify number of projects that have High Priority tag compare to total of projects that was count by driver
		assertEquals(highPriorCount, tableRow);
		// verify the dropbox text matched with the current filter option  
		assertEquals(textDropbox.getText(), "High Priority");
		}
	
	@Test (priority = 8) //Create custom filter with "On track" label
	public void createFilterWithOnTrackLablel() throws InterruptedException {
		RiseFunctions fc = new RiseFunctions(driver);
		fc.createFilterWithLabel(onTrackTag, startDate, deadLine);
		fc.createFilterName(onTrackTag);
		
		pauseInSecond(2000);
		fc.showAllProject();
		
		int tableRow = driver.findElements(CT_ACCOUNT.TABLE_ROW).size();
		int ontrackCount = fc.countTag(CT_ACCOUNT.PROJECT_TAGS(onTrackTag), onTrackTag);
		
		System.out.println("Total row that driver has count: "+tableRow);
		System.out.println("Total row that have tags as 'On Track'  driver has count: "+ontrackCount);
		
		 // verify number of projects that have On track tag compare to total of projects that was count by driver
		assertEquals(ontrackCount, tableRow);
		pauseInSecond(2000);
	}
	
	@Test (priority = 9) //Create custom filter with "Urgent" label
	public void createFilterWithUrgenLablel() throws InterruptedException {
		RiseFunctions fc = new RiseFunctions(driver);
		fc.createFilterWithLabel(urgentTag, startDate, deadLine);
		fc.createFilterName(urgentTag);
		
		pauseInSecond(2000);
		fc.showAllProject();
		
		int tableRow = driver.findElements(CT_ACCOUNT.TABLE_ROW).size();
		int urgentCount = fc.countTag(CT_ACCOUNT.PROJECT_TAGS(urgentTag), urgentTag);
		
		System.out.println("Total row that driver has count: "+tableRow);
		System.out.println("Total row that have tags as 'Urgent'  driver has count: "+urgentCount);
		
		 // verify number of projects that have Urgent tag compare to total of projects that was count by driver
		assertEquals(urgentCount, tableRow);
		pauseInSecond(2000);
	}
	
	@Test (priority = 10) //Create custom filter with "Perfect" label
	public void createFilterWithPerfectLablel() throws InterruptedException {
		RiseFunctions fc = new RiseFunctions(driver);
		fc.createFilterWithLabel(perfectTag, startDate, deadLine);
		fc.createFilterName(perfectTag);
		
		pauseInSecond(2000);
		fc.showAllProject();
		
		int tableRow = driver.findElements(CT_ACCOUNT.TABLE_ROW).size();
		int perfectCount = fc.countTag(CT_ACCOUNT.PROJECT_TAGS(perfectTag), perfectTag);
		
		System.out.println("Total row that driver has count: "+tableRow);
		System.out.println("Total row that have tags as 'Perfect'  driver has count: "+perfectCount);
		
		 // verify number of projects that have Perfect tag compare to total of projects that was count by driver
		assertEquals(perfectCount, tableRow);
		pauseInSecond(2000);
	}
	
	@Test (priority = 11) //Not type name when create custom filter
	public void createFilterWithNoName() throws InterruptedException {
		RiseFunctions fc = new RiseFunctions(driver);
		fc.createFilterWithLabel(perfectTag, startDate, deadLine);
		fc.createFilterName("");
		
		WebElement requireMsg = driver.findElement(CT_ACCOUNT.REQUIRE_NAME);
		pauseInSecond(2000);
		 // verify number of projects that have High Priority tag compare to total of projects that was count by driver
		assertTrue(requireMsg.isDisplayed());
		pauseInSecond(2000);
	}
	
	@Test (priority = 12)//Create custom filter with "Hold" Status
	public void createFilterWithHoldStatus() throws InterruptedException {
		RiseFunctions fc = new RiseFunctions(driver);
		fc.createFilterWithStatus(holdStatus, startDate, deadLine);
		fc.createFilterName(holdStatus);
		
		pauseInSecond(2000);
		fc.showAllProject();
		int tableRow = driver.findElements(CT_ACCOUNT.TABLE_ROW).size();
		int holdCount = fc.countTag(CT_ACCOUNT.STATUS_PROJECTS(holdStatus), holdStatus);
		
		System.out.println("Total row that driver has count: "+tableRow);
		System.out.println("Total row that have tags as 'On Track'  driver has count: "+holdCount);
		
		 // verify number of projects that have Hold status compare to total of projects that was count by driver
		assertEquals(holdCount, tableRow);
		pauseInSecond(2000);
	}
	
	@Test (priority = 13)//Create custom filter with "Cancel" Status
	public void createFilterWithCancelStatus() throws InterruptedException {
		RiseFunctions fc = new RiseFunctions(driver);
		fc.createFilterWithStatus(cancelStatus, startDate, deadLine);
		fc.createFilterName(cancelStatus);
		
		pauseInSecond(2000);
		fc.showAllProject();
		int tableRow = driver.findElements(CT_ACCOUNT.TABLE_ROW).size();
		int cancelCount = fc.countTag(CT_ACCOUNT.STATUS_PROJECTS(cancelStatus), cancelStatus);
		
		System.out.println("Total row that driver has count: "+tableRow);
		System.out.println("Total row that have tags as 'On Track'  driver has count: "+cancelCount);
		
		 // verify number of projects that have Hold status compare to total of projects that was count by driver
		assertEquals(cancelCount, tableRow);
		pauseInSecond(2000);
	}
	
	@Test (priority = 14)//Create custom filter with "Completed" Status
	public void createFilterWithCompletedStatus() throws InterruptedException {
		RiseFunctions fc = new RiseFunctions(driver);
		fc.createFilterWithStatus(completeStatus, startDate, deadLine);
		fc.createFilterName(completeStatus);
		
		pauseInSecond(2000);
		fc.showAllProject();
		int tableRow = driver.findElements(CT_ACCOUNT.TABLE_ROW).size();
		int completedCount = fc.countTag(CT_ACCOUNT.STATUS_PROJECTS(completeStatus), completeStatus);
		
		System.out.println("Total row that driver has count: "+tableRow);
		System.out.println("Total row that have tags as 'Completed'  driver has count: "+completedCount);
		
		 // verify number of projects that have Hold status compare to total of projects that was count by driver
		assertEquals(completedCount, tableRow);
		pauseInSecond(2000);
	}
	
	@Test (priority = 15)//Create custom filter with "Open" Status
	public void createFilterWithOpenStatus() throws InterruptedException {
		RiseFunctions fc = new RiseFunctions(driver);
		fc.createFilterWithStatus(openStatus, startDate, deadLine);
		fc.createFilterName(openStatus);
		
		pauseInSecond(2000);
		fc.showAllProject();
		int tableRow = driver.findElements(CT_ACCOUNT.TABLE_ROW).size();
		int completedCount = fc.countTag(CT_ACCOUNT.STATUS_PROJECTS(openStatus), openStatus);
		
		System.out.println("Total row that driver has count: "+tableRow);
		System.out.println("Total row that have tags as 'openStatus'  driver has count: "+completedCount);
		
		 // verify number of projects that have Hold status compare to total of projects that was count by driver
		assertEquals(completedCount, tableRow);
		pauseInSecond(2000);
	}
}
