package ru.stqa.training.selenium.lesson11.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import ru.stqa.training.selenium.lesson11.pages.CartPage;
import ru.stqa.training.selenium.lesson11.pages.ProductPage;

public class Application {

    private WebDriver driver;
    private ProductPage productPage;
    private CartPage cartPage;

    public Application() {
        FirefoxOptions options = new FirefoxOptions().setLegacy(false);
        driver = new FirefoxDriver(options);

        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    public void quit() {
        driver.quit();
    }

    public void addProductsToCart(int countProducts) throws InterruptedException {
        productPage.open();
        for (int i = 1; i <= countProducts; i++) {
            productPage.openProduct(i);
            productPage.selectSize("Small");
            productPage.addToCart();
        }
    }

    public void removeAllProductsFromCart() {
        cartPage.open();
        System.out.println("removeCartItem whole " + cartPage.removeCartItems.size());
        cartPage.shotcut.click();
        while (cartPage.removeCartItems.size() > 0) {
            cartPage.removeCartItem();
            System.out.println("removeCartItem " + cartPage.removeCartItems.size());
        }
    }

    public int getCountProductsInCart() {
        productPage.open();
        return Integer.parseInt(productPage.quantityCart.getText());
    }

}
