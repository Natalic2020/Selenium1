package ru.stqa.training.selenium.lesson11.tests;

import org.junit.Assert;
import org.junit.Test;

public class Cart19 extends TestBase {

    @Test
    public  void checkCartTest() throws InterruptedException {
        int firstInCart = app.getCountProductsInCart();
        app.addProductsToCart(3);
        Assert.assertTrue("Add no 3 Products ", firstInCart + 3 == app.getCountProductsInCart());
        app.removeAllProductsFromCart();
        Assert.assertTrue("Did not remove all Products ", 0 == app.getCountProductsInCart());
    }
}
