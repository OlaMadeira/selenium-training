package ru.selenium.training;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CountrySorting extends TestBase {

    @Test
    public void sortCountries() {
        final String leftBox = "//div[@id='sidebar']//ul[@id='box-apps-menu']";
        final String table = "//main[@id='main']//div[@id='content']//table[@class='table table-striped table-hover data-table']";
        final String tableRows = "//main[@id='main']//div[@id='content']//table[@class='table table-striped table-hover data-table']/tbody/tr";


        login();
        Assert.assertTrue(isElementPresent(By.xpath(leftBox)));
        driver.findElement(By.xpath(leftBox + "//a[contains(@href, 'countries')]")).click();
        System.out.println("Countries page is opened");

        //Assert.assertTrue(isElementPresent(By.xpath(table + "/tfoot[contains(.,'Countries: 2')]")));

        int numberOfRows = driver.findElements(By.xpath(tableRows)).size();
        System.out.println("number of rows = " + numberOfRows);


        //List<WebElement> zonesCells = driver.findElements(By.xpath(table + "//tr//td[6]"));
        //System.out.println(zonesCells.size());

    }


}
