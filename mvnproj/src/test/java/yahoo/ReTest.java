package yahoo;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.net.URL;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;




@Listeners({ATUReportsListener.class,ConfigurationListener.class,MethodListener.class})
public class ReTest  extends DriverClass
{

	//DesiredCapabilities ds;
	
	
	
	{
		System.setProperty("atu.reporter.config","e:\\agility\\atu.properties");
	}
  @Test
  @Parameters({"browser"})
  public void retesting(String br) throws Exception
  {
	  if(br.matches("firefox"))
	  {
		 driver=new FirefoxDriver();	
		  //ds=DesiredCapabilities.firefox();
		  //ds.setPlatform(Platform.WINDOWS);
	  }
	  if(br.matches("chrome"))
	  {		 
		 System.setProperty("webdriver.chrome.driver","d:\\chromedriver.exe");
		 driver=new ChromeDriver();	
		  
		  //ds=DesiredCapabilities.chrome();
		  //ds.setPlatform(Platform.WINDOWS);
	  }
	  
	 // driver=new RemoteWebDriver(new URL("http://192.168.1.222:4444/wd/hub"), ds);
	 FileInputStream fin=new FileInputStream("e:\\agility\\testdata.xlsx");
	 XSSFWorkbook wb=new XSSFWorkbook(fin);
	 XSSFSheet ws=wb.getSheet("retest");
	 
	 String classname,methodname;
	 
	 Row row;
	 for(int r=1;r<=ws.getLastRowNum();r++)
	 {
		 row=ws.getRow(r);
		 if(row.getCell(4).getStringCellValue().matches("yes"))
		 {
	            classname=row.getCell(2).getStringCellValue();
	            methodname=row.getCell(3).getStringCellValue();
	            Class c=Class.forName(classname);  //load the given class into memory and refer to var of Class type
	    		Method m=c.getMethod(methodname, null);   //get the method in the class
	    		Object obj=c.newInstance();   //create object for the class
	    		m.invoke(obj, null);	            
		 }
	 }
	  
  }
}













