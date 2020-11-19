package ru.selenium.training;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by k.grigorchuk on 27.02.2017.
 */
public class LeftPanel {

    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void start(){
        //System.setProperty("webdriver.chrome.driver", "C:\\workspace\\chromedriver.exe");
        driver = new ChromeDriver();
        //wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    //isElementPresent method here
    public boolean isElementPresent (By locator){
        try {
            wait.until((WebDriver d) -> d.findElement(locator) );
            //driver.findElement(locator);
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
        catch (NullPointerException ex) {
            return false;
        }
    }

    //login method here
    public void login() {
        driver.get("http://localhost:8080/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).submit();
        //Assert.assertTrue(isElementPresent(By.xpath("//*[@id='box-apps-menu']"))); //падает AssertionError
        String actualTitle = driver.getTitle();
        String expectedTitle = "My Store";
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test
    public void leftPanelNavigation() {

        login();

        WebElement panel = driver.findElement(By.xpath("//*[@id='box-apps-menu']"));//заменить звездочку на тэг
        List<WebElement> panelsList = panel.findElements(By.xpath("./li"));

        System.out.println("number of parent panels:" + "" +panelsList.size());

        for (int i = 1; i < panelsList.size(); i++) {
            driver.findElement(By.xpath("//*[@id='box-apps-menu']/li[" + i + "]")).click();


            List<WebElement> subPanelList = driver.findElements(By.xpath("//*[@id='box-apps-menu']//ul/li"));

            for (int j = 1; j < subPanelList.size(); j++) {
                driver.findElement(By.xpath("//*[@id='box-apps-menu']//ul/li[" + j + "]")).click();

                System.out.println("opened panel:" + "" + driver.getCurrentUrl());
            }

        }
    }



    @After
    public void stop(){
        driver.quit();
        driver = null;
    }

}
