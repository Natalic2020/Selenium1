package ru.stqa.training.selenium.lessons8;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import ru.stqa.training.selenium.TestBaseFireFox;

import java.util.List;
import java.util.Set;

public class Windows14 extends TestBaseFireFox {

        private void loginAdmin(){
            driver.get("http://localhost/litecart/admin/");
            driver.findElement(By.name("username")).sendKeys("admin");
            driver.findElement(By.name("password")).sendKeys("admin");
            driver.findElement(By.name("login")).click();
            String expected = "My Store";
            String actuil = driver.getTitle();
            Assert.assertEquals(expected, actuil);
        }

        @Test
        public  void windowsTest()  {
            loginAdmin();
            driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
            WebElement newCountry = driver.findElement(By.cssSelector("[href='http://localhost/litecart/admin/?app=countries&doc=edit_country']"));
            newCountry.click();
            String firstWindow = driver.getWindowHandle();
            System.out.println("currentWindow" + firstWindow);
            final Set<String> oldWindows = driver.getWindowHandles();
            System.out.println("oldWindows" + oldWindows);
            List<WebElement> hrefs = driver.findElements(By.cssSelector("[target='_blank']"));
            hrefs.forEach(href ->{
                href.click();
                String newWindow = wait.until(anyWindowOtherthan(oldWindows));
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(firstWindow);
            });
            System.out.println(driver.getWindowHandles());
           }

        public ExpectedCondition<String> anyWindowOtherthan(Set<String> oldWindows){
            return new ExpectedCondition<String>() {
                @Override
                public String apply(WebDriver driver) {
                    Set<String> handles = driver.getWindowHandles();
                    handles.removeAll(oldWindows);
                    return handles.size()>0 ? handles.iterator().next():null;
                }
            };
        }
        }