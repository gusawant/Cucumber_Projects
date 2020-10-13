package stepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PostJob {

	WebDriver driver;
	WebDriverWait wait;

	@Given("^Open browser with Alchemy Jobs site$")
	public void openBrowser() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 30);

		driver.get("https://alchemy.hguy.co/jobs");
		driver.manage().window().maximize();
	}

	@When("^Go to Post a Job page$")
	public void goToPostAJobPage() {
		driver.findElement(By.linkText("Post a Job")).click();
	}

	@Then("^Enter the \"(.*)\", \"(.*)\", \"(.*)\", \"(.*)\", \"(.*)\", \"(.*)\" details and click on the Preview button$")
	public void fillJobDetails(String email, String jobTitle, String location, String description,
			String applicationEmail, String companyName) {
		driver.findElement(By.id("create_account_email")).clear();
		driver.findElement(By.id("create_account_email")).sendKeys(email);

		driver.findElement(By.id("job_title")).clear();
		driver.findElement(By.id("job_title")).sendKeys(jobTitle);

		driver.findElement(By.id("job_location")).clear();
		driver.findElement(By.id("job_location")).sendKeys(location);

		driver.findElement(By.id("job_location")).clear();
		driver.findElement(By.id("job_location")).sendKeys(location);

		WebElement iframe = driver.findElement(By.id("job_description_ifr"));
		driver.switchTo().frame(iframe);
		driver.findElement(By.xpath("/html/body")).sendKeys(description);
		driver.switchTo().defaultContent();

		driver.findElement(By.id("application")).clear();
		driver.findElement(By.id("application")).sendKeys(applicationEmail);

		driver.findElement(By.id("company_name")).clear();
		driver.findElement(By.id("company_name")).sendKeys(companyName);

		driver.findElement(By.name("submit_job")).click();
	}

	@Then("^Click submit$")
	public void clickOnSubmitButton() {
		driver.findElement(By.id("job_preview_submit_button")).click();
	}

	@Then("^Go to the Jobs page$")
	public void gotoJobsPage() {
		driver.findElement(By.linkText("Jobs")).click();
	}

	@And("^Confirm job listing \"(.*)\" is shown on page$")
	public void confirmJobListing(String jobTitle1) {
		driver.findElement(By.linkText("Jobs")).click();

		driver.findElement(By.id("search_keywords")).clear();
		driver.findElement(By.id("search_keywords")).sendKeys(jobTitle1);

		driver.findElement(By.xpath("//input[@value='Search Jobs']")).click();

		wait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div/h3[text()='" + jobTitle1 + "']")));
		String actualJobTitle = driver.findElement(By.xpath("//div/h3[text()='" + jobTitle1 + "']")).getText();

		Assert.assertEquals(actualJobTitle, jobTitle1);
	}

	@And("^Close Browser$")
	public void closeTheBrowser() throws Throwable {
		driver.quit();
	}

}
