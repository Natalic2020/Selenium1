package ru.stqa.training.selenium.lesson9;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Grid15 {

    @Test
    public void gridChromeTest() throws MalformedURLException, InterruptedException {

        DesiredCapabilities cap=new DesiredCapabilities();
        cap.setBrowserName("chrome");
        cap.setPlatform(Platform.WINDOWS);
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        options.merge(cap);

        WebDriver driver = new RemoteWebDriver(new URL("http://192.168.178.66:4444/wd/hub"),  options);
        TimeUnit.MILLISECONDS.sleep(500);
        driver.get("http://google.com");
        driver.findElement(By.name("q")).sendKeys("Selenium Grid Tutorial");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
    }

    @Test
    public void gridFireFoxTest() throws MalformedURLException, InterruptedException {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setBrowserName("firefox");
        capabilities.setPlatform(Platform.WINDOWS);

        WebDriver driver = new RemoteWebDriver(new URL("http://192.168.178.66:4444/wd/hub"),  capabilities);
        TimeUnit.MILLISECONDS.sleep(500);
        driver.get("http://google.com");
        driver.findElement(By.name("q")).sendKeys("Selenium Grid Tutorial");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
    }

    @Test
    public void gridIETest() throws MalformedURLException, InterruptedException {
        DesiredCapabilities capabilities =  DesiredCapabilities.internetExplorer();
        capabilities.setBrowserName("internet explorer");
        capabilities.setPlatform(Platform.WINDOWS);

        WebDriver driver = new RemoteWebDriver(new URL("http://192.168.178.66:4444/wd/hub"),  capabilities);
        TimeUnit.MILLISECONDS.sleep(500);
        driver.get("http://google.com");
        driver.findElement(By.name("q")).sendKeys("Selenium Grid Tutorial");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
    }

}
