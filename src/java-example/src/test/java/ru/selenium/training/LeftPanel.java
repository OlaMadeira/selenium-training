package ru.selenium.training;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.stream.IntStream;

public class LeftPanel {

    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void start() {
        //System.setProperty("webdriver.chrome.driver", "C:\\workspace\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @Test
    //clicking through items of left navigation panel
    public void leftPanelNavigation() {

        login();
        Assert.assertTrue(isElementPresent(By.xpath("//div[@id='sidebar']//ul[@id='box-apps-menu']")));
        WebElement panel = driver.findElement(By.xpath("//div[@id='sidebar']//ul[@id='box-apps-menu']"));
        int numberOfPanels = panel.findElements(By.xpath("./li")).size();// кол-во панелей на стр
        System.out.println("number of parent panels: " + numberOfPanels);


        for (int i = 0; i < numberOfPanels; i++) {

            WebElement currentPanel = driver.findElements(By.xpath("//div[@id='sidebar']//ul[@id='box-apps-menu']/li")).get(i);
            System.out.println("current panel is "+ currentPanel.getText());
            currentPanel.click();
            //driver.findElements(By.xpath("//div[@id='sidebar']//ul[@id='box-apps-menu']/li")).get(i).click();
            Assert.assertTrue(isElementPresent(By.xpath("//div[@class='panel-heading']")));
            textSelector();

            int numberOfSubPanels = driver.findElements(By.xpath("//li[contains(@class,'doc')]")).size();
            System.out.println("number of subpanels: " + numberOfSubPanels);

            if (numberOfSubPanels > 0) {
                for (int j =0; j < numberOfSubPanels; j++) {

                    WebElement currentSubpanel = driver.findElements(By.xpath("//li[contains(@class,'doc')]")).get(j);
                    System.out.println("current sub panel is " + currentSubpanel.getText());
                    currentSubpanel.click();
                    Assert.assertTrue(isElementPresent(By.xpath("//div[@class='panel-heading']")));
                    textSelector();
                }

            }
        }

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

    }

    //text selector method
    public void textSelector() {

        WebElement textPanel = driver.findElement(By.xpath("//div[@class='panel-heading']"));
        String text = textPanel.getText();
        System.out.println("current header is: "+ text);

    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
