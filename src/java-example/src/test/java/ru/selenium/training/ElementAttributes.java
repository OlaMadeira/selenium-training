package ru.selenium.training;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.By.xpath;

public class ElementAttributes extends TestBase {

    @Test
    public void checkWarningWrongPW(){
    login("alina2", "wrongpw");
        waitElementsReload(5000);
        final By wrongCredentialsMessagePath = xpath("//div[@id='notices']//div[@class='alert alert-danger'][contains(.,'Wrong combination of username and password')]");

        Boolean messageDisplay = driver.findElement(wrongCredentialsMessagePath).isDisplayed();
        System.out.println("message about wrong pw has been shown: " +  messageDisplay);



    }
}
