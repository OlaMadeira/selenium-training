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

public class LeftPanel{

    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void start() {
        //System.setProperty("webdriver.chrome.driver", "C:\\workspace\\chromedriver.exe");
        driver = new ChromeDriver();
        //wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    //isElementPresent method
    public boolean isElementPresent(By locator) {
        try {
            wait.until((WebDriver d) -> d.findElement(locator));
            //driver.findElement(locator);
            return true;
        } catch (TimeoutException ex) {
            return false;
        } catch (NullPointerException ex) {
            return false;
        }
    }

    //login method
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
    //clicking through items of left navigation panel
    public void leftPanelNavigation() {

        login();
        Assert.assertTrue(isElementPresent(By.xpath("//*[@id='box-apps-menu']")));
        WebElement panel = driver.findElement(By.xpath("//*[@id='box-apps-menu']"));//заменить звездочку на тэг
        int numberOfPanels = panel.findElements(By.xpath("./li")).size();// кол-во тэгов ли на стр

        System.out.println("number of parent panels: " + numberOfPanels);

        for (int i = 1; i < numberOfPanels; i++) {
            driver.findElement(By.xpath("//*[@id='box-apps-menu']/li[" + i + "]")).click();
            System.out.println("opened panel:" + "" + driver.findElement(By.xpath("//*[@id='box-apps-menu']/li[" + i + "]")).getText());
            int numberOfSubPanels = driver.findElements(By.xpath("//li[contains(@class,'doc')]")).size();


            if (numberOfSubPanels > 0) {
                for (int j=1; j< numberOfSubPanels; j++){
                    driver.findElement(By.xpath("//li[contains(@class,'doc')][" + j + "]")).click();
                    System.out.println("opened panel:" + "" + driver.findElement(By.xpath("//li[contains(@class,'doc')][" + j + "]")).getText());

                }
            }
        }

    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
