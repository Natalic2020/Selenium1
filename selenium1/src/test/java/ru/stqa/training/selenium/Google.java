package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.concurrent.TimeUnit;

public class Google extends TestBaseWithListener{

    @Test
    public void getBrowserLogs() throws InterruptedException {
        driver.navigate().to("http://selenium2.ru");
        TimeUnit.MILLISECONDS.sleep(2000);
//        System.out.println(driver.manage().logs().getAvailableLogTypes());
        driver.manage().logs().get("browser").forEach(System.out::println);
    }


    @Test
    public void googleTest(){
        driver.get("http://google.com");
        driver.findElement(By.name("q")).sendKeys("webdriver");
//        driver.findElement(By.name("q"))
        driver.findElement(By.name("btnG")).sendKeys(Keys.ENTER);
    }
}
