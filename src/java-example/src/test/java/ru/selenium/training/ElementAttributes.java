package ru.selenium.training;

import com.sun.javafx.css.converters.ColorConverter;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;

import javax.swing.*;
import java.util.List;

import static java.lang.Double.parseDouble;
import static org.openqa.selenium.By.xpath;

public class ElementAttributes extends TestBase {
    //xpath selectors used in tests are stored here
    final By campaignProductsSection = xpath("//section[@id='box-campaign-products']//h2[contains(.,'Campaign Products')]");
    final By campaignProductXpath = xpath("//section[@id='box-campaign-products']//div[@class='listing products']//a");
    final By nameOnHomePage = xpath("//section[@id='box-campaign-products']//div[@class='listing products']//h4[@class='name']");
    final By oldPriceOnHomePage = xpath("//section[@id='box-campaign-products']//div[@class='listing products']//del[@class='regular-price']");
    final By newPriceOnHomePage = xpath("//section[@id='box-campaign-products']//div[@class='listing products']//strong[@class='campaign-price']");
    final By nameOnDetailsPage = xpath("//article[@id='box-product']//div//h1[@class='title']");
    final By oldPriceOnDetailsPage = xpath("//article[@id='box-product']//div[@class='price-wrapper']//del[@class='regular-price']");
    final By newPriceOnDetailsPage = xpath("//article[@id='box-product']//div[@class='price-wrapper']//strong[@class='campaign-price']");


    @Test
    public void checkWarningWrongPW() {
        login("alina2", "wrongpw");
        waitElementsReload(5000);
        final By wrongCredentialsMessagePath = xpath("//div[@id='notices']//div[@class='alert alert-danger'][contains(.,'Wrong combination of username and password')]");

        Boolean messageDisplay = driver.findElement(wrongCredentialsMessagePath).isDisplayed();
        System.out.println("message about wrong pw has been shown: " + messageDisplay);
    }

    @Test
    //make sure name, newPrice and OldPrice are the same inside the CampaignProductsPage and ProductDetails page
    public void CompareHomePageAndDetailsPageValues() {

        //starting the test
        driver.get("http://localhost:8080/litecart/");
        Assert.assertTrue(isElementPresent(campaignProductsSection));
        System.out.println("campaign products section is present");

        int numberOfProducts = driver.findElements(campaignProductXpath).size();
        System.out.println("the number of campaign products atm is: " + numberOfProducts);

        for (int i = 0; i < numberOfProducts; i++) {
            //getting product attributes from homePage
            System.out.println("Getting values from home page and details page:");
            String name1 = driver.findElement(nameOnHomePage).getText();
            String oldPrice1 = driver.findElement(oldPriceOnHomePage).getText();
            String newPrice1 = driver.findElement(newPriceOnHomePage).getText();
            System.out.println("--> product data on home page: name: " + name1 + "; old price: " + oldPrice1 + "; new price: " + newPrice1);

            //getting product attributes from details page
            driver.findElement(campaignProductXpath).click();
            String name2 = driver.findElement(nameOnDetailsPage).getText();
            String oldPrice2 = driver.findElement(oldPriceOnDetailsPage).getText();
            String newPrice2 = driver.findElement(newPriceOnDetailsPage).getText();
            System.out.println("--> product data on details page: name: " + name2 + "; old price: " + oldPrice2 + "; new price: " + newPrice2);

            //comparing the attributes on 2 pages
            System.out.println("Comparing values from home page and details page:");
            if (name1.equals(name2)) {
                System.out.println("--> name on home page " + "<" + name1 + ">" + " and name on details page " + "<" + name2 + ">" + " are the same");
            } else System.out.println("--> names do not match");

            if (oldPrice1.equals(oldPrice2)) {
                System.out.println("--> old price on home page " + "<" + oldPrice1 + ">" + " and old price on details page " + "<" + oldPrice2 + ">" + " are the same");
            } else System.out.println("--> old price values do not match");

            if (newPrice1.equals(newPrice2)) {
                System.out.println("--> new price on home page " + "<" + newPrice1 + ">" + " and new price on details page " + "<" + newPrice2 + ">" + " are the same");
            } else System.out.println("--> names do not match");
        }
    }

    @Test
    public void checkPriceValues() {
        //starting the test
        driver.get("http://localhost:8080/litecart/");
        Assert.assertTrue(isElementPresent(campaignProductsSection));

        int numberOfProducts = driver.findElements(campaignProductXpath).size();
        System.out.println("The number of campaign products atm is: " + numberOfProducts);

        System.out.println("Checking price text styles on the home page:");
        for (int i = 0; i < numberOfProducts; i++) {

            //old price
            String color1 = driver.findElement(oldPriceOnHomePage).getCssValue("color");
            String[] numbers = color1.replace("rgba(", "").replace(")", "").split(",");
            int r = Integer.parseInt(numbers[0].trim());
            int g = Integer.parseInt(numbers[1].trim());
            int b = Integer.parseInt(numbers[2].trim());
            System.out.println("--> old price color rgba code is:" + color1 + "; " +
                    "its parsed code is: " + "r=" + r + "; " + "g=" + g + " ;" + "b=" + b);

            if (r <= 51 || r >= 0 || b >= 0 || b <= 120) {
                System.out.println("--> old price color is black");
            } else {
                System.out.println("--> old price color is not black");
            }

            String decoration1 = driver.findElement(oldPriceOnHomePage).getCssValue("text-decoration-line");
            System.out.println("--> old price text style: old price is marked with: " + decoration1);

            String size1 = driver.findElement(oldPriceOnHomePage).getCssValue("font-size");
            String s1 = size1.replace("px", "");
            double s11 = Double.parseDouble(s1);
            System.out.println("--> old price text size is: " + s11);


            //new price
            String color2 = driver.findElement(newPriceOnHomePage).getCssValue("color");
            String[] numbers2 = color2.replace("rgba(", "").replace(")", "").split(",");
            int r2 = Integer.parseInt(numbers2[0].trim());
            int g2 = Integer.parseInt(numbers2[1].trim());
            int b2 = Integer.parseInt(numbers2[2].trim());
            System.out.println("--> new price color rgba code is:" + color2 + "; " +
                    "its parsed code is: " + "r=" + r2 + "; " + "g=" + g2 + " ;" + "b=" + b2);
            if (b2 <= 105 || b2 >= 0 || g2 >= 0 || g2 <= 38) {
                System.out.println("--> new price color is red");
            } else {
                System.out.println("--> new price color is not red");
            }
            String decoration2 = driver.findElement(newPriceOnHomePage).getCssValue("font-weight");
            System.out.println("--> new price text style: new price is written in : " + decoration2);

            String size2 = driver.findElement(newPriceOnHomePage).getCssValue("font-size");
            String s2 = size2.replace("px", "");
            double s22 = Double.parseDouble(s2);
            System.out.println("--> new price text size is: " + s22);

            if (s11 < s22){
                System.out.println("->> old price font is smaller than new price font");
            } else {
                System.out.println("->> old price font is bigger than new price font");
            }

        }
    }
}
