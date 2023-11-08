package pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class welcomepage {
//Declaration
	@FindBy(xpath="//a[@class='navbar-brand']")
	private WebElement logo;
	
	@FindBy(xpath="//a[text()='LOGIN']")
	private WebElement loginButton;
	
	//Initilalization
	public welcomepage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	//Utilization
	public String getLogo() {
		return logo.getText();
	}
	
	public void clickLogoButton() {
		loginButton.click();
	}

}