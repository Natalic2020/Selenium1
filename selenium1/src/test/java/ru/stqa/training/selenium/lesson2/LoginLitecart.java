package ru.stqa.training.selenium.lesson2;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.training.selenium.TestBaseFireFoxNightly;

import java.sql.SQLOutput;
import java.util.concurrent.TimeUnit;

public class LoginLitecart extends TestBaseFireFoxNightly {

    @Test
    public void loginAdminTest(){
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        String expected = "My Store";
        String actuil = driver.getTitle();

        Assert.assertEquals(expected, actuil);
    }
}
