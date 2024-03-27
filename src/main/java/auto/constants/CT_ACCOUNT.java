package auto.constants;

import org.openqa.selenium.By;

public class CT_ACCOUNT {
	public static By RISE_USER = By.xpath("//input[@id='email']");
	public static By RISE_PASS = By.xpath("//input[@id='password']");
	public static By RISE_SUBMIT = By.xpath("//button[text()='Sign in']");
	public static By TABLE_COUNT_NUMBER = By.id("project-table_info");
	public static By TABLE_SHOW_SELECTION_DROP = By.xpath("//span[@id = 'select2-chosen-2' ]/parent::a");
	public static By TABLE_ROW = By.xpath("//tbody/child::tr");
	public static By STATUS_COLUMN = By.xpath("//thead/descendant::th[text() = 'Status']");
	public static By COMPLETE_PROJECTS = By.xpath("//tbody//descendant::td[text() = 'Completed']");
	public static By OPEN_PROJECTS = By.xpath("//tbody//descendant::td[text() = 'Open']");
	public static By HIGH_PRIOR_PROJECTS = By.xpath("//tr//descendant::span[text() = 'High Priority']");
	public static By UPCOMING_PROJECTS = By.xpath("//tr//descendant::span[text() = 'Upcoming']");
	public static By RISE_PROJECTS_DROP_BOX = By.xpath("//div[@class = 'filter-section-left']//descendant::button[contains(@class, 'dropdown')]");
	public static By CLEAR_BTN = By.xpath("//a[text() = 'Clear']");
	public static By FILTERS_SEARCH_BOX =By.xpath("//input[@type = 'text' and @placeholder = 'Search']");
	
	
	public static By SHOW_OPTION (String options) {
		return By.xpath("//div[text() = '"+options+"']");
	}
	
	public static By RISE_FILTER_ITEMS (String button) {
		return By.xpath("//div[@class = 'filter-section-left']//descendant::button[@data-id = '"+button+"'] ");
	} 
	
	public static By RISE_PROJECTS_DROP_BOX (String item) {
		return By.xpath("//div[@class = 'filter-item-box']//descendant::button[normalize-space() = '"+item+"']");
	} 
	public static By SIDEBAR_MENU (String item) {
		return By.xpath("//ul[@id = 'sidebar-menu']/child::li//descendant::span[text() = '"+item+"']");
	}
	
}
