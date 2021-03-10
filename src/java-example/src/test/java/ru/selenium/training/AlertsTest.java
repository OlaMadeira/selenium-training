package ru.selenium.training;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;

public class AlertsTest extends TestBase {

    @Test
    public void alerts() {
        final By button1 = xpath("//button[@id='alertButton' and @class='btn btn-primary']");
        final By button2 = xpath("//button[@id='timerAlertButton' and @class='btn btn-primary']");
        final By button3 = xpath("//button[@id='confirmButton' and @class='btn btn-primary']");
        final By button4 = xpath("//button[@id='promtButton' and @class='btn btn-primary']");


        driver.get("https://demoqa.com/alerts");
        waitElementsReload(2000);

        //alert1

        driver.findElement(button1).click();
        wait.until(alertIsPresent());
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("You clicked a button", alert.getText());
        alert.dismiss();
        System.out.println("Correct alert1 was opened and closed");

        //alert2
        driver.findElement(button2).click();
        wait.until(alertIsPresent());
        driver.switchTo().alert();
        Assert.assertEquals("This alert appeared after 5 seconds", alert.getText());
        alert.dismiss();
        System.out.println("Correct alert2 was opened and closed");

        //alert3
        driver.findElement(button3).click();
        wait.until(alertIsPresent());
        driver.switchTo().alert();
        Assert.assertEquals("Do you confirm action?", alert.getText());
        alert.accept();
        System.out.println("Correct alert3 was opened and closed on OK");

        //alert4
        driver.findElement(button4).click();
        wait.until(alertIsPresent());
        driver.switchTo().alert();
        Assert.assertEquals("Please enter your name", alert.getText());
        alert.sendKeys("Salut Vera, Alert Vera!");
        alert.accept();
        System.out.println("Correct alert4 was opened and closed on OK");
    }
}
