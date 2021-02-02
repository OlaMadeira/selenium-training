package ru.selenium.training;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class EnterText extends TestBase {

    @Test
    public void maskiDruzya() {
        driver.get("https://snipp.ru/jquery/masked-input");
        waitElementsReload(4000);

        //enter phone
        WebElement iframe6111 = driver.findElement(By.xpath("//*[@id='sample-6111']"));
        WebElement iframe6114 = driver.findElement(By.xpath("//*[@id='sample-6114']"));
        driver.switchTo().frame(iframe6111);
        WebElement phone = driver.findElement(By.xpath("//input[@class = 'mask-phone form-control']"));
        phone.sendKeys("9515555555" + Keys.TAB);
        String s = phone.getAttribute("value");
        Assert.assertEquals("+7 (951) 555-55-55", s);

        //enter date
        driver.switchTo().defaultContent();
        driver.switchTo().frame(iframe6114);
        WebElement date = driver.findElement(By.xpath("//input[@class = 'mask-date form-control']"));
        date.sendKeys("12.11.1990" + Keys.TAB);
        String s2 = date.getAttribute("value");
        Assert.assertEquals("12.11.1990", s2);

        //go back to phone and change it
        driver.switchTo().defaultContent();
        driver.switchTo().frame(iframe6111);
        phone.click();
        phone.click();
        phone.sendKeys(Keys.ARROW_LEFT);
        phone.sendKeys(Keys.ARROW_LEFT);
        phone.sendKeys(Keys.ARROW_LEFT);
        phone.sendKeys(Keys.ARROW_LEFT);
        phone.sendKeys(Keys.ARROW_LEFT);
        phone.sendKeys("66");
        String s3 = phone.getAttribute("value");
        Assert.assertEquals("+7 (951) 555-66-55", s3);
        System.out.println("The updated phone is:" + s3);
    }

    @Test
    public void bigText() {
        driver.get("https://www.lettercount.com/");
        WebElement field = driver.findElement(By.name("charcount"));
        JavascriptExecutor myExecutor = ((JavascriptExecutor) driver);
        myExecutor.executeScript("arguments[0].value='new super puper long very text!!!!!!!!!!';",field);
        String s = field.getAttribute("value");
        Assert.assertEquals("new super puper long very text!!!!!!!!!!",s);
        System.out.println("this was entered: " +s);
    }
}

