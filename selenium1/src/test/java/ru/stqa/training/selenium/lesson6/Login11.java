package ru.stqa.training.selenium.lesson6;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.training.selenium.TestBaseImplicitlyWait;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Login11 extends TestBaseImplicitlyWait {
    private final static String FIRST_NAME = "Natalie";
    private final static String LAST_NAME = "Svitlychna";
    private final static String ADDRESS_1 = "Westendstr. 95";
    private final static String POSTCODE = "80339";
    private final static String CITY = "Munich";
    private final static String COUNTRY = "United States";
    private final static String STATE = "State 1";
    private static String EMAIL;
    private final static String PHONE = "+4915757612129";
    private final static String PASSWORD = "Natali168";

    @Before public void start1(){
        EMAIL = "nata" + new Random().nextInt(100) + "@m.ru";
        System.out.println(EMAIL);
    }

    @Test
    public  void checkLoginTest() throws InterruptedException {
        createNewUser();
        logout();
        login();
        logout();
    }

    private void login() {
        WebElement loginEmail = driver.findElement(By.name("email"));
        loginEmail.sendKeys(EMAIL);
        WebElement loginPassword = driver.findElement(By.name("password"));
        loginPassword.sendKeys(PASSWORD + Keys.ENTER);
    }

    private void logout() {
        WebElement logout = driver.findElement(By.cssSelector("#box-account [href='http://localhost/litecart/en/logout']"));
        logout.click();
    }

    private void createNewUser() throws InterruptedException {
        driver.get("http://localhost/litecart/");
        WebElement newCustomer = driver.findElement(By.cssSelector("#box-account-login [href='http://localhost/litecart/en/create_account']"));

        newCustomer.click();
        TimeUnit.MILLISECONDS.sleep(500);
        WebElement firstName = driver.findElement(By.cssSelector("[name=firstname]"));
        firstName.sendKeys(FIRST_NAME + Keys.ENTER);
        System.out.println("FIRST_NAME " + firstName.getAttribute("value"));
        WebElement lastName = driver.findElement(By.cssSelector("[name=lastname]"));
        lastName.sendKeys(LAST_NAME + Keys.ENTER);
        System.out.println("LAST_NAME " + lastName.getAttribute("value"));
        WebElement address1 = driver.findElement(By.cssSelector("[name=address1]"));
        address1.sendKeys(ADDRESS_1 + Keys.ENTER);
        System.out.println("ADDRESS_1 " + address1.getAttribute("value"));
        WebElement postcode = driver.findElement(By.cssSelector("[name=postcode]"));
        postcode.sendKeys(POSTCODE + Keys.ENTER);
        System.out.println("POSTCODE " + postcode.getAttribute("value"));
        WebElement city = driver.findElement(By.cssSelector("[name=city]"));
        city.sendKeys(CITY + Keys.ENTER);
        System.out.println("CITY " + city.getAttribute("value"));
        Select  country = new Select(driver.findElement(By.name("country_code"))) ;
        country.selectByVisibleText(COUNTRY);
        System.out.println("COUNTRY ");// + driver.findElement(By.id("select2-country_code-bd-container")).getText());
        WebElement hiddenState = driver.findElement(By.cssSelector("[style='opacity: 0.15;']"));
        unhideStyle(driver, hiddenState);
        WebElement state = driver.findElement(By.name("zone_code"));

        System.out.println("zone_code");
        unhideType(driver, state);
        state.sendKeys(STATE + Keys.ENTER);
        System.out.println("STATE " + state.getAttribute("value"));
        WebElement email = driver.findElement(By.cssSelector("[name=email]"));
        email.sendKeys(EMAIL + Keys.ENTER);
        System.out.println("EMAIL " + email.getAttribute("value"));
        WebElement phone = driver.findElement(By.cssSelector("[name=phone]"));
        phone.sendKeys(Keys.HOME + PHONE + Keys.ENTER);
        System.out.println("PHONE " + phone.getAttribute("value"));
        WebElement password = driver.findElement(By.cssSelector("[name=password]"));
        password.sendKeys(PASSWORD);
        System.out.println("PASSWORD " + password.getAttribute("value"));
        WebElement confirmedPassword = driver.findElement(By.cssSelector("[name=confirmed_password]"));
        System.out.println("Confirmed PASSWORD 1");
        confirmedPassword.sendKeys(PASSWORD + Keys.ENTER);
        System.out.println("PASSWORD + Keys.ENTER");
    }

    public void unhideStyle(WebDriver driver, WebElement element) {
        String script = "arguments[0].style.opacity=1;"
                + "arguments[0].style['transform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['MozTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['WebkitTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['msTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['OTransform']='translate(0px, 0px) scale(1)';"
                + "return true;";
        ((JavascriptExecutor) driver).executeScript(script, element);
    }

    public void unhideType(WebDriver driver, WebElement element) {
        String script = "arguments[0].type=null;"
                + "return true;";
        ((JavascriptExecutor) driver).executeScript(script, element);
    }
}
