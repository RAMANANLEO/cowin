package com.java;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base_Class {
	public static WebDriver driver;
	public static String value;

	public static WebDriver get_Browser(String type) {
		if (type.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (type.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", ".\\\\driver\\\\chromedriver.exe");
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		return driver;
	}

	public static void click_On_Element(WebElement element) {
		element.click();
	}

	public static void input_Vale_Of_Element(WebElement element, String value) {
		element.sendKeys(value);

	}

	public static WebDriver get_Url(String url) {
		driver.get(url);
		return driver;

	}

	public static WebDriver close() {
		driver.close();
		return driver;
	}

	public static WebDriver quit() {
		driver.quit();
		return driver;
	}

	public static void clear() {
		clear();

	}

	public static void drop_Down(String type, WebElement element, String value) {
		Select s = new Select(element);
		if (type.equalsIgnoreCase("selectbyvalue")) {
			s.selectByValue(value);
		} else if (type.equalsIgnoreCase("selectbyindex")) {
			int index = Integer.parseInt(value);
			s.selectByIndex(index);

		} else if (type.equalsIgnoreCase("selectbyvisibletext")) {
			s.selectByVisibleText(value);

		}
	}

	public static WebDriver alert() {
		driver.switchTo().alert().accept();
		return driver;

	}

	public static void action(String type, WebElement element) {
		Actions a = new Actions(driver);
		if (type.equalsIgnoreCase("click")) {
			a.click(element).build().perform();
		} else if (type.equalsIgnoreCase("contextClick")) {
			a.contextClick(element).build().perform();
		} else if (type.equalsIgnoreCase("doubleClick")) {
			a.doubleClick(element).build().perform();

		} else if (type.equalsIgnoreCase("clickAndHold")) {
			a.clickAndHold(element).build().perform();

		} else if (type.equalsIgnoreCase("dragAndDrop")) {
			a.dragAndDrop(element, element).build().perform();

		} else if (type.equalsIgnoreCase("moveToElement")) {
			a.moveToElement(element).build().perform();

		}

	}

	public static void robot() throws AWTException {
		Robot r = new Robot();

	}

	public static void screenshot(String path) throws Throwable {
		TakesScreenshot t = (TakesScreenshot) driver;
		File sorce = t.getScreenshotAs(OutputType.FILE);
		File destination = new File(path);
		FileUtils.copyFile(sorce, destination);
	}

	public static void get_Text(WebElement element) {
		String text = element.getText();
		System.out.println(text);

	}

	public static void get_Options(WebElement element) {
		Select s = new Select(element);
		List<WebElement> options = s.getOptions();
		for (int i = 1; i < options.size(); i++) {
			String text = options.get(i).getText();
			System.out.println(text);

		}

	}

	public static void get_Title(WebDriver driver) {
		String title = driver.getTitle();
		System.out.println(title);

	}

	public static void navigate_To(String value) {
		driver.navigate().to(value);

	}

	public static void navigate_Forward() {
		driver.navigate().forward();

	}

	public static void navigate_Backward() {
		driver.navigate().back();

	}

	public static void navigate_Refersh() {
		driver.navigate().refresh();
	}

	public static void is_Displayed(WebElement element) {
		boolean displayed = element.isDisplayed();
		System.out.println(displayed);

	}

	public static void is_Enabled(WebElement element) {
		boolean enabled = element.isEnabled();
		System.out.println(enabled);

	}

	public static void is_Selected(WebElement element) {
		boolean selected = element.isSelected();
		System.out.println(selected);

	}

	public static void scroll_Up_Down(String type) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(2000);
		js.executeScript(type);

	}

	public static void scroll_Intoview(WebElement element) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(5000);
		js.executeScript("arguments[0].scrollIntoView();", element);

	}

	public static void implicity_Wait() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public static void explicity_Wait(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void get_Current_Url() {
		driver.getCurrentUrl();

	}

	public static void get_Attribute_Value(WebElement element) {
		String attributevalue = element.getAttribute("value");
		System.out.println(attributevalue);

	}

	public static void get_First_Selected_Option(WebElement element) {
		Select s = new Select(element);
		s.getFirstSelectedOption();

	}

	public static void get_All_selected_Option(WebElement element) {
		Select s = new Select(element);
		s.getAllSelectedOptions();

	}

	public static String read_Data_From_Exel(String path, int row_Index, int row, int cell) throws IOException {
		File f = new File(path);
		FileInputStream fis = new FileInputStream(f);
		Workbook w = new XSSFWorkbook(fis);
		Sheet sheet = w.getSheetAt(row_Index);
		Row r = sheet.getRow(row);
		Cell c = r.getCell(cell);
		CellType cellType = c.getCellType();
		if (cellType.equals(CellType.STRING)) {
			value = c.getStringCellValue();
		} else if (cellType.equals(CellType.NUMERIC)) {
			double numericCellValue = c.getNumericCellValue();
			int data = (int) numericCellValue;
			value = String.valueOf(data);

		}
		return value;

	}

}
