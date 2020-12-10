package ru.selenium.training;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Iterator;
import java.util.List;

import static org.openqa.selenium.By.xpath;

public class CountrySorting extends TestBase {

    //variables used
    final By leftBox = xpath("//div[@id='sidebar']//ul[@id='box-apps-menu']");
    final By countriesButton = xpath("//div[@id='sidebar']//ul[@id='box-apps-menu']//a[contains(@href, 'countries')]");
    final By countryNames = xpath("//div[@class='panel-body']//tbody//tr//td[5]/a");
    final By zoneCell = xpath("//div[@id='content']//table[@class='table table-striped table-hover data-table']/tbody//tr/td[6]");

    @Test
    public void sortCountries() {

        login("admin", "admin");
        Assert.assertTrue(isElementPresent(leftBox));
        Assert.assertTrue(isElementPresent(countriesButton));
        driver.findElement(countriesButton).click();
        System.out.println("Countries page is opened");

        Assert.assertTrue(isElementPresent(By.xpath("//div[@class='panel-body']")));
        Assert.assertTrue(isElementPresent(By.xpath("//div[@class='panel-body']//tbody//tr//td[5]/a")));
        List<WebElement> countries = driver.findElements(countryNames);
        System.out.println("Countries available: " + countries.size());

        Iterator<WebElement> iterator = countries.iterator();//iterator

        while (iterator.hasNext()) {

            WebElement element = iterator.next();

            String element1 = element.getText();

            if (!iterator.hasNext()) {

                System.out.println("No pair for element " + element1);
                break;
            }

            String element2 = iterator.next().getText();

            if (element1.compareTo(element2) < 0) {
                System.out.println("Compared element 1 " + element1 + " to element 2 " + element2 + " Result is - correct sorting ");
            } else {
                System.out.println("Sorting is wrong");

            }
        }

    }
    /*
    @Test
    public void sortZones() {
        login("admin", "admin");
        Assert.assertEquals(true, isElementPresent(leftBox));
        driver.get("http://localhost:8080/litecart/admin/?app=countries&doc=countries");

        Assert.assertTrue(areElementsPresent(zoneCell));
        List<WebElement> zoneNumber = driver.findElements(zoneCell);

        List<WebElement> countries = driver.findElements(countryNames);
        Iterator<WebElement> iterator2 = countries.iterator();

        while (iterator2.hasNext()) {

            if (!"0".equals(0)) {

                System.out.println(zoneNumber);
                driver.findElement(countriesButton).click();
                System.out.println(driver.getCurrentUrl());
                driver.navigate().back();
            }
            else System.out.println("Only 0 available");
        }

    } */
}
