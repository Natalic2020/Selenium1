package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class TestBaseFireFoxNightly {

        public static ThreadLocal<WebDriver> tiDriver = new ThreadLocal<>();
        public WebDriver driver;
        public WebDriverWait wait;

        @Before
        public void start(){
            FirefoxOptions options = new FirefoxOptions();
            options.setBinary(new FirefoxBinary(new File("c:\\Program Files\\Firefox Nightly\\firefox.exe")));
            driver = new FirefoxDriver(options);
        wait = new WebDriverWait(driver, 11);
    }

        public boolean areElementsPresent(By locator){
        return driver.findElements(locator).size() > 0;
    }

        @After
        public void stop(){
        driver.quit();
        driver = null;
    }
}

