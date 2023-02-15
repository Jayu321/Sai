package crossbrowserTESTNG;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import basebrowsers.Browsers;
import pomModule.HomepageMagentoEcommerse;
import pomModule.HoodiesAndSweatshirtsTopsMenMagentoCommerse;
import pomModule.OsloTrakHoodieMagentoCommerse;
import pomModule.StarkfundamentalhoodieMagentocommerse;
import util.Utility;

public class Verifyhoodie extends Browsers {
	WebDriver driver;
	String testid;
	
	private HomepageMagentoEcommerse mentophoodie;
	private HoodiesAndSweatshirtsTopsMenMagentoCommerse choosehoodie;
	private OsloTrakHoodieMagentoCommerse orderhoodie;
	private StarkfundamentalhoodieMagentocommerse secondordersecondhoodie;
	
	@Parameters("browsers")
	@BeforeTest
	public void launchbrowser(String browser)
	{
 
		if(browser.equals("Chrome"))
		{
			driver=openchrome();
		}
		
		if(browser.equals("firefox"))
		{
			driver=openfirefox();
		}
		
		if(browser.equals("edge"))
		{
			driver =openedge();
		}
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		//driver.manage().window().maximize();
	}
	
	@BeforeClass
	public void createpomobjects()
	{
		mentophoodie= new HomepageMagentoEcommerse(driver);
		choosehoodie = new HoodiesAndSweatshirtsTopsMenMagentoCommerse(driver);
		orderhoodie = new OsloTrakHoodieMagentoCommerse(driver);
		secondordersecondhoodie= new StarkfundamentalhoodieMagentocommerse(driver);
	}
	
	@BeforeMethod
	public void enterurlofapplication()
	{
        System.out.println("Before Method");
		driver.get("https://magento.softwaretestingboard.com/?ref=hackernoon.com");
		
		mentophoodie.MoveOnMENoption();
		
		String expurl="https://magento.softwaretestingboard.com/men/tops-men/hoodies-and-sweatshirts-men.html";
		String acturl=driver.getCurrentUrl();
		Assert.assertEquals(expurl, acturl, "url is correct");
		
		choosehoodie.ClickOnModeListSymbol();
		choosehoodie.SelectFromShortByDropdown();
		
	}
	
	@Test
	public void verifytoproductchoose() throws InterruptedException
	{
		testid="TC_01";
		choosehoodie.getProductPrice();
	    choosehoodie.clickonproduct();
		orderhoodie.SelectSizeOfProduct();
		orderhoodie.SelectCOLOURofProduct();
		orderhoodie.AddQTY();
		orderhoodie.AddProductInCart();
		
	
		Thread.sleep(4000);
		orderhoodie.SelectCartSymbol();
		orderhoodie.ClickOnSeeDetails();
		orderhoodie.ProceedToCheckOut();
		
	}
	
	@Test
	public void VerifySecondproductchoose() throws InterruptedException
	{

		testid="TC_02";
		choosehoodie.getsecondproductprice();
		choosehoodie.getsecondproductdetails();
			
		secondordersecondhoodie.SelectSizeOfProductsecond();
		secondordersecondhoodie.SelectCOLOURofProductsecond();
		secondordersecondhoodie.AddQTYsecond();
		secondordersecondhoodie.AddProductInCartsecond();
		Thread.sleep(4000);
		secondordersecondhoodie.SelectCartSymbolsecond();
		secondordersecondhoodie.ClickOnSeeDetailssecond();
		secondordersecondhoodie.ProceedToCheckOutsecond();

	}

	@AfterMethod
	public void failtestscreenshot(ITestResult result) throws IOException
	{
		if(ITestResult.FAILURE== result.getStatus())
		{
			Utility.capturescreenshot(driver, testid);
		}
	}
	@AfterClass
	public void closewindow()
	{
		mentophoodie= null;
		choosehoodie = null;
		orderhoodie = null;
		secondordersecondhoodie = null;
	}
	
	@AfterTest
	public void  closethebrowser()
	{
		driver.close();
		driver=null;
		System.gc(); // garbage collector
	}

}
