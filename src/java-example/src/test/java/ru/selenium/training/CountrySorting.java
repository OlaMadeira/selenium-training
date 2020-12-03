package ru.selenium.training;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Iterator;
import java.util.List;

import static org.openqa.selenium.By.xpath;

public class CountrySorting extends TestBase {

    @Test
    public void sortCountries() {
        //variables used
        final By leftBox = xpath("//div[@id='sidebar']//ul[@id='box-apps-menu']");
        final By countriesButton = xpath("//div[@id='sidebar']//ul[@id='box-apps-menu']//a[contains(@href, 'countries')]");
        final By nameColumn = xpath("//div[@class='panel-body']//tbody//tr//td[5]/a");
        final By tableRows = xpath("//main[@id='main']//div[@id='content']//table[@class='table table-striped table-hover data-table']/tbody/tr");

        //logic itself
        login("admin", "admin");
        Assert.assertEquals(true, isElementPresent(leftBox));
        //driver.findElement(countriesButton).click();
        //System.out.println("Countries page is opened");

        //got countries list
        driver.get("http://localhost:8080/litecart/admin/?app=countries&doc=countries");
        Assert.assertTrue(isElementPresent(nameColumn));
        List<WebElement> countries = driver.findElements(nameColumn);
        System.out.println(countries.size());

        Iterator<WebElement> iterator = countries.iterator();

        while (iterator.hasNext()) {

            WebElement element = iterator.next();

            String element1 = element.getAttribute("innerHTML");

            if (!iterator.hasNext()) {

                System.out.println("No pair for element " + element1);
                break;
            }

            String element2 = iterator.next().getAttribute("innerHTML");

            if (element1.compareTo(element2) < 0) {
                System.out.println("Compare element 1 " + element1 + " to element 2 " + element2 + " Result is - correct sorting ");
            }
            else {
                System.out.println("Sorting is wrong");
            }
        }

    }
}
