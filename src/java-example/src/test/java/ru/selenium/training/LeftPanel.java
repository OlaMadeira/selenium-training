package ru.selenium.training;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LeftPanel extends TestBase {

    @Test
    //clicking through items of left navigation panel
    public void leftPanelNavigation() {

        login();
        Assert.assertTrue(isElementPresent(By.xpath("//div[@id='sidebar']//ul[@id='box-apps-menu']")));
        Assert.assertTrue(isElementPresent(By.xpath("//div[@id='sidebar']//ul[@id='box-apps-menu']//span[contains(., 'Appearance')]")));
        WebElement panel = driver.findElement(By.xpath("//div[@id='sidebar']//ul[@id='box-apps-menu']"));
        int numberOfPanels = panel.findElements(By.xpath("./li")).size();// кол-во панелей на стр
        System.out.println("number of parent panels: " + numberOfPanels);


        for (int i = 0; i < numberOfPanels; i++) {

            WebElement currentPanel = driver.findElements(By.xpath("//div[@id='sidebar']//ul[@id='box-apps-menu']/li")).get(i);
            System.out.println("opened panel is: " + currentPanel.getText());
            currentPanel.click();
            //driver.findElements(By.xpath("//div[@id='sidebar']//ul[@id='box-apps-menu']/li")).get(i).click();
            Assert.assertTrue(isElementPresent(By.xpath("//div[@class='panel-heading']")));
            textSelector();

            int numberOfSubPanels = driver.findElements(By.xpath("//li[contains(@class,'doc')]")).size();
            System.out.println("number of subpanels: " + numberOfSubPanels);

            if (numberOfSubPanels > 0) {
                for (int j = 0; j < numberOfSubPanels; j++) {

                    WebElement currentSubpanel = driver.findElements(By.xpath("//li[contains(@class,'doc')]")).get(j);
                    System.out.println("opened subpanel is " + currentSubpanel.getText());
                    currentSubpanel.click();
                    Assert.assertTrue(isElementPresent(By.xpath("//div[@class='panel-heading']")));
                    textSelector();
                }
            }
        }
    }

    //method extracting the text of the opened panel header
    public void textSelector() {

        WebElement textPanel = driver.findElement(By.xpath("//div[@class='panel-heading']"));
        String text = textPanel.getText();
        System.out.println("current header is: " + text);

    }

}
