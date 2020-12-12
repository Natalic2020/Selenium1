package ru.stqa.training.selenium.lesson11.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.training.selenium.lesson11.pages.Page;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class ProductPage extends Page {

    @FindBy(css="span.quantity")
    public WebElement quantityCart;

    @FindBy(name="add_cart_product")
    public WebElement cart;

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("https://litecart.stqa.ru");
    }

    public void selectSize(String size)
    {
        if (driver.findElements(By.name("options[Size]")).size() > 0) {
            new Select(driver.findElement(By.name("options[Size]"))).selectByValue(size);
        }
    }

    public void openProduct(int i) {
        List<WebElement> products = driver.findElements(By.cssSelector(".link[href^='https://litecart.stqa.ru/en']"));
        products.get(i).click();
    }

    public void addToCart() {
        int qCart = Integer.parseInt(quantityCart.getText());
        WebElement addToCart = wait.until(visibilityOfElementLocated(By.name("add_cart_product")));
        addToCart.click();
        wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"), String.valueOf(qCart + 1)));
        System.out.println("quantity " + quantityCart.getText());
    }
}
