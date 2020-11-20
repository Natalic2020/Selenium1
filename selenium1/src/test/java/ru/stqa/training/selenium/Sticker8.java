package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class Sticker8 extends  TestBaseimplicitlyWait{

    @Test
    public  void checkStikerByGoodsMainPageTest() {
        driver.get("http://localhost/litecart/");
        checkStickerByList("box-most-popular");
        checkStickerByList("box-campaigns");
        checkStickerByList("box-latest-products");
    }

    private void checkStickerByList(String locator){
        WebElement boxCampaigns = driver.findElement(By.id(locator));
        List<WebElement> boxList = boxCampaigns.findElements(By.tagName("li"));
        System.out.println(locator + "  " +boxList.size());
        boxList.forEach(this::checkSticker);
    }

    private void checkSticker(WebElement webElement){
        List<WebElement> stickerList = webElement.findElements(By.cssSelector("[class^=sticker]"));
        assertTrue(oneStickerPresent(stickerList));
    }

    private boolean oneStickerPresent(List<WebElement> stickerList) {
        if (stickerList.size() == 1){
            return true;
        }
        return false;
    }
}

