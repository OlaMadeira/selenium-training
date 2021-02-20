package ru.selenium.training;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

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

        System.out.println("done");
        waitElementsReload(2000);

        //verify that DnD worked correctly - check the 1st list

        int list1size = driver.findElements(By.xpath("//body//ul[@id='sortable1']/li")).size();
        Assert.assertEquals(4, list1size);

        String a = driver.findElement(By.xpath("//body//ul[@id='sortable1']/li[1]")).getText();
        Assert.assertEquals("Item 1", a);

        String b = driver.findElement(By.xpath("//body//ul[@id='sortable1']/li[2]")).getText();
        Assert.assertEquals("Item 4", b);

        String c = driver.findElement(By.xpath("//body//ul[@id='sortable1']/li[3]")).getText();
        Assert.assertEquals("Item 2", c);

        String d = driver.findElement(By.xpath("//body//ul[@id='sortable1']/li[4]")).getText();
        Assert.assertEquals("Item 5", d);

        System.out.println("first list be sorted like: "+ a + "; " + b + "; " + c + "; " + d + ";");


        /*
        List<WebElement> items = driver.findElements(By.xpath("//body//ul[@id='sortable1']/li"));

            for (int i = 0; i < items.size(); i++) {
            String a = items.get(0).getText();
            String b = items.get(1).getText();
            String c = items.get(2).getText();
            String d = items.get(3).getText();
            System.out.println("first list be sorted like: " + a + " ;" + b + " ;" + c + " ;" + d + "");
        } */

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



