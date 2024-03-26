package auto.testsuits;

import java.net.URI;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import auto.common.CommonBase;
import auto.constants.Common_URL;
import auto.pages.CT_ACCOUNT;
import auto.pages.RiseFunctions;
import auto.pages.TEST_DATA;

public class RiseTest extends CommonBase{
	@BeforeMethod
	@Parameters ("browserName")
	public void initBrowser ( @Optional ("chrome") String browserValue) throws InterruptedException {
		setupDriver(browserValue);
		driver.get(Common_URL.URLRise);
	}
	
	@AfterMethod
	public void closeBrowser () {
		driver.close();
	}
	
	@Test
	public void test () throws InterruptedException {
		RiseFunctions fc = new RiseFunctions(driver);
		fc.LoginFunction(TEST_DATA.RISE_USER_ADMIN, TEST_DATA.RISE_PASS);
		clickElement(CT_ACCOUNT.SIDEBAR_MENU(TEST_DATA.SIDEBAR_PROJECT));
	}
}
