package ru.stqa.training.selenium.lesson4;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.training.selenium.TestBaseImplicitlyWait;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class Sticker8 extends TestBaseImplicitlyWait {

    @Test
    public  void checkStikerByGoodsMainPageTest() {
        driver.get("http://localhost/litecart/");
        List<WebElement> productList = driver.findElements(By.className("product"));
        System.out.println(" Amount product : " + productList.size());
        productList.forEach(this::checkSticker);
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
