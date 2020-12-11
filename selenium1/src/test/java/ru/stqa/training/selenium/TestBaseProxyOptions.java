package ru.stqa.training.selenium;

import com.google.common.io.Files;
import net.lightbody.bmp.BrowserMobProxy;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

public class TestBaseProxyOptions {
    public static ThreadLocal<EventFiringWebDriver> tiDriver = new ThreadLocal<>();
    //    public EventFiringWebDriver driver;
    public WebDriverWait wait;
    public BrowserMobProxy proxy;
    //    public Proxy proxy;
    public WebDriver driver;

    public static class MyListener extends AbstractWebDriverEventListener {
        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by);
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by + " found") ;
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            System.out.println(throwable);
            File tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            new File("screen-" + System.currentTimeMillis() + ".png");

            File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File screen = new File("screen-" + System.currentTimeMillis() + ".png");
            try {
                Files.copy(tmp, screen);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(screen);
        }
    }

    @Before
    public void start(){



//
//        proxy = new BrowserMobProxyServer();
//        proxy.start(8866);
//        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
//        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setCapability(CapabilityType.PROXY, seleniumProxy);
//        driver = new EventFiringWebDriver(new ChromeDriver(caps));
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--proxy-server=http://localhost:8866");

        //options.merge(caps);
//        driver = new ChromeDriver(options);

//        Proxy proxy = new Proxy();
//        proxy.setHttpProxy("localhost:8866");
//        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setCapability("proxy", proxy);
//        WebDriver driver = new ChromeDriver(caps);



//        proxy = new BrowserMobProxyServer();
//        proxy.start(8888);
//        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
////        proxy.setHttpProxy("localhost:8888");
//        DesiredCapabilities caps = new DesiredCapabilities();
//
////        caps.setCapability(ChromeOptions.CAPABILITY, proxy);
//        caps.setCapability("proxy", proxy);
//        ChromeOptions options = new ChromeOptions();
////        options.addArguments("--proxy-server=socks5://lo:8888");
//
//        options.merge(caps);
//        WebDriver driver = new ChromeDriver(options);

        ///////////////////////
//        DesiredCapabilities cap=new DesiredCapabilities();
//        cap.setBrowserName("chrome");
//        cap.setPlatform(Platform.WINDOWS);
//        ChromeOptions options = new ChromeOptions();
//        options.setHeadless(true);
//        options.merge(cap);



//        driver = new EventFiringWebDriver(new ChromeDriver(caps));
//        driver.register(new TestBaseWithListener.MyListener());
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public boolean areElementsPresent(By locator){
        return driver.findElements(locator).size() > 0;
    }

    @After
    public void stop(){
//        driver.quit();
        //      driver = null;
    }

}
