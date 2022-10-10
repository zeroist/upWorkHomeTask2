package com.upwork.tests;

import com.upwork.pages.GoogleSearchPage;
import com.upwork.utilities.ConfigurationReader;
import com.upwork.utilities.Driver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Unit {

    public static void main(String[] args) {
        String searchText = "yusuf olgun";
        ConfigurationReader.setBrowser("chrome");
        ConfigurationReader.setSearchEngine("google");

        String searchEngine = ConfigurationReader.getProperty("searchEngine");
        Driver.getDriver().get(searchEngine);
        Driver.getDriver().manage().deleteAllCookies();


        GoogleSearchPage googleSearchPage = new GoogleSearchPage();
        googleSearchPage.searchInput.sendKeys(searchText + Keys.ENTER);
        googleSearchPage.nextPage.click();

         searchText = "serdar olgun";
        ConfigurationReader.setBrowser("chrome");
        ConfigurationReader.setSearchEngine("bing");

         searchEngine = ConfigurationReader.getProperty("searchEngine");
        Driver.getDriver().get(searchEngine);
        Driver.getDriver().manage().deleteAllCookies();

        googleSearchPage.searchInput.sendKeys(searchText + Keys.ENTER);
        googleSearchPage.nextPage.click();


    }
}
