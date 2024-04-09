package WebDriverScript;


/** Case Study: Book Search Automation 

Problem Statement: 

Automate Search functionality and validate Total books on online library shopping Website. 

Listing Category and Count of Books.
Browsing the books with respective to Subject.
Suggested site: openlibrary.org, however you are free to choose any other legitimate shopping site 

Detailed Description: 

Launch any browser as per the user input ex: Chrome/Firefox.
Open the online shopping website ex: https://www.openlibrary.org/.
User will navigate to home page of the application for finding details of the books.
Display the details of books like Category and Count in the console window.
Click “Browse” link in home page and select “Subjects”, application navigates to next page where list of books are displayed with respect to subject.
Go further navigation by clicking “See more options”.
Check whether “Tamil” books are available more than one thousand.
Close the browser. */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;
import java.util.List;
import java.util.Scanner;

public class Miniproject {
	
	public static WebDriver driver;
	public static String browserChoice;
	
	public WebDriver getDriver(String browserChoice) { //Launch any browser as per the user input ex: Chrome/Firefox.

//	browserChoice=browser;
	{
		if(browserChoice.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		
		else if(browserChoice.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
		else if(browserChoice.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else {
			System.out.println("Incorrect Input!");
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
}

public void launch() throws InterruptedException {
	
//	driver = new ChromeDriver();
//	driver = new EdgeDriver();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get("https://www.openlibrary.org/"); //Open the online shopping website ex: https://www.openlibrary.org/.
	driver.manage().window().maximize(); //User will navigate to home page of the application for finding details of the books.
	Thread.sleep(3000);
}

public void display() throws InterruptedException {
	
	WebElement element = driver.findElement(By.xpath("(//*[@class='slick-next slick-arrow'])[11]"));
	JavascriptExecutor jscript = (JavascriptExecutor)driver; 
	jscript.executeScript("arguments[0].scrollIntoView();", element);
	
	List<WebElement> element1 = driver.findElements(By.xpath("//a[@class='category-nostyle']"));
	int count = 0;
	
	for(WebElement wele : element1) { // Display the details of books like Category and Count in the console window.
		System.out.println(wele.getText());

		count++;
		if(count>=6) {
			jscript.executeScript("arguments[0].click();", element);
			continue;
		}
	}
}

public void browse() throws InterruptedException { //Click “Browse” link in home page and select “Subjects”, application navigates to next page where list of books are displayed with respect to subject.
	JavascriptExecutor jscript=(JavascriptExecutor)driver;
	WebElement element2 = driver.findElement(By.xpath("(//details//summary[contains(text(),'Browse')])[1]"));
	jscript.executeScript("arguments[0].click();", element2);
	Thread.sleep(2000);
	driver.findElement(By.linkText("Subjects")).click();
}

public void moreOptions() { //Go further navigation by clicking “See more options”.
	JavascriptExecutor jscript = (JavascriptExecutor)driver;
	WebElement element3=driver.findElement(By.xpath("//a[text()='See more...']"));
	jscript.executeScript("arguments[0].scrollIntoView();",element3);
	jscript.executeScript("arguments[0].click();",element3);
}

public void checkTamilBooks() { // Check whether “Tamil” books are available more than one thousand.
	JavascriptExecutor jscript = (JavascriptExecutor)driver;
	WebElement check=driver.findElement(By.linkText("Tamil"));
	jscript.executeScript("arguments[0].scrollIntoView();",check);
}

public void tamilBooks() {
	String books = driver.findElement(By.xpath("//*[@id=\"contentBody\"]/ul/li[38]/span/b")).getText();
	System.out.println("Count of Tamil Books : "+books);
	String[] b = books.split(" ");
	String[] c = b[0].split(",");
	String str = c[0]+c[1];
	int count = Integer.parseInt(str);
		if(count>1000) // Check whether “Tamil” books are available more than one thousand.
			System.out.println("Tamil books are available more than one thousand");
			else
			System.out.println("Tamil books are not available more than one thousand");
}

public void closeBrowser() { // Close the browser.
	driver.quit();
}

public static void main (String[] args) throws InterruptedException {
	Miniproject object = new Miniproject();
	
	Scanner sc = new Scanner(System.in);
	
	System.out.println("Enter the Browser Name");
		browserChoice = sc.nextLine();
		
		object.getDriver(browserChoice);
		object.launch();
		object.display();
		object.browse();
		object.moreOptions();
		object.checkTamilBooks();
		object.tamilBooks();
		object.closeBrowser();
	}
}