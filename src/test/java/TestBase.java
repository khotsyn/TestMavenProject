import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {
	
	protected static WebDriver driverBase1 = new FirefoxDriver();		

	@BeforeSuite
	public static void setUp() throws Exception {		
		driverBase1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//driverBase1.manage().window().maximize();
	}

	@AfterSuite
	public static void tearDown() throws Exception {		
		driverBase1.close();				
	}	
}
