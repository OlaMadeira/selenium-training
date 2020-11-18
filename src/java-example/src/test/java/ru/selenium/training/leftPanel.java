package ru.selenium.training;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
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
public class leftPanel extends TestBase{

    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void start(){
        //System.setProperty("webdriver.chrome.driver", "C:\\workspace\\chromedriver.exe");
        driver = new ChromeDriver();
        //wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @Test
    public void leftPanelNavigation(){
        driver.get("http://localhost:8080/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).submit();
        //Assert.assertTrue(isElementPresent(By.xpath("//span[contains(., 'Appearance')]"))); //падает НПЕ
        String actualTitle = driver.getTitle();
        String expectedTitle = "My Store";
        Assert.assertEquals(actualTitle, expectedTitle);

        WebElement panel = driver.findElement(By.xpath("//*[@id='box-apps-menu']"));
        List<WebElement> panelsList = panel.findElements(By.xpath("./li"));
        List<WebElement> subPanelList = driver.findElements(By.xpath("//*[@id='box-apps-menu']//ul/li"));
        System.out.println(panelsList.size());

        for (int i=1; i < panelsList.size();i++){
            driver.findElement(By.xpath("//*[@id='box-apps-menu']/li["+i+"]")).click();

        }



    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }

}
