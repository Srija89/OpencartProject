package Testcases;

import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.AccountPage;
import PageObjects.HomePage;
import TestBase.baseclass;

public class Tc_001_AccountRegistration  extends baseclass {

	@Test(priority = 1,groups = {"sanity","regression"})
	public void testcase_Account() {
		
		try {
		log.info("***********homepage executing***********");
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickRegister();
		log.info("clicked on register");
		
		AccountPage ap= new AccountPage(driver);
		log.info("entering data");
		ap.setFirstName(randomstring());
		ap.setLastName(randomstring());
		ap.setEmail(randomstring() +"@gmail.com");
		ap.setTelephone(randomeNumber());
		
		String pass =randomAlphaNumeric();
		ap.setPassword(pass);
		ap.setConfirmPassword(pass);
		ap.setPrivacyPolicy();
		ap.clickContinue();
		
	     String msg=ap.getConfirmationMsg();
	     log.info("validating data");
		Assert.assertEquals(msg, "Your Account Has Been Created!");
		}
	catch(Exception e)
	{
		log.error("test failed");
		Assert.fail();
	}
	log.info("****************executing finished************");
}
	
	
}
