package com.evs.vtiger.automationframework.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class UIUtil {
	public static WebDriver driver;
	public static Map<String,String> DataMap;
	
	public static void fn_launchBrowser(){
		driver=new FirefoxDriver();
	}
	
	public static void fn_getURL(){
		driver.get("http://localhost:8888");
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
        System.out.println(driver.getTitle());
        //Thread.sleep(5000);

	}
	
	public static void fn_init(){
		fn_launchBrowser();
		fn_getURL();
	}
	
	public static void fn_Input(String orelement) throws IOException, FilloException{
	WebElement we=fn_getWebElement(orelement);		
	//Map<String,String> testdata=fn_getTestData();
	String testdata=DataMap.get(orelement);
	System.out.println(testdata);
	we.sendKeys(testdata);
	
	}
	
	public static void fn_Click(String orelement) throws IOException{
		WebElement we=fn_getWebElement(orelement);		
		//String testdata=fn_getTestData(orelement);
		we.click();
		
		}
	public static WebElement fn_getWebElement(String elementname) throws IOException{
		FileInputStream fis=new FileInputStream("OR//or.properties");
		Properties OR=new Properties();
		OR.load(fis);
		String orelementvalue=OR.getProperty(elementname);
		System.out.println(orelementvalue);
		String[] arr=orelementvalue.split("##");
		String locatorvalue=arr[0];
		System.out.println(locatorvalue);
		String locatortype=arr[1];
		System.out.println(locatortype);
		WebElement we;
		switch(locatortype){
		case "NM":we=driver.findElement(By.name(locatorvalue));
		          break;
		case "XP":we=driver.findElement(By.xpath("//input[@name='"+locatorvalue+"']"));
                  break;
		case "LK":we=driver.findElement(By.linkText("Sign Out"));
                  break;
		default:we=null;break;          
		}
		return we;

	}

	public static ArrayList<Map<String,String>> fn_getTestData() throws FilloException{
		
		Fillo fillo=new Fillo();
		Connection con=fillo.getConnection("TestData//testdata.xlsx");
		Recordset rs=con.executeQuery("select * from sheet1 where TestCaseID='TC001'");
		ArrayList<Map<String,String>> datalist=new ArrayList<Map<String,String>>();
		//arrlist=rs.getFieldNames();
		//int count=arrlist.size();
		Map<String,String> mapObj=new HashMap<String,String>();
		String locatorname=null;
		String locatorvalue=null;
		while(rs.next()){
			for(int i=3;i<=6;i=i+2){
			locatorname=rs.getField(i).value();
			locatorvalue=rs.getField(i+1).value();
			mapObj.put(locatorname, locatorvalue);
			}
			datalist.add(mapObj);
		}
	
		return datalist;
	}
}
