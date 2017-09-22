package yahoo;

import java.io.FileInputStream;
import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

@Listeners({ATUReportsListener.class,ConfigurationListener.class,MethodListener.class})
public class SanityTest extends DriverClass
{
	
	{
		System.setProperty("atu.reporter.config","e:\\agility\\atu.properties");
	}
	
   	@Test
	@Parameters({"browser"})
	public void sanityTesting(String br) throws Exception
	{
		if(br.matches("firefox"))
		{
			driver=new FirefoxDriver();
		}
		FileInputStream fin=new FileInputStream("e:\\agility\\testdata.xlsx");
		 XSSFWorkbook wb=new XSSFWorkbook(fin);
		 XSSFSheet ws=wb.getSheet("sanitytest");
		 
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
