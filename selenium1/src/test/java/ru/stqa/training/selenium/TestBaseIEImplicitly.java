package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TestBaseIEImplicitly {
        public static ThreadLocal<WebDriver> tiDriver = new ThreadLocal<>();
        public WebDriver driver;
        public WebDriverWait wait;

        @Before
        public void start(){
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
            driver = new InternetExplorerDriver(caps);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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