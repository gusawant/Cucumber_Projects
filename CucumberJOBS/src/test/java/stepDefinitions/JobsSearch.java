package stepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class JobsSearch {

	WebDriver driver;
	WebDriverWait wait;

	@Given("^Open browser with Alchemy Jobs site​ and navigate to Jobs page$")
	public void openBrowser() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 30);

		driver.get("https://alchemy.hguy.co/jobs");
		driver.manage().window().maximize();

		driver.findElement(By.linkText("Jobs")).click();
	}

	@And("^Find the Keywords search input field$")
	public void findKeywordSearchTextBox() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("search_keywords")));
	}

	@Then("^Type in keywords to search for jobs and change the Job type$")
	public void searchForJobs() {
		driver.findElement(By.id("search_keywords")).clear();
		driver.findElement(By.id("search_keywords")).sendKeys("Tester");

		driver.findElement(By.xpath("//input[@value='Search Jobs']")).click();
	}

	@And("^Find the filter using XPath and filter job type to show only Full Time jobs$")
	public void filterByFullTimeJobs() {
		driver.findElement(By.id("job_type_freelance")).click();
		driver.findElement(By.id("job_type_internship")).click();
		driver.findElement(By.id("job_type_part-time")).click();
		driver.findElement(By.id("job_type_temporary")).click();
	}

	@Then("^Find a job listing using XPath and it to see job details$")
	public void openJobDetails() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[contains(@href,'ibm-hyderabad-2-cucumber-bdd-tester-4')]")));
		driver.findElement(By.xpath("//a[contains(@href,'ibm-hyderabad-2-cucumber-bdd-tester-4')]")).click();
	}

	@And("^Find the title of the job listing using XPath and print it to the console$")
	public void printJobTitle() {
		String getJobTitle = driver.findElement(By.className("entry-title")).getText();

		Assert.assertEquals(getJobTitle, "Cucumber/BDD Tester");
	}

	@Then("^Find and Click on the “Apply for job” button$")
	public void applyForJob() {
		driver.findElement(By.xpath("//input[@value='Apply for job']")).click();
	}
	
	@And("^Close the Browser$")
	public void closeTheBrowser() throws Throwable {
		driver.quit();
	}
}
