package ru.selenium.training;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class TestBase {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 2);
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    boolean areElementsPresent(By locator, int time) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        boolean a = driver.findElements(locator).size() > 0;
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
        return a;
    }




    public boolean isElementPresent (By locator){
        try {
            wait.until( d-> d.findElement(locator) );
            //driver.findElement(locator);
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    boolean areElementsPresent(By locator) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        return driver.findElements(locator).size() > 0;
    }

    @After
    public void Stop(){
        driver.quit();
        //WebDriverUtils.killDrivers();
        driver = null;
    }

}
