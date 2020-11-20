package ru.stqa.training.selenium.lesson4;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.training.selenium.TestBaseFireFox;
import ru.stqa.training.selenium.TestBaseFireFoxImplicitly;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class AdminScenariy75 extends TestBaseFireFoxImplicitly {

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

        List<WebElement> appsMenuList = driver.findElements(By.className("icon"));
        int sizeMenu = appsMenuList.size();

        System.out.println("appsMenuList  size Menu " + sizeMenu);
        for (int i = 0; i < sizeMenu; i++) {

            List<WebElement> itemMenus = driver.findElements(By.className("icon"));

            itemMenus.get(i).click();

            System.out.println("Click " + (i+1));

            System.out.println("h1");

            assertTrue(areElementsPresent(By.tagName("h1")));

            List<WebElement> listLi = driver.findElements(By.cssSelector("li[id^='doc']"));
            if (listLi.size() == 0){
                continue;
            }
            int sizeSubMenu = listLi.size();
            for (int j = 0; j < sizeSubMenu; j++) {
                System.out.println("ClickClick " + (i+1) + " / " + (j+1));
                WebElement itemName = driver.findElements(By.cssSelector("li[id^='doc']")).get(j);
                itemName.click();

                assertTrue(areElementsPresent(By.tagName("h1")));
            }
        }
    }
}

