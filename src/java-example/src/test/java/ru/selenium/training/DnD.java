package ru.selenium.training;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static org.openqa.selenium.By.xpath;

public class DnD extends TestBase {

    @Test
    //DnD method which does not work in Chrome and works with a hack in FF
    public void dragNdrop() {
        final By column1 = xpath("//ul[@id='sortable1']/li");
        final By column2 = xpath("//ul[@id='sortable2']/li");

        driver.get("https://jqueryui.com/resources/demos/sortable/connect-lists.html");

        //DnD inside list 1
        waitElementsReload(2000);

        List<WebElement> list1 = driver.findElements(column1);
        WebElement item2 = list1.get(1);
        WebElement item4 = list1.get(3);

        Actions actions = new Actions(driver);
        actions.dragAndDrop(item2, item4).perform();
        waitElementsReload(2000);

        //DnD to list 2 - still does not work when moved to 3rd element - WHY??
        List<WebElement> list2 = driver.findElements(column2);
        WebElement item3 = list1.get(2);
        WebElement item33 = list2.get(1);

        actions.dragAndDrop(item3, item33).perform();
        waitElementsReload(2000);

        //verify that DnD worked correctly - check the left list
        List<WebElement> list1s = driver.findElements(column1);

        String a = list1s.get(0).getText();
        Assert.assertEquals("Item 1", a);

        String b = list1s.get(1).getText();
        Assert.assertEquals("Item 4", b);

        String c = list1s.get(2).getText();
        Assert.assertEquals("Item 2", c);

        String d = list1s.get(3).getText();
        Assert.assertEquals("Item 5", d);

        System.out.println("left list be sorted like: " + a + "; " + b + "; " + c + "; " + d + ";");

        //verify that DnD worked correctly - check the right list
        List<WebElement> list2s = driver.findElements(column2);

        String a1 = list2s.get(0).getText();
        Assert.assertEquals("Item 1", a1);

        String b1 = list2s.get(1).getText();
        Assert.assertEquals("Item 2", b1);

        String c1 = list2s.get(2).getText();
        Assert.assertEquals("Item 3", c1);

        //Assert cell 3 is of correct color (fetched from left list)
        String c1Color = list2s.get(2).getCssValue("background-color");
        Assert.assertEquals("rgb(246, 246, 246)", c1Color);

        String d1 = list2s.get(3).getText();
        Assert.assertEquals("Item 3", d1);

        String e1 = list2s.get(4).getText();
        Assert.assertEquals("Item 4", e1);

        String f1 = list2s.get(5).getText();
        Assert.assertEquals("Item 5", f1);

        System.out.println("right list be sorted like: " + a1 + "; " + b1 + "; " + c1 + " (grey color;)" + " "
                + d1 + "; " + e1 + "; " + f1);
    }

    @Test
    public void getOffset() {

        driver.get("https://jqueryui.com/resources/demos/sortable/connect-lists.html");
        WebElement l2item3 = driver.findElement(xpath("//ul[@id='sortable2']/li[contains(.,'Item 2')]"));
        Point point = l2item3.getLocation();
        int xcord = point.getX();
        int ycord = point.getY();
        System.out.println("x is: " + xcord + "; y is: " + ycord);
    }
}



