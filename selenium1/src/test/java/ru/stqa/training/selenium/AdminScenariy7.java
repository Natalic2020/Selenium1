package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class AdminScenariy7 extends  TestBase{

    private void loginAdmin(){
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        String expected = "My Store";
        String actuil = driver.getTitle();

        Assert.assertEquals(expected, actuil);
    }

    @Test
    public  void MenuLeftTest() {
        loginAdmin();
        List<WebElement> formList = driver.findElements(By.id("box-apps-menu"));

        WebElement formAppsMenu = formList.get(0);
        List<WebElement> appsMenuList = formAppsMenu.findElements(By.id("app-"));
        int sizeMenu = appsMenuList.size();
        System.out.println("appsMenuList  size Menu " + sizeMenu);
        for (int i = 0; i < sizeMenu; i++) {

            driver.findElement(By.xpath("//*[@id='box-apps-menu']/li[" + (i + 1) + "]//*[@class='name']")).click();

            System.out.println("Click " + (i+1));

            System.out.println("h1");
            wait.until((WebDriver driver) -> driver.findElement(By.xpath("//h1")));
            assertTrue(areElementsPresent(By.tagName("h1")));

            WebElement appsMenuList2 = driver.findElement(By.className("selected"));

            List<WebElement> ul = appsMenuList2.findElements(By.tagName("ul"));
            if (ul.size() == 0){
                continue;
            }
            List<WebElement> li2 = ul.get(0).findElements(By.tagName("li"));
            int sizeSubMenu = li2.size();
            for (int j = 0; j < sizeSubMenu; j++) {
                System.out.println("ClickClick " + (i+1) + " / " + (j+1));
                WebElement appsMenuList4 = driver.findElement(By.className("selected"));
                List<WebElement> ul4 = appsMenuList4.findElements(By.tagName("ul"));
                List<WebElement> li4 = ul4.get(0).findElements(By.tagName("li"));
                WebElement itemName = li4.get(j).findElement(By.className("name"));
                itemName.click();
                wait.until((WebDriver driver) -> driver.findElement(By.xpath("//h1")));
                assertTrue(areElementsPresent(By.tagName("h1")));
            }
        }
    }
}
