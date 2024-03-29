package auto.constants;

import org.openqa.selenium.By;

public class CT_ACCOUNT {
	
	//Login
	public static By RISE_USER = By.xpath("//input[@id='email']");
	public static By RISE_PASS = By.xpath("//input[@id='password']");
	public static By RISE_SUBMIT = By.xpath("//button[text()='Sign in']");
	
	public static By TABLE_COUNT_NUMBER = By.id("project-table_info");
	public static By TABLE_SHOW_SELECTION_DROP = By.xpath("//span[@id = 'select2-chosen-2' ]/parent::a");
	public static By TABLE_ROW = By.xpath("//tbody/child::tr");
	public static By STATUS_COLUMN = By.xpath("//thead/descendant::th[text() = 'Status']");
	
	public static By OPEN_PROJECTS = By.xpath("//tbody//descendant::td[text() = 'Open']");
	public static By PROJECT_TAGS (String tagName) {
		return By.xpath("//tr//descendant::span[text() = '"+tagName+"']");
	};
	public static By FILTER_PROJECTS_DROP_BOX = By.xpath("//div[@class = 'filter-section-left']//descendant::button[contains(@class, 'dropdown')]");
	public static By CLEAR_BTN = By.xpath("//a[text() = 'Clear']");
	public static By FILTERS_SEARCH_BOX = By.xpath("//input[@type = 'text' and @placeholder = 'Search']");
	
	//date picker
	public static By CREATE_CUSTOM_FILTER_BTN = By.xpath("//div[@class = 'filter-section-left']/child::div[@class = 'filter-item-box'][3]/descendant::button");
	public static By DATE_PICKER = By.xpath("//div[contains(@class, 'datepicker ')]");
	public static By START_FROM_BTN = By.xpath("//button[@name = 'start_date_from']");
	public static By DEADLINE_BTN = By.xpath("//button[@name = 'start_date_to']");
	public static By MONTH_YEAR = By.xpath("//div[@class='datepicker-days']//descendant::th[@class = 'datepicker-switch']");
	public static By NEXT = By.xpath("//div[@class='datepicker-days']//descendant::th[@class = 'next']");
	public static By PREP = By.xpath("//div[@class='datepicker-days']//descendant::th[@class = 'prev']");
	public static By DAY(String day) {
		return By.xpath("//td[@class = 'day' and text() = '"+day+"']");
	}
	
	//custom label
	public static By STATUS_DROP =	By.xpath("//button[text() = 'Status ']");
	public static By LABEL_DROP = By.xpath("//div[@id = 's2id_autogen3']");
	public static By LABEL_SEARCH = By.xpath("//div[@class = 'select2-search']/child::input");
	public static By ACCEPT_BTN = By.xpath("//button[@class = 'btn btn-default save-filter-button']");
	public static By CANCEL_BTN = By.xpath("//button[@class = 'btn btn-default cancel-filter-button']");
	
	//custom label name
	public static By CUSTTOM_TITLE_INPUT = By.xpath("//input[@placeholder = 'Title']");
	public static By BOOKMARK_CHECKBOX = By.id("bookmark_input");
	public static By SAVE_NAME_BTN = By.xpath("//button[text() = ' Save']");
	
	public static By STATUS_PROJECTS (String status) {
		return By.xpath("//tbody//descendant::td[text() = '"+status+"']");
	} 
	
	public static By STATUS_ITEMS (String status) {
		return By.xpath("//li[@data-name = 'status_id' and  text() = '"+status+"']");
	}
	
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
	
	//Error message 
	public static By REQUIRE_NAME = By.xpath("//span[text() = 'This field is required.']");
	
}
