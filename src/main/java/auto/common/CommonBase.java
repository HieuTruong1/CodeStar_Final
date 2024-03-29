package auto.common;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonBase {
	public WebDriver driver;
	public int initwaitTime = 40;
	
	public CommonBase() {
	}
	
	public CommonBase(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public WebDriver initChromeDriver() throws InterruptedException {
		System.out.println("Chrome is launching.....");
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		//driver.manage().timeouts().pageLoadTimeout(4,TimeUnit.SECONDS);
		return driver;
	}
	
	public WebDriver initEdgeDriver() throws InterruptedException {
		System.out.println("Edge is launching.....");
		System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\driver\\msedgedriver.exe");
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		//driver.manage().timeouts().pageLoadTimeout(4,TimeUnit.SECONDS);
		return driver;
	}
	
	public WebDriver initFireFoxDriver() throws InterruptedException {
		System.out.println("Firefox  is launching.....");
		System.setProperty("webdriver.firefox.driver", System.getProperty("user.dir") + "\\driver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		//driver.manage().timeouts().pageLoadTimeout(4,TimeUnit.SECONDS);
		return driver;
	}
	
	public WebDriver setupDriver (String browserName) throws InterruptedException {
		switch (browserName.trim().toLowerCase()) {
		case "chrome":
			driver = initChromeDriver();
			break;
		case "edge":
			driver = initEdgeDriver();
			break;
		case "firefox":
			driver = initFireFoxDriver();
			break;
		default:
			System.out.println("Invalid browser name >>>> Starting Chrome .......");
			driver =initChromeDriver();
			break;
		}
		return driver;
	}
	
	public WebElement getElementInDOM(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return driver.findElement(locator);
	}
	
	public void pauseInSecond (long time) {
		try {
			Thread.sleep(time);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void scrollToElement(By locator) {
		WebElement element = driver.findElement(locator);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public void inputText(WebElement element , String text) {
		element.sendKeys(text);
	}
	
	public void clickElement(By locator) {
		WebElement e = getElementInDOM(locator);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(e));
		e.click();
	}
	
	public void changeToPopupWindow () {
		 //find main window 
		String mainWindow = driver.getWindowHandle();
		//get list of popup window after clicked the button
		Set<String> popupWins = driver.getWindowHandles();
		
		//find the popup window and switch to it 
		for (String window : popupWins) {
			if (!window.equals(mainWindow)) {
				driver.switchTo().window(window);
			}
		}
	}
	
	public void clickElementWithJS(By locator) {
		WebElement element = getElementInDOM(locator);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	public void typeInElement(By locator, String key) {
		WebElement e = getElementInDOM(locator);
		e.clear();
		e.sendKeys(key);
	}
	
	public void typeInElementEnter(By locator) {
		WebElement e = getElementInDOM(locator);
		e.sendKeys(Keys.ENTER);
	}
	
	public void typeInElementTab(By locator) {
		WebElement e = getElementInDOM(locator);
		e.clear();
		e.sendKeys(Keys.TAB);
	}
	
	public boolean isPresent(By locator) {
	    try {
	    	 driver.findElement(locator);
	         return true;   
	    } catch (org.openqa.selenium.NoSuchElementException e) {
	    	return false;
	    }
	}
	
	public boolean isElementPresentDOM(By locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));	
			return true;
		} catch (org.openqa.selenium.TimeoutException e) {
			return false;
		}
	}
	
//	public boolean isElementPresent(By locator)
//	{
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.visibilityOf(getElementPresentDOM(locator)));
//		return getElementPresentDOM(locator).isDisplayed();
//	}
}
