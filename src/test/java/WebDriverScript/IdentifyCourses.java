package WebDriverScript;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class IdentifyCourses {

public static WebDriver driver;
public static String browserChoice;

public WebDriver getDriver(String browserChoice) { //Launch any browser as per the user input ex: Chrome/Firefox.
	
	if(browserChoice.equalsIgnoreCase("chrome")) {
		WebDriverManager.chromedriver().setup();
		driver = (WebDriver) new ChromeDriver();
	}
	
	else if(browserChoice.equalsIgnoreCase("edge")) {
		WebDriverManager.edgedriver().setup();
		driver = (WebDriver) new EdgeDriver();
	}
	
	else if(browserChoice.equalsIgnoreCase("firefox")) {
		WebDriverManager.firefoxdriver().setup();
		driver = (WebDriver) new FirefoxDriver();
	}
	else {
		System.out.println("Incorrect Input!");
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
}

public void launch() throws InterruptedException {

	driver.	manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get("https://www.coursera.org/"); //Open the course website : https://www.coursera.org/.
	driver.manage().window().maximize();
	Thread.sleep(3000);
	
}

public void module1() throws InterruptedException, IOException {//Search for web development courses for Beginners level & English Language and extract the course names, total learning hours & rating for first 2 courses
	
	System.out.println("Module 1 : Search for web development courses for Beginners level & English Language and extract the course names, total learning hours & rating for first 2 courses");
	
	JavascriptExecutor jscript=(JavascriptExecutor)driver;
	WebElement sendKey = driver.findElement(By.xpath("//input[@placeholder='What do you want to learn?']"));
	jscript.executeScript("arguments[0].click();", sendKey);
	sendKey.sendKeys("web delevopment");
	sendKey.sendKeys(Keys.ENTER);
	
	Thread.sleep(2000);
	WebElement clickEng = driver.findElement(By.xpath("//*[text()='English']//span"));
	jscript.executeScript("arguments[0].click();", clickEng);
	
	Thread.sleep(2000);
	WebElement clickBeg = driver.findElement(By.xpath("//*[text()='Beginner']//span"));
	jscript.executeScript("arguments[0].click();", clickBeg);


	//print 1st course details
	System.out.println("\n1st Course");
	
	WebElement course1 = driver.findElement(By.xpath("(//h3[@class='cds-CommonCard-title css-1sktkql'])[1]"));
	jscript.executeScript("arguments[0].click();",course1);
	//	course1.click();
	
	String parentWindow = driver.getWindowHandle();
	Set<String> handles =  driver.getWindowHandles();
	   for(String windowHandle  : handles)
	       {
	       if(!windowHandle.equals(parentWindow))
	          {
	          driver.switchTo().window(windowHandle);
	          
	         WebElement title = driver.findElement(By.xpath("//h1[@class=\"cds-119 cds-Typography-base css-1xy8ceb cds-121\"]"));
	         System.out.println("Course Name : "+title.getText());
	         
	         WebElement rating = driver.findElement(By.xpath("(//div[@class='css-lt1dx1']//div[@class=\"css-guxf6x\"])[2]"));
	         System.out.println("Rating : "+rating.getText());
	         
	         WebElement duration = driver.findElement(By.xpath("(//div//div[@class='cds-119 cds-Typography-base css-h1jogs cds-121'])[7]"));
	         System.out.println("Total Learning Hours : "+duration.getText());
	         
	         driver.close(); //closing child window
	         driver.switchTo().window(parentWindow); //cntrl to parent window
	          }
	}
	   
	 //print 2nd course details   
	System.out.println("2nd Course");
   
	WebElement course2 = driver.findElement(By.xpath("(//h3[@class='cds-CommonCard-title css-1sktkql'])[2]"));
	jscript.executeScript("arguments[0].click();",course2);
//	course2.click();
	
	String parentWindow2 = driver.getWindowHandle();
	Set<String> handles2 =  driver.getWindowHandles();
	   for(String windowHandle  : handles2)
	       {
	       if(!windowHandle.equals(parentWindow2))
	          {
	          driver.switchTo().window(windowHandle);
	          
	         WebElement title = driver.findElement(By.xpath("//h1[@class=\"cds-119 cds-Typography-base css-1xy8ceb cds-121\"]"));
	         System.out.println("Course Name : "+title.getText());
	         
	         WebElement rating = driver.findElement(By.xpath("(//div[@class='css-lt1dx1']//div[@class='css-guxf6x'])[2]"));
	         System.out.println("Rating : "+rating.getText());
	         
	         WebElement duration = driver.findElement(By.xpath("(//div//div[@class='cds-119 cds-Typography-base css-h1jogs cds-121'])[7]"));
	         System.out.println("Total Learning Hours : "+duration.getText());
	         
	         driver.close(); //closing child window
	         driver.switchTo().window(parentWindow); //cntrl to parent window
	          }
	}
	
		
}

public void module2() throws InterruptedException {// Look for Language Learning; Extract all the languages and different levels with its total count & display them
	
	System.out.println("\nModule 2 : Look for Language Learning; Extract all the languages and different levels with its total count & display them");
	
	JavascriptExecutor jscript=(JavascriptExecutor)driver;
	driver.findElement(By.xpath("//*[@class=\"rc-CourseraLogo\"]")).click();
	WebElement sendKey = driver.findElement(By.xpath("//input[@placeholder='What do you want to learn?']"));
	jscript.executeScript("arguments[0].click();", sendKey);
	sendKey.sendKeys("Language Learning");
	sendKey.sendKeys(Keys.ENTER);
	
	
	WebElement seeMore = driver.findElement(By.xpath("//*[@aria-label=\"Show more Language options\"]"));
	jscript.executeScript("arguments[0].click();", seeMore);
	
	//print list of languages
	int cntLan=0;
	List<WebElement> lang=driver.findElements(By.xpath("//div[@class='css-1vfysn6']"));
	System.out.println("\nList of Languages");
	for(WebElement lan:lang) {
		Thread.sleep(1000);
		System.out.println(lan.getText());
		cntLan++;
	}
	System.out.println("Total Count of Languages : "+cntLan);
	
	// close button
	Thread.sleep(3000);
	WebElement close=driver.findElement(By.xpath("//div[@class='css-jyd7rb']/button"));
	jscript.executeScript(("arguments[0].click()"),close);
	
	//print list of levels
	int cntLvl=0;
	List<WebElement> level=driver.findElements(By.xpath("//label[text()='Level']/following-sibling::div/div/label/div/span/span"));
	System.out.println("\nList of Levels");
	for(WebElement l:level) {
		System.out.println(l.getText());
		cntLvl++;
	}
	System.out.println("Total Count of Levels : "+cntLvl);
	
	
}


public void module3() throws InterruptedException, IOException {// In Home page, go to "For Enterprise"; Look into Courses for Campus under Product; Fill the  "Ready to transform" form with any one input invalid (example: email); Capture the error message & display

	System.out.println("\nModule 3 : In Home page, go to \"For Enterprise\"; Look into Courses for Campus under Product; Fill the  \"Ready to transform\" form with any one input invalid (example: email); Capture the error message & display");
	JavascriptExecutor jscript=(JavascriptExecutor)driver;
	
	WebElement course=driver.findElement(By.xpath("//a[text()='All AI Courses']"));
	jscript.executeScript(("arguments[0].scrollIntoView();"),course);
	WebElement ener =driver.findElement(By.xpath("//a[text()='For Enterprise']"));
	ener.click();
	WebElement course11=driver.findElement(By.xpath("//span[text()='Watch now']"));

	jscript.executeScript(("arguments[0].scrollIntoView();"),course11);
	WebElement what=driver.findElement(By.xpath("//a[text()='What We Offer']"));
	what.click();
//Thread.sleep(5000);
	WebElement clc=driver.findElement(By.xpath("//h4[text()='* Credit eligibility determined by your institution.']"));
	jscript.executeScript(("arguments[0].scrollIntoView();"),clc);
	WebElement learn=driver.findElement(By.xpath("(//div[contains(@class,'MuiGrid-root')]//span[text()='Learn more'])[2]"));
	learn.click();
	WebElement lastname=driver.findElement(By.xpath("//div[text()='International Information Technology University (IITU) ']"));
	jscript.executeScript(("arguments[0].scrollIntoView()"), lastname);

	WebElement firstname=driver.findElement(By.xpath("//input[@id='FirstName']"));
	firstname.sendKeys("Samyuktha");
	WebElement touch=driver.findElement(By.xpath("//h2[text()='Get in touch with our sales team']"));
	jscript.executeScript(("arguments[0].scrollIntoView();"),touch);
	WebElement lastname1=driver.findElement(By.id("LastName"));
	lastname1.sendKeys("Venugopal");
	WebElement email=driver.findElement(By.id("Email"));
	email.sendKeys("fun@123.com");
	WebElement phone=driver.findElement(By.id("Phone"));
	phone.sendKeys("9361499590");
	Select s= new Select(driver.findElement(By.id("Institution_Type__c")));
	s.selectByIndex(1);
//	System.out.println(s);
	WebElement ins=driver.findElement(By.id("Company"));
	ins.sendKeys("Cognizant");

	WebElement student=driver.findElement(By.xpath("//h2[@class='cds-119 css-1cqrdzk cds-121']"));
	jscript.executeScript(("arguments[0].scrollIntoView();"), student);

	Select s1=new Select(driver.findElement(By.id("Title")));
	s1.selectByValue("Student");
//	System.out.println(s1.getText());
	Select ss1=new Select(driver.findElement(By.id("Department")));
	ss1.selectByValue("Other");
	Select ss2=new Select(driver.findElement(By.id("What_the_lead_asked_for_on_the_website__c")));
	ss2.selectByValue("Learner Support");
	Select ss3=new Select(driver.findElement(By.id("Country")));
	ss3.selectByValue("India");
	Select ss4=new Select(driver.findElement(By.id("State")));
	ss4.selectByValue("Tamil Nadu");
	WebElement sc=driver.findElement(By.xpath("//div[text()='Lameck O.']"));
	jscript.executeScript(("arguments[0].scrollIntoView();"), sc);
	
	WebElement submit=driver.findElement(By.xpath("//button[text()='Submit']"));
	jscript.executeScript(("arguments[0].click();"),submit);
//	WebElement touch=driver.findElement(By.xpath("//h2[text()='Get in touch with our sales team']"));
	jscript.executeScript(("arguments[0].scrollIntoView();"),touch);
	
	WebElement errorMsg = driver.findElement(By.xpath("//div[@class=\"mktoErrorMsg\"]"));
	System.out.println("\nError Message : "+errorMsg.getText());
	
	File ssErrorMsg = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(ssErrorMsg, new File(".//screenshot/errorMsg.png"));
}


public void closeBrowser() { // Close the browser.
	driver.quit();
}
	
	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		
		IdentifyCourses object = new IdentifyCourses();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the Browser Name");
		browserChoice = sc.nextLine();
		
		object.getDriver(browserChoice);
		object.launch();
		object.module1();
		object.module2();
		object.module3();
		object.closeBrowser();

	}

}