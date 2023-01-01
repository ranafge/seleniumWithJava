import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JunithandsOn {
    WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headed");
        driver  = new ChromeDriver();
        //timeout
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

    }

    @Test
    public void getTitle() throws InterruptedException {
        driver.get("https://demoqa.com/");
        driver.manage().window().maximize();
        Thread.sleep(5000);
        String title = driver.getTitle();
        System.out.println("This is the title : " + title);
        Assert.assertTrue(title.contains("ToolsQA"));

    }

    @Test
    public void getFalseTitleNagitive() {
        String falseTitle = driver.getTitle();
        Assert.assertTrue(falseTitle.contains("xxxxxxx"));
    }

    @Test
    public void fillUpRegistrationPage() {
        driver.get("https://demoqa.com/text-box");
        driver.manage().window().maximize();
        driver.findElement(By.id("userName")).sendKeys("My name");
        driver.findElement(By.id("userEmail")).sendKeys("My name@gmail.com");
        driver.findElement(By.id("currentAddress")).sendKeys("currentAddress");
        driver.findElement(By.id("permanentAddress")).sendKeys("permanentAddress");
        driver.findElement(By.id("submit")).click();
    }

    @Test
    public void checkDoubleClickBtn() {
        driver.get("https://demoqa.com/buttons");
        WebElement doubleClickElement =  driver.findElement(By.id("doubleClickBtn"));
        Actions action = new Actions(driver);
        action.doubleClick(doubleClickElement).perform();
        String doubleClickButtonMessage = driver.findElement(By.id("doubleClickMessage")).getText();
        Assert.assertTrue(doubleClickButtonMessage.contains("You have done a double click" ));
        System.out.println("Button message is : " + doubleClickButtonMessage);
    }


    @Test
    public void listOfButtonClick() throws InterruptedException {
        driver.get("https://demoqa.com/buttons");
        driver.manage().window().maximize();
        List <WebElement> listOfBtn = driver.findElements(By.tagName("button"));
        Actions action = new Actions(driver);
        action.doubleClick(listOfBtn.get(1)).click().perform();
        action.click(listOfBtn.get(3)).click().perform();
        String btn3message = driver.findElement(By.id("dynamicClickMessage")).getText();
        Thread.sleep(3000);
        Assert.assertTrue(btn3message.contains("You have done a dynamic click"));
        Thread.sleep(5000);
        action.contextClick(listOfBtn.get(2)).perform();
        Thread.sleep(5000);
        WebElement btn2message = driver.findElement(By.id("rightClickMessage"));
        System.out.println("Click message:  " +btn2message.getText());
        Assert.assertTrue(btn2message.getText().contains("You have done a right click"));





    }

    @After
    public void teardown() {
        driver.quit();
    }





}
