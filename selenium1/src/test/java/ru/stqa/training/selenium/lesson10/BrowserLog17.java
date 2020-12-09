package ru.stqa.training.selenium.lesson10;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebElement;
import ru.stqa.training.selenium.TestBaseWithListener;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BrowserLog17 extends TestBaseWithListener {

    private void loginAdmin() {
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        String expected = "My Store";
        String actuil = driver.getTitle();
        Assert.assertEquals(expected, actuil);
    }

    @Test
    public void massageCheckTest() throws InterruptedException {
        loginAdmin();
        List<WebElement> products = getProducts();

//        System.out.println(driver.manage().logs().getAvailableLogTypes());
//        driver.manage().logs().get("performance").getAll().forEach(System.out::println);

        for (int i = 0; i < products.size(); i++) {

            products = getProducts();
            products.get(i).click();
            driver.manage().logs().get("browser").getAll().forEach(System.out::println);
            Assert.assertEquals(0, driver.manage().logs().get("browser").getAll().size());
        }
    }

    private List<WebElement> getProducts() {
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");

        List<WebElement> products = driver.findElements(By
                .cssSelector("[href^='http://localhost/litecart/admin/?app=catalog&doc=edit_product&category_id']" +
                        ":not([title='Edit'])"));
        return products;
    }
}
