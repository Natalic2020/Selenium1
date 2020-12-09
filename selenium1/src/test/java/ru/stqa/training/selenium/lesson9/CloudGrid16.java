package ru.stqa.training.selenium.lesson9;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class CloudGrid16 {

    @Test
    public void browserstackTest() throws MalformedURLException, InterruptedException {

        final String USERNAME = "nataliesvitlychn1";
        final String AUTOMATE_KEY = "zdKMv4WVDQHTdNGRL6WN";
        final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "80");
        caps.setCapability("name", "nataliesvitlychn1's First Test");

        WebDriver driver = new RemoteWebDriver(new URL(URL), caps);

        TimeUnit.MILLISECONDS.sleep(500);
        driver.get("https://www.germanpersonnel.de/");
//        driver.get("http://google.com");
//        driver.findElement(By.name("q")).sendKeys("Selenium Grid Tutorial");
//        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);

    }
}
