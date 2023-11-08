package genericLibraries;

import java.io.File;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class webDriverUtility {
	WebDriver driver;

	/**
	 * This method is used to launch user desired browser
	 * 
	 * @param browser
	 * @return
	 */
	public WebDriver launchBrowser(String browser) {
		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");
			driver = new ChromeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;

		case "edge":
			WebDriverManager.firefoxdriver().setup();
			driver = new EdgeDriver();
			break;

		default:
			System.out.println("Invalid browser info");
		}
		driver.manage().window().maximize();
		return driver;

	}

	/**
	 * this method is used to navigate to url
	 * 
	 * @param url
	 */
	public void navigateToApp(String url) {
		driver.get(url);
	}

	/**
	 * This method is an implicit wait statement
	 * 
	 * @param time
	 */
	public void WaitTillElementFound(long time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	/**
	 * This method is used to wait until the element is visible
	 * 
	 * @param time
	 * @param element
	 * @return
	 */
	public WebElement explicitWait(long time, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * This methos is used to wait until element is enabled
	 * 
	 * @param element
	 * @param time
	 * @return
	 */
	public WebElement explicitWait(WebElement element, long time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * This method is used until title of the webpage appears
	 * 
	 * @param title
	 * @param time
	 * @return
	 */
	public Boolean explicitWait(String title, long time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.titleContains(title));

	}

	/**
	 * This method is used to mousehover to element
	 * 
	 * @param element
	 */
	public void mouseHoverToElement(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	/**
	 * This method is used to double click on element
	 * 
	 * @param element
	 */
	public void doubleClickOnElement(WebElement element) {
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
	}

	/**
	 * This method is used to right click on element
	 * 
	 * @param element
	 */
	public void rightClick(WebElement element) {
		Actions action = new Actions(driver);
		action.contextClick(element).perform();
	}

	/**
	 * This method is used to drag and drop an element
	 * 
	 * @param element
	 * @param target
	 */
	public void DragAndDropElement(WebElement element, WebElement target) {
		Actions action = new Actions(driver);
		action.dragAndDrop(element, target).perform();
	}

	/**
	 * This method is used to select an element from dropdown based on index
	 * 
	 * @param element
	 * @param index
	 */
	public void handleDropdown(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	/**
	 * This method is used to select an element from dropdown based on text
	 * 
	 * @param element
	 * @param text
	 */
	public void handleDropdown(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);

	}

	/**
	 * This method is used to select an element from dropdown based on value
	 * 
	 * @param element
	 * @param value
	 */
	public void handleDropdown(String value, WebElement element) {
		Select select = new Select(element);
		select.selectByValue(value);

	}

	/**
	 * This method is used to switch to frame based on index
	 * 
	 * @param index
	 */
	public void switchToFrame(int index) {
		driver.switchTo().frame(index);
	}

	/**
	 * This method is used to switch to frame based on id or name attribute
	 * 
	 * @param idorName
	 */
	public void switchToFrame(String idorName) {
		driver.switchTo().frame(idorName);
	}

	/**
	 * This method is used to switch to frame based on frame element reference
	 * 
	 * @param frameElement
	 */
	public void switchToFrame(WebElement frameElement) {
		driver.switchTo().frame(frameElement);

	}

	/**
	 * This method is used to switch back from frame
	 */
	public void switchBackFromFrame() {
		driver.switchTo().defaultContent();
	}

	/**
	 * This method is used to capture window screenshot
	 * 
	 * @param driver
	 * @param className
	 * @param jutil
	 */
	public void takeSreenshot(WebDriver driver, String className, javautility jutil) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./Screenshot/" + className + " " + jutil.getCurrentTime() + ".png");

		try {
			FileUtils.copyFile(src, dest);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method is used to scroll till element
	 * 
	 * @param element
	 */
	public void scrollTillElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoview(true)", element);

	}

	/**
	 * This method is used to handle alert
	 * 
	 * @param status
	 */
	public void handleAlert(String status) {
		Alert alert = driver.switchTo().alert();
		if (status.equalsIgnoreCase("ok"))
			alert.accept();
		else
			alert.dismiss();

	}

	/**
	 * This method is used to switch to child browser
	 */
	public void switchToChildBrowser() {
		Set<String> set = driver.getWindowHandles();
		for (String windowID : set) {
			driver.switchTo().window(windowID);
		}
	}

	/**
	 * This method returns parent browser address
	 * 
	 * @return
	 */
	public String getParentWindowID() {
		return driver.getWindowHandle();

	}

	/**
	 * This method is used to switch to specified window
	 * 
	 * @param windowID
	 */
	public void switchToWindow(String windowID) {
		driver.switchTo().window(windowID);
	}

	/**
	 * This method is used to close current window
	 */
	public void closeCurrntWindow() {
		driver.close();
	}

	/**
	 * This method is used to close all the window
	 */
	public void closeAllWindow() {
		driver.quit();
	}
}
