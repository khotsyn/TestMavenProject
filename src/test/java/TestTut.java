import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import javax.mail.MessagingException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class TestTut {

	protected static WebDriver driver;

	@BeforeClass
	public void BeforeClassTestTut() throws IOException {
		driver = TestBase.driverBase1;
	}

	@Test(dataProvider = "dateForLog", dataProviderClass = TestDataProvider.class)	
	public void checkSendingMail(String acc1, String pass1, String acc2, String pass2)
			throws UnsupportedEncodingException, MessagingException, InterruptedException {		
		
		Calendar calendar = Calendar.getInstance();
		String subject = calendar.getTime().toString();
		Function.sendEmail(driver, pass1, acc1, acc2, subject);
		
		driver.get("http://www.tut.by/");	
		/*
		Actions actions = new Actions(driver);
		WebElement element = driver.findElement(By.linkText("Войти"));
		actions.moveToElement(element, 1, 1).click().build().perform();		
		*/
		
		driver.findElement(By.linkText("Войти")).click();
		
		driver.findElement(By.name("login")).sendKeys(acc1);
		driver.findElement(By.name("password")).sendKeys(pass1);
		driver.findElement(By.id("memory")).click();
		driver.findElement(By.cssSelector("input[value='Войти']")).click();
		driver.findElement(By.cssSelector("a.enter.logedin")).click();
		driver.findElement(By.linkText("Почта")).click();
		driver.findElement(By.xpath("//a[@title='Отправленные'][1]")).click();
		try {
			driver.findElement(By.xpath("//span[@title='" + subject + "']"));
		} catch (NoSuchElementException e) {
			System.out.println("Email " + subject + " is not in Sent of " + acc1);
		}
		driver.get("http://www.tut.by/");
		driver.findElement(By.cssSelector("a.enter.logedin")).click();
		driver.findElement(By.linkText("Выйти")).click();

		driver.findElement(By.linkText("Войти")).click();
		driver.findElement(By.name("login")).sendKeys(acc2);
		driver.findElement(By.name("password")).sendKeys(pass2);
		driver.findElement(By.id("memory")).click();
		driver.findElement(By.cssSelector("input[value='Войти']")).click();
		driver.findElement(By.cssSelector("a.enter.logedin")).click();
		driver.findElement(By.linkText("Почта")).click();
		assertEquals(driver.findElement(By.xpath("//span[@title='" + subject + "']")).getText(), subject);

	}

	@AfterMethod
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			System.out.println(testResult.getStatus());
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("h:\\WorkspaceJ\\TestTask\\Screenshots\\" + scrFile.getName()));
		}
		
	}

}
