package ru.stqa.training.selenium.lesson5;

import org.apache.commons.exec.util.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import ru.stqa.training.selenium.TestBaseFireFoxImplicitly;
import ru.stqa.training.selenium.TestBaseIEImplicitly;
import ru.stqa.training.selenium.TestBaseImplicitlyWait;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

    public class Price10 extends TestBaseImplicitlyWait {
//    public class Price10 extends TestBaseFireFoxImplicitly {
//    public class Price10 extends TestBaseIEImplicitly {

    @Test
    public  void checkPriceTest() {
        driver.get("http://localhost/litecart/");
        List<WebElement> campaignsList = driver.findElements(By.cssSelector("#box-campaigns .product"));
        String href = "";
        String nameMain = "";
        HashMap<String,String> regularPriceMainProperties = new HashMap<>();
        HashMap<String,String> campaignPriceMainProperties = new HashMap<>();
        if (campaignsList.size()>0){
            WebElement campaignFirstElement = campaignsList.get(0);
            href = campaignFirstElement.findElement(By.className("link")).getAttribute("href");
            System.out.println("href : " + href);
            nameMain = campaignFirstElement.findElement(By.className("name")).getText();
            System.out.println("nameMain : " + nameMain);
            WebElement regularPriceMain = campaignFirstElement.findElement(By.className("regular-price"));
            regularPriceMainProperties =  receivePriceProperties(regularPriceMain);
            WebElement campaignPriceMain = campaignFirstElement.findElement(By.className("campaign-price"));
            campaignPriceMainProperties =  receivePriceProperties(campaignPriceMain);
        }
        driver.get(href);
        String nameContent = driver.findElement(By.tagName("h1")).getText();
        WebElement regularPriceContent = driver.findElement(By.className("regular-price"));
        HashMap<String,String> regularPriceContentProperties =  receivePriceProperties(regularPriceContent);
        WebElement campaignPriceContent = driver.findElement(By.className("campaign-price"));
        HashMap<String,String> campaignPriceContentProperties =  receivePriceProperties(campaignPriceContent);

        Assert.assertEquals("Name product on main page  is not like on content page",nameMain, nameContent);
        Assert.assertEquals("Regular price on main page  is not like on content page", regularPriceMainProperties.get("price"), regularPriceContentProperties.get("price"));
        Assert.assertEquals("Action price on main page  is not like on content page",campaignPriceMainProperties.get("price"), campaignPriceContentProperties.get("price"));

        checkPropertie(regularPriceMainProperties, campaignPriceMainProperties);
        checkPropertie(regularPriceContentProperties, campaignPriceContentProperties);

    }

    private HashMap<String, String> receivePriceProperties(WebElement webElement){
        HashMap<String,String> priceProperties = new HashMap<>();
        priceProperties.put("price", webElement.getText());
        priceProperties.put("font-size", webElement.getCssValue("font-size"));
        priceProperties.put("font-weight", webElement.getCssValue("font-weight"));
        priceProperties.put("color", webElement.getCssValue("color"));
        priceProperties.put("text-decoration-line", webElement.getCssValue("text-decoration-line"));

        System.out.println("regularPriceTextMain : " + webElement.getText());
        System.out.println("font-size : " + webElement.getCssValue("font-size"));
        System.out.println("font-weight : " + webElement.getCssValue("font-weight"));
        System.out.println("color : " + webElement.getCssValue("color"));
        System.out.println("text-decoration-line : " + webElement.getCssValue("text-decoration-line"));
        return priceProperties;
    }
       private void checkPropertie(HashMap<String,String> regularProperties, HashMap<String,String> campaignProperties){
           Assert.assertTrue("Action  font-weight price is not bigger then regular ", Integer.valueOf(regularProperties.get("font-weight")) <
                   Integer.valueOf(campaignProperties.get("font-weight")));
           if (!driver.toString().contains("InternetExplorer")){
               Assert.assertEquals("Regular price is not striked out ","line-through", regularProperties.get("text-decoration-line"));
               Assert.assertEquals("Action price is  striked out ","none", campaignProperties.get("text-decoration-line"));
           }

           Assert.assertEquals("Action  font price is not bigger then regular ",true, resieveFontSize(campaignProperties) > resieveFontSize(regularProperties));
           HashMap<String, Integer> regularRGB = decomposeRGB( regularProperties);
           Assert.assertEquals("Regular color price is not grey. It have to be red = blue = green ",true, regularRGB.get("red") == regularRGB.get("green") && regularRGB.get("blue") == regularRGB.get("green"));
           HashMap<String, Integer> campaignRGB = decomposeRGB( campaignProperties);
           Assert.assertEquals("Action color price is not red. It have  blue = green = 0 ",true, 0 == campaignRGB.get("green") && campaignRGB.get("blue") == 0);
       }

       private Double resieveFontSize(HashMap<String,String> properties){
           String fontSizeString = properties.get("font-size");
           System.out.println(fontSizeString.substring(0, fontSizeString.length()-2));
           return Double.parseDouble(fontSizeString.substring(0, fontSizeString.length()-2));
       }

       private HashMap<String, Integer> decomposeRGB(HashMap<String,String> properties){
        String color = properties.get("color");
        int indexOfRed = color.indexOf("(",0) + 1;
        int indexOfGreen = color.indexOf(",",indexOfRed) + 1;
        int indexOfBlue = color.indexOf(",",indexOfGreen) + 1;
        int indexOfEnd = color.indexOf(",",indexOfBlue);
        if (indexOfEnd == -1){
            indexOfEnd = color.indexOf(")",indexOfBlue);
        }
           System.out.println(indexOfRed + " : " + indexOfGreen + " : " + indexOfBlue);
        int red = Integer.parseInt(color.substring(indexOfRed, indexOfGreen - 1).trim());
        int green = Integer.parseInt(color.substring(indexOfGreen, indexOfBlue - 1).trim());
        int blue = Integer.parseInt(color.substring(indexOfBlue, indexOfEnd).trim());
           System.out.println(red + " : " + green + " : " + blue);
           HashMap<String, Integer> rgb = new HashMap<>();
           rgb.put("red", red);
           rgb.put("green", green);
           rgb.put("blue", blue);
           return rgb;
       }

}
