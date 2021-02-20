package ru.selenium.training;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class DnD extends TestBase {

    @Test
    //DnD method which does not work in Chrome and half-works in FF
    public void dragNdrop() {
        driver.get("https://jqueryui.com/resources/demos/sortable/connect-lists.html");

        //DnD inside list 1
        waitElementsReload(2000);
        WebElement l1item2 = driver.findElement(By.xpath("//body//ul[@id='sortable1']/li[contains(.,'Item 2')]"));
        WebElement l1item4 = driver.findElement(By.xpath("//body//ul[@id='sortable1']/li[contains(.,'Item 4')]"));
        Actions actions = new Actions(driver);
        actions.dragAndDrop(l1item2, l1item4).perform();
        waitElementsReload(2000);

        //DnD to list 2
        WebElement l1item3 = driver.findElement(By.xpath("//body//ul[@id='sortable1']/li[contains(.,'Item 3')]"));
        WebElement l2item3 = driver.findElement(By.xpath("//body//ul[@id='sortable2']/li[contains(.,'Item 2')]"));//WHY 2
        actions.dragAndDrop(l1item3, l2item3).perform();

        System.out.println("done dnd");
        waitElementsReload(2000);

        //verify that DnD worked correctly - check the left list
        List<WebElement> items = driver.findElements(By.xpath("//body//ul[@id='sortable1']/li"));

        String a = items.get(0).getText();
        Assert.assertEquals("Item 1", a);

        String b = items.get(1).getText();
        Assert.assertEquals("Item 4", b);

        String c = items.get(2).getText();
        Assert.assertEquals("Item 2", c);

        String d = items.get(3).getText();
        Assert.assertEquals("Item 5", d);

        System.out.println("left list be sorted like: "+ a + "; " + b + "; " + c + "; " + d + ";");


        //verify that DnD worked correctly - check the right list
        List<WebElement> items2 = driver.findElements(By.xpath("//body//ul[@id='sortable2']/li"));

        String a1 = items2.get(0).getText();
        Assert.assertEquals("Item 1", a1);

        String b1 = items2.get(1).getText();
        Assert.assertEquals("Item 2", b1);

        String c1 = items2.get(2).getText();
        Assert.assertEquals("Item 3", c1);

        //Assert cell 3 is of correct color
        String c1Color = items2.get(2).getCssValue("background-color");
        Assert.assertEquals("rgb(246, 246, 246)", c1Color);

        String d1 = items2.get(3).getText();
        Assert.assertEquals("Item 3", d1);

        String e1 = items2.get(4).getText();
        Assert.assertEquals("Item 4", e1);

        String f1 = items2.get(5).getText();
        Assert.assertEquals("Item 5", f1);

        System.out.println("right list be sorted like: "+ a1 + "; " + b1 + "; " + c1 +  " (grey color;)" + " "
                + d1 + "; " + e1 + "; " + f1);

    }

    //another variant
    @Test
    public void DnDUgly() {
        driver.get("https://jqueryui.com/resources/demos/sortable/connect-lists.html");

        //DnD inside list 1
        waitElementsReload(2000);
        WebElement l1item2 = driver.findElement(By.xpath("//body//ul[@id='sortable1']/li[contains(.,'Item 2')]"));
        WebElement l1item4 = driver.findElement(By.xpath("//body//ul[@id='sortable1']/li[contains(.,'Item 4')]"));
        Actions builder = new Actions(driver);

        Action dragAndDrop = builder.clickAndHold(l1item2)
                .moveToElement(l1item4)
                .release(l1item4)
                .build();

        dragAndDrop.perform();
        waitElementsReload(5000);

        //DnD to list 2 - why dont you work!!!!!!!

        WebElement l1item3 = driver.findElement(By.xpath("//body//ul[@id='sortable1']/li[contains(.,'Item 3')]"));
        WebElement l2item3 = driver.findElement(By.xpath("//body//ul[@id='sortable2']/li[contains(.,'Item 3')]"));

        Action dragAndDrop2 = builder.clickAndHold(l1item3)
                .moveToElement(l2item3)
                .release(l2item3)
                .build();

        dragAndDrop2.perform();
        waitElementsReload(2000);

    }
}



