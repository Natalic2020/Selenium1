package ru.stqa.training.selenium.lesson6;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.training.selenium.TestBaseImplicitlyWait;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class Product12 extends TestBaseImplicitlyWait {
    private final static String NAME = "Cat";
    private final static String CODE = "55555";
    private final static String KEYWORDS = "Cat";
    private final static String HEAD_TITLE = "Head title Cat";
    private final static String META_DESCRIPTION = "Mete description Cat";
    private final static String SCORT_DESCRIPTION = "My favorite cat";
    private final static String DESCRIPTION = "MThe cat (Felis catus) is a domestic species of small carnivorous mammal."
            + "[1][2] It is the only domesticated species in the family Felidae and is often referred to as the domestic"
            + "cat to distinguish it from the wild members of the family.[4] A cat can either be a house cat, a farm cat"
            + "or a feral cat; the latter ranges freely and avoids human contact.[5] Domestic cats are valued by humans"
            + "for companionship and their ability to hunt rodents. About 60 cat breeds are recognized by various cat "
            + "registries.[6]. The cat is similar in anatomy to the other felid species: it has a strong flexible body,"
            + "quick reflexes, sharp teeth and retractable claws adapted to killing small prey. Its night vision and "
            + "sense of smell are well developed. Cat communication includes vocalizations like meowing, purring, "
            + "trilling, hissing, growling and grunting as well as cat-specific body language. A predator that is most "
            + "active at dawn and dusk, the cat is a solitary hunter but a social species. It can hear sounds too faint "
            + "or too high in frequency for human ears, such as those made by mice and other small mammals.";

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
    public  void createProductTest() throws InterruptedException {
        loginAdmin();
        createProduct();
        fillGeneral();
        fillInformation();
        fillPrices();
        checkNewProduct();
    }
    private void checkNewProduct() throws InterruptedException {
        List<WebElement> productList = driver.findElements(By.cssSelector("[href^='http://localhost/litecart/admin/?app=catalog&doc=edit_product&category_id=0&product_id=']"));
         boolean hasNewProduct = false;
        hasNewProduct = productList.stream().map(p->p.getText()).anyMatch(NAME::equals);
        Assert.assertTrue("Do not find created Product ", hasNewProduct);
    }

    private void fillInformation() throws InterruptedException {
        WebElement catalog = driver.findElement(By.cssSelector("[href='#tab-information']"));
        catalog.click();
        TimeUnit.MILLISECONDS.sleep(500);
        Select manufacturer = new Select(driver.findElement(By.name("manufacturer_id"))) ;
        manufacturer.selectByValue("1");
        WebElement keywords = driver.findElement(By.name("keywords"));
        keywords.sendKeys(KEYWORDS);
        WebElement shortDescription = driver.findElement(By.name("short_description[en]"));
        shortDescription.sendKeys(SCORT_DESCRIPTION);
        WebElement description = driver.findElement(By.cssSelector(".trumbowyg-editor"));
        System.out.println("ssssss");
        description.sendKeys(DESCRIPTION);
        WebElement headTitle = driver.findElement(By.name("head_title[en]"));
        headTitle.sendKeys(HEAD_TITLE);
        WebElement metaDescription = driver.findElement(By.name("meta_description[en]"));
        metaDescription.sendKeys(META_DESCRIPTION);
    }

    private void fillPrices() throws InterruptedException {
        WebElement prices = driver.findElement(By.cssSelector("[href='#tab-prices']"));
        prices.click();
        TimeUnit.MILLISECONDS.sleep(500);
        WebElement purchasePrice = driver.findElement(By.name("purchase_price"));
        purchasePrice.clear();
        purchasePrice.sendKeys("125");
        Select currency = new Select(driver.findElement(By.name("purchase_price_currency_code"))) ;
        currency.selectByValue("EUR");
        WebElement priceUSD = driver.findElement(By.name("prices[USD]"));
        priceUSD.sendKeys("202");
//        WebElement grossPriceUSD = driver.findElement(By.name("gross_prices[USD]"));
//        grossPriceUSD.sendKeys("111");
        WebElement save = driver.findElement(By.name("save"));
        save.click();
    }

    private void createProduct() throws InterruptedException {
        driver.get("http://localhost/litecart/admin/");
        TimeUnit.MILLISECONDS.sleep(500);
        WebElement catalog = driver.findElement(By.cssSelector("[href='http://localhost/litecart/admin/?app=catalog&doc=catalog']"));
        catalog.click();
        TimeUnit.MILLISECONDS.sleep(500);
        WebElement addNewProductButton = driver.findElement(By.cssSelector("[href='http://localhost/litecart/admin/?category_id=0&app=catalog&doc=edit_product']"));
        addNewProductButton.click();
        TimeUnit.MILLISECONDS.sleep(500);
    }

    private void fillGeneral() throws InterruptedException {
        WebElement status = driver.findElement(By.cssSelector(("[name='status'][value='1']")));
        status.click();
        System.out.println("status");
        WebElement name = driver.findElement(By.name("name[en]"));
        name.sendKeys(NAME);
        System.out.println("name");
        WebElement code = driver.findElement(By.name("code"));
        code.sendKeys(CODE);
        System.out.println("code");
        WebElement productGroupsUnisex = driver.findElement(By.cssSelector("[value='1-3']"));
        productGroupsUnisex.click();
        System.out.println("unisex");
        WebElement quantity = driver.findElement(By.name("quantity"));
        quantity.clear();
        quantity.sendKeys("33.00");
        Select quantityUnit = new Select(driver.findElement(By.name("quantity_unit_id")));
        quantityUnit.selectByValue("1");
        Select deliveryStatus = new Select(driver.findElement(By.name("delivery_status_id")));
        deliveryStatus.selectByValue("1");
        Select soldOutStatus = new Select(driver.findElement(By.name("sold_out_status_id")));
        soldOutStatus.selectByValue("2");
        WebElement image = driver.findElement(By.name("new_images[]"));
        System.out.println("image...");
        image.sendKeys(receivePath("s1200-1.jpg"));
        System.out.println("image");
        WebElement dateValidFrom = driver.findElement(By.name("date_valid_from"));
        System.out.println("date");
//        driver.navigate().to("http://jqueryui.com/datepicker/");
//        driver.switchTo().frame(
//                driver.findElement(By.cssSelector("iframe.demo-frame")));
//        SetDatepicker(driver, "[name=date_valid_from]", "02/20/2020");
        dateValidFrom.sendKeys("01.01.2020");
        System.out.println("date picker");
        WebElement dateValidTo = driver.findElement(By.name("date_valid_to"));
        dateValidTo.sendKeys("05.05.2022");
        System.out.println("date picker to");
    }

    String receivePath(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();

        File file = Optional
                .ofNullable(classLoader.getResource(fileName))
                .map(URL::getFile)
                .map(File::new)
                .orElseThrow(() -> new IllegalArgumentException("File doesn't exis"));

        return file.getAbsolutePath();
    }

    public void SetDatepicker(WebDriver driver, String cssSelector, String date)
    {
        new WebDriverWait(driver, 30).until(
                d -> driver.findElement(By.cssSelector(cssSelector)).isDisplayed());
        ((JavascriptExecutor) driver).executeScript(
            String.format("$('{0}').datepicker('setDate', '{1}')", cssSelector, date));
    }

}
