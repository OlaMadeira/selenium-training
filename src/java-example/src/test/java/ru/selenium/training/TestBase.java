package ru.selenium.training;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class TestBase {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start(){
        //System.setProperty("webdriver.edge.driver", "C:\\windows\\workspace\\msedgedriver.exe");
        //driver = new ChromeDriver();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    boolean areElementsPresent(By locator, int time) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        boolean a = driver.findElements(locator).size() > 0;
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
        return a;
    }


    public boolean
    isElementPresent (By locator){
        try {
            wait.until( d-> d.findElement(locator) );
            //driver.findElement(locator);
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    boolean areElementsPresent(By locator) {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        return driver.findElements(locator).size() > 0;
    }

    //login method
    public void login(String username, String password) {

        driver.get("http://localhost:8080/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).submit();

    }

    //wait for the page to load
    public void waitForPageToLoad() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        int timeWaitedInMilliseconds = 0;
        int maxWaitTimeInMilliseconds = 2000;

        while (timeWaitedInMilliseconds < maxWaitTimeInMilliseconds) {
            if (js.executeScript("return document.readyState").equals("interactive")) {
                System.out.println("Waited interactive: " + timeWaitedInMilliseconds);
                break;
            }
            waitElementsReload(100);
            timeWaitedInMilliseconds += 100;
        }

        timeWaitedInMilliseconds = 0;
        while (!js.executeScript("return document.readyState").equals("complete")) {
            //System.out.println("waiting !!!!");
            waitElementsReload(500);
            timeWaitedInMilliseconds += 500;
            if (timeWaitedInMilliseconds == 10000) {
                break;
            }
        }
    }




    /**
     * thread sleep
     *
     * @param ms time in milliseconds
     */
    protected void waitElementsReload(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
            throw new IllegalArgumentException("error"+ e);
        }
    }


    @After
    public void Stop(){
        driver.quit();
        //WebDriverUtils.killDrivers();
        driver = null;
    }

}
