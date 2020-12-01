package ru.stqa.training.selenium.lessons7;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.training.selenium.TestBase;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Cart13 extends TestBase {

    @Test
    public  void checkCartTest() throws InterruptedException {
        driver.get("https://litecart.stqa.ru");
        addProductsToCart();
        WebElement checkout = driver.findElement(By.cssSelector(".link[href^='https://litecart.stqa.ru/en/checkout']"));
        checkout.click();
        removeProductsFromCart();
    }

    private void removeProductsFromCart() throws InterruptedException {
        List<WebElement> removeCartItems = driver.findElements(By.name("remove_cart_item"));
        System.out.println("removeCartItem " + removeCartItems.size());

        while (driver.findElements(By.name("remove_cart_item")).size() > 0) {

            WebElement item = driver.findElements(By.cssSelector("td.item")).get(0);

        try {
            WebElement cartItem = wait.until(visibilityOf(driver.findElement(By.cssSelector("button[name=remove_cart_item]"))));
            cartItem.click();
            wait.until(ExpectedConditions.stalenessOf(item));
        } catch (Exception e){
            System.out.println(e.getMessage());
            continue;
        }
            removeCartItems = driver.findElements(By.cssSelector("button[name=remove_cart_item]"));
            System.out.println("removeCartItem " + removeCartItems.size());
        }
    }

    private void addProductsToCart() throws InterruptedException {
        for (int i=1; i < 4; i++) {
            List<WebElement> products = wait.until(visibilityOfAllElementsLocatedBy(By.cssSelector(".link[href^='https://litecart.stqa.ru/en']")));
            products.get(i).click();

            if (driver.findElements(By.name("options[Size]")).size() > 0) {
                Select manufacturer = new Select(driver.findElement(By.name("options[Size]")));
                manufacturer.selectByValue("Small");
            }

            WebElement quantityCart = driver.findElement(By.cssSelector("span.quantity"));
            int qCart = Integer.parseInt(quantityCart.getText());
            WebElement addToCart = wait.until(visibilityOfElementLocated(By.name("add_cart_product")));
            addToCart.click();

            wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"), String.valueOf(qCart + 1)));

            quantityCart = driver.findElement(By.cssSelector("span.quantity"));
            System.out.println("quantity " + quantityCart.getText());
        }
    }
}
