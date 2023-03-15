package TestBase;



import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;





public class baseclass {

	
	public static WebDriver driver;
	public Logger log;
	public ResourceBundle rb;
	
	@BeforeClass
	@Parameters("browser")
	public void launchapp(String br)
	
	{ 
		log=LogManager.getLogger(this.getClass());
		rb= ResourceBundle.getBundle("config");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		
		if(br.equals("chrome")) {
		driver=new ChromeDriver(options);
		}
		
		else if(br.equals("edge"))
		{
			driver=new EdgeDriver();
		}
  //     ChromeOptions op=new ChromeOptions();
       // op.setHeadless(true);*/
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("http://localhost/opencart/upload/index.php");
		//driver.get("https://demo.opencart.com/index.php");
		
		driver.manage().window().maximize();
	}
	
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	
	public String randomstring()
	{
		String randoms=RandomStringUtils.randomAlphabetic(5);
	    return (randoms);
	
	}
	
	public String randomeNumber() {
		String generatedString2 = RandomStringUtils.randomNumeric(10);
		return (generatedString2);
	}
	
	public String randomAlphaNumeric() {
		String st = RandomStringUtils.randomAlphabetic(4);
		String num = RandomStringUtils.randomNumeric(3);
		
		return (st+"@"+num);
	}
	
	
	
	
	
	public String ss(String tname) throws IOException
	{
		String timestamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts =(TakesScreenshot)driver;
	   File src =ts.getScreenshotAs(OutputType.FILE);
	String   dest=System.getProperty("user.dir" + "//screenshots//" + tname+ "_"+timestamp+".png");
	   
	try {
	FileUtils.copyFile(src, new File(dest));
	}catch(Exception e)
	{
		e.getMessage();
	}
	
	return dest;
	   
	}


	
}
