package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends basepage
{

	public HomePage(WebDriver driver) {
		super(driver);
		
	}

	
	@FindBy(xpath = "//span[text()='My Account']")
	WebElement lnkMyaccount;

	@FindBy(linkText = "Register")
	WebElement lnkRegister;

	// Action Methods
	public void clickMyAccount() {
		lnkMyaccount.click();
	}

	public void clickRegister() {
		lnkRegister.click();
	}
	
	
	
}
