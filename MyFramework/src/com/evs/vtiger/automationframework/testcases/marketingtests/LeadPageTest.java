package com.evs.vtiger.automationframework.testcases.marketingtests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;
import com.evs.vtiger.automationframework.reusablepages.basepage.BasePage;
import com.evs.vtiger.automationframework.reusablepages.login.LoginPage;
import com.evs.vtiger.automationframework.reusablepages.marketing.leads.MarketingLeadLandingPage;
import com.evs.vtiger.automationframework.utility.UIUtil;

public class LeadPageTest {
	/*public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		LeadPageTest obj=new LeadPageTest();
		obj.createAndVerifyLeads();
	}*/
@Test
public void createAndVerifyLeads() throws InterruptedException, IOException, FilloException
   {	//FirefoxDriver  driver=new FirefoxDriver();
		//driver.get("http://localhost:8888");
	UIUtil.fn_init();
	ArrayList<Map<String,String>> datalist=UIUtil.fn_getTestData();
	int count=datalist.size();
	for(int i=1;i<=count;i++){
		UIUtil.DataMap=datalist.get(i);
		System.out.println(i);
		new LoginPage().validLogin();
		Thread.sleep(5000);
	    //UIUtil.driver.quit();
		
	}
		
	}


}
