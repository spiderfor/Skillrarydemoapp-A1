package genericLibraries;

import org.apache.hc.core5.net.Host;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import pompages.AddNewCategoryPage;
import pompages.AddNewCoursePage;
import pompages.AddNewUserPage;
import pompages.AdminHomePage;
import pompages.CategoryPage;
import pompages.CourseListPage;
import pompages.LoginPage;
import pompages.UsersPage;
import pompages.welcomepage;

public class BaseClass {
	//@BeforeSuite
	//@BeforeTest
	
	protected PropertiesUtility property;
	protected ExcelUtility excel;
	protected javautility jutil;
	protected webDriverUtility webUtil;
	protected WebDriver driver;
	
	public static  WebDriver sdriver;
	public static javautility sjutil;
	
	protected welcomepage welcome;
	protected LoginPage login;
	protected AdminHomePage home;
	protected UsersPage users;
	protected CourseListPage course;
	protected CategoryPage category;
	protected AddNewUserPage addUser;
	protected AddNewCoursePage addCourse;
	protected AddNewCategoryPage addCategory;
	
	
	@BeforeClass
	public void classConfig() {
		property = new PropertiesUtility();
		excel = new ExcelUtility();
		jutil = new javautility();
		webUtil = new webDriverUtility();
		
		property.propertiesinitialization(IConstantpath.PROPERTIES_PATH);
		driver = webUtil.launchBrowser(property.readFromProperties("browser"));
		
		sdriver = driver;
		sjutil = jutil;
	}
	
	
	@BeforeMethod
	public void methodconfig(){
		excel.excelinitialization(IConstantPath.EXCEL_PATH);
		
		welcome = new welcomepage(driver);
		login = new LoginPage(driver);
		home = new AdminHomePage(driver);
		users = new UsersPage(driver);
		course = new CourseListPage(driver);
		category = new CategoryPage(driver);
		addUser = new AddNewUserPage(driver);
		addCourse = new AddNewCoursePage(driver);
		addCategory = new AddNewCategoryPage(driver);
		
		webUtil.navigateToApp(property.readFromProperties("url"));
		
		long time = Long.parseLong(property.readFromProperties("timeouts"));
		webUtil.waitTillElementFound(time);
		
		welcome.clickLoginButton();
		login.setEmail(property.readFromProperties("username"));
		login.setPassword(property.readFromProperties("password"));
		login.clickLogin();
		
		
	}
	
	
	@AfterMethod
	public void methodTearDown() {
		excel.closeExcel();
		home.signOutofApp();
		
	}
	@AfterClass
	public void classTeardown() {
		webUtil.closeAllWindows();
	}
	
	//@AfterTest
	//@AfterSuite
	
	

}