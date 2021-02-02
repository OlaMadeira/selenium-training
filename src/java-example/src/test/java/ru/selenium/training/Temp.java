package ru.selenium.training;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Temp {
    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void start() {
        //if FF, uncomment this
        driver = new FirefoxDriver();

        //if IE, uncomment this
        //DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
        //ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        //driver = new InternetExplorerDriver(ieCapabilities);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
    }

        @Test
        public void clickTest() {
            driver.get("https://output.jsbin.com/zezuyo");
            Assert.assertTrue(isElementPresent(By.xpath("//a[contains(@href,'http://seleniumhq.org/')]")));
            WebElement link = driver.findElement(By.xpath("//a[contains(@href,'http://seleniumhq.org/')]"));
            System.out.println("little sneaky element is found");
            link.click();
            System.out.println(driver.getCurrentUrl());
            Assert.assertEquals("https://www.selenium.dev/", driver.getCurrentUrl());

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

}

