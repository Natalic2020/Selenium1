package ru.stqa.training.selenium.lesson5;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.training.selenium.TestBaseImplicitlyWait;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

public class SortCountry9 extends TestBaseImplicitlyWait {

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
    public  void countiesNameSortTest()  {
        loginAdmin();
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        List<WebElement> countryList = driver.findElements(By.cssSelector("a[href^='http://localhost/litecart/admin/?app=countries&doc=edit_country&country_code='"));
        System.out.println("countryList " + countryList.size());
        List<String> countryNameList = new ArrayList<>();
        List<String> hrefCountryWithZones = new ArrayList<>();
        final int[] i = {1};
        countryList.forEach(row -> {
            String countryName = row.getText();
            if (!countryName.isEmpty()){
                countryNameList.add(countryName);
                addZone(row, hrefCountryWithZones);
            }
        });
        List<String> countryNameSortList = countryNameList.stream().sorted().collect(Collectors.toList());
        Assert.assertEquals(countryNameSortList, countryNameList);

        checkZone(hrefCountryWithZones);
    }

    @Test
    public  void zonesNameSortTest()  {
        loginAdmin();
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        List<String> hrefCountryWithZones =  fillListWithHrefCountries();
        hrefCountryWithZones.forEach(url -> {
            List<String> zoneNameList = fillZone(url);
            List<String> zoneNameSortList = zoneNameList.stream().sorted().collect(Collectors.toList());
            Assert.assertEquals(zoneNameSortList, zoneNameList);
        } );
    }

    private  List<String> fillListWithHrefCountries() {
        List<WebElement> countryList = driver.findElements(By
                .cssSelector("a[href^='http://localhost/litecart/admin/?app=geo_zones&doc=edit_geo_zone&page=1&geo_zone_id'"));
        List<String> hrefCountryWithZones = new ArrayList<>();
        countryList.forEach(row -> {
            String countryName = row.getText();
            if (!countryName.isEmpty()){
                hrefCountryWithZones.add(row.getAttribute("href"));
            }
        });
        return hrefCountryWithZones;
    }

    private List<String> fillZone(String url) {
        driver.get(url);
        List<String> zoneNameList = new ArrayList<>();
        List<WebElement> zoneList = driver.findElements(By.cssSelector("select[name$='[zone_code]']"));
        zoneList.forEach(zone -> {
            if(zone.isEnabled()){
                List<WebElement> optionsList = zone.findElements(By.tagName("option"));
                optionsList.forEach(option-> {
                    if(option.isSelected()){
                        zoneNameList.add(option.getText());
                        System.out.println(option.getText());
                    }
                });
            }
        });
        return zoneNameList;
    }

    private void checkZone(List<String> hrefCountryWithZones) {
        hrefCountryWithZones.forEach(href -> {
            driver.get(href);
            List<WebElement> zonesList = driver.findElements(By.cssSelector("[name$='[name]']"));
            List<String> zonesNameList = new ArrayList<>();
            final int[] j = {1};
            zonesList.forEach(row -> {
                String zone = row.getAttribute("Value");
                if(!zone.isEmpty()){
                    System.out.println("zone :" + zone);
                    zonesNameList.add(zone);
                    System.out.println(j[0] + " : " + zone);
                    j[0]++;
                }
            } );
            List<String> zoneNameSortList = zonesNameList.stream().sorted().collect(Collectors.toList());
            Assert.assertEquals(zoneNameSortList, zonesNameList);
        });
    }

    private void addZone(WebElement row, List<String> hrefCountryWithZones ) {
        String href = row.getAttribute("href");
        WebElement parentCountry = row.findElement(By.xpath("./.."));
        WebElement Zones = parentCountry.findElement(By.xpath("./following-sibling::*"));
        int countZon = Integer.parseInt(Zones.getAttribute("textContent"));

        if (countZon > 0){
            hrefCountryWithZones.add(href);
            System.out.println(href + " : " + countZon);
        }
    }
}
