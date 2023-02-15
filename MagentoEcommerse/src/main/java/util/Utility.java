package util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utility {
	
	public static void capturescreenshot(WebDriver driver,String filename) throws IOException
	{
	
	File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	String DATETIME=new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
	File dest= new File("\"C:\\Users\\Admin\\Pictures\\Saved Pictures\\jayesh.jpg");
	FileHandler.copy(src, dest);
	}
}
