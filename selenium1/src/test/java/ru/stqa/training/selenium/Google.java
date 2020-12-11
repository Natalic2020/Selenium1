package ru.stqa.training.selenium;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.concurrent.TimeUnit;

public class Google {

    @Test
    public void getBrowserLogs() throws InterruptedException {
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.start(0);
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

//       driver = new FirefoxDriver(capabilities);


        WebDriver driver = new ChromeDriver(capabilities);
//        driver.register(new TestBaseWithListener.MyListener());
//        proxy.newHar();
        driver.get("https://webdriver.ru/");
        TimeUnit.MILLISECONDS.sleep(500);
//        Har har = proxy.endHar();
//        har.getLog().getEntries().forEach( l -> System.out.println(l.getResponse().getStatus() +
//                " : " + l.getRequest().getUrl()));
//        TimeUnit.MILLISECONDS.sleep(2000);
//        System.out.println(driver.manage().logs().getAvailableLogTypes());
//        driver.manage().logs().get("browser").forEach(System.out::println);
    }

    @Test
    public void googleProxyTest() throws InterruptedException {
//        proxy.newHar();
//        driver.navigate().to("http://google.com");
//        TimeUnit.MILLISECONDS.sleep(500);
//        Har har = proxy.endHar();
//        har.getLog().getEntries().forEach( l -> System.out.println(l.getResponse().getStatus() +
//                " : " + l.getRequest().getUrl()));
    }



}
