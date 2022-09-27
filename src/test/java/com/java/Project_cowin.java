package com.java;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Project_cowin extends Base_Class{
	static String expectedoption = "Tamil Nadu";

	public static void main(String[] args) throws InterruptedException {
		get_Browser("chrome");
		driver.get("https://www.cowin.gov.in/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement dropdown = driver.findElement(By.xpath("//div[@class=\"mat-select-arrow-wrapper ng-tns-c69-352\"]"));
		js.executeScript("arguments[0].scrollIntoView;", dropdown);
		js.executeScript("arguments[0].click", dropdown);
		
		Select s = new Select(dropdown);
		List<WebElement> options = s.getOptions();
		for (int i = 0; i < options.size(); i++) {
			String particularoption = options.get(i).getText();
			if (expectedoption.equalsIgnoreCase(particularoption)) {
				s.selectByVisibleText(expectedoption);
				break;
				
			}
			
			
		}
	}

}
