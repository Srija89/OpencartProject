package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class basepage 
{
	
	WebDriver driver;
	
	public basepage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements( driver,this);         //when we use pagefactory method dnt need to write driver in evry sentence
	}

}
