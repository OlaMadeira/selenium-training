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
        WebElement panel = driver.findElement(By.xpath("//div[@id='sidebar']//ul[@id='box-apps-menu']"));//заменить звездочку на тэг
        int numberOfPanels = panel.findElements(By.xpath("./li")).size();// кол-во тэгов ли на стр


        for (int i = 1; i <= numberOfPanels; i++) {

            driver.findElement(By.xpath("//div[@id='sidebar']//ul[@id='box-apps-menu']/li[" + i + "]")).click();
            Assert.assertTrue(isElementPresent(By.xpath("//div[@class='panel-heading']")));
            textSelector();

            int numberOfSubPanels = driver.findElements(By.xpath("//li[contains(@class,'doc')]")).size();

            if (numberOfSubPanels > 0) {
                for (int j = 1; j < numberOfSubPanels +1; j++) {
                    driver.findElement(By.xpath("//li[contains(@class,'doc')][" + j + "]")).click();
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
    public void textSelector(){

        WebElement textPanel = driver.findElement(By.xpath("//div[@class='panel-heading']"));
        String text = textPanel.getText();
        System.out.println(text);

    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
