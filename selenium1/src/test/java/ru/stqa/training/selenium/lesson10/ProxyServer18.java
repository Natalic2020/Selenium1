package ru.stqa.training.selenium.lesson10;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import org.junit.Test;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.concurrent.TimeUnit;

public class ProxyServer18 {

    @Test
    public void BrowserMobProxyServerTest() throws InterruptedException {
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.start(0);
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
        ChromeOptions options  = new ChromeOptions();
        options.setExperimentalOption("w3c", false);
        options.merge(capabilities);
        EventFiringWebDriver driver = new EventFiringWebDriver(new ChromeDriver(options));

        proxy.newHar();
        driver.get("https://webdriver.ru/");
        Har har = proxy.endHar();
        har.getLog().getEntries().forEach( l -> System.out.println(l.getResponse().getStatus() +
                " : " + l.getRequest().getUrl()));
    }

//    @Test
//    public void BrowserProxyServerTest() throws InterruptedException {
//        Proxy proxy = new Proxy();
//        proxy.setHttpProxy("localhost:8866");
//        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setCapability("proxy", proxy);
//        WebDriver driver = new ChromeDriver(caps);
//
//        driver.get("https://www.germanpersonnel.de/");
//    }

}
