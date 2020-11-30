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

        login();
        Assert.assertTrue(isElementPresent(By.xpath(leftBox)));
        WebElement countriesPanel = driver.findElement(By.xpath(leftBox + "//a[contains(@href, 'countries')]"));
        countriesPanel.click();
        System.out.println("Countries page is opened");

        Assert.assertTrue(isElementPresent(By.xpath(table+"//tr")));
        System.out.println("Table has been loaded");

        WebElement countriesTable = driver.findElement(By.xpath(table));
        Assert.assertTrue(areElementsPresent(By.xpath(table + "//tbody//tr")));
        List<WebElement> tableRows = countriesTable.findElements(By.xpath(".//tbody//tr"));
        int numberOfRows = tableRows.size();
        System.out.println(numberOfRows);

        //List<WebElement> zonesCells = driver.findElements(By.xpath(table + "//tr//td[6]"));
        //System.out.println(zonesCells.size());

    }


}
