package ru.stqa.training.selenium.lesson11.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends Page {

    @FindBy(name="remove_cart_item")
    public
    List<WebElement> removeCartItems;

    @FindBy(css="[href='#']")
    public WebElement shotcut;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("https://litecart.stqa.ru/en/checkout");
    }

    public void removeCartItem()  {
            WebElement dataTable = driver.findElement(By.className("dataTable"));
            WebElement cartItem = driver.findElement(By.cssSelector("[name=remove_cart_item]"));
            cartItem.click();
            wait.until(ExpectedConditions.stalenessOf(dataTable));
        }
}
