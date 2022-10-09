package com.upwork.tests;

import com.upwork.pages.BingSearchPage;
import com.upwork.pages.GoogleSearchPage;
import com.upwork.utilities.GoogleUtils;
import com.upwork.utilities.ConfigurationReader;
import com.upwork.utilities.Driver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;


import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class GoogleSearchFunctionality {

    public static void main(String[] args) {

        List<String> searchItemList = GoogleUtils.createGoogleSearchItemList();
        ConfigurationReader.setBrowser("chrome");

        ConfigurationReader.setSearchEngine("google");
        String searchEngine = ConfigurationReader.getProperty("searchEngine");
        Driver.getDriver().get(searchEngine);
        Driver.getDriver().manage().deleteAllCookies();
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();

        Map<String, List<SearchResultClass>> allSearchResulListFromGoogle = new LinkedHashMap<>();

        for (String eachSearchItem : searchItemList) {
            GoogleSearchPage googleSearchPage = new GoogleSearchPage();
            if (eachSearchItem.isEmpty()) {
                continue;
            }
            googleSearchPage.searchInput.sendKeys(eachSearchItem + Keys.ENTER);
            js.executeScript("arguments[0].scrollIntoView(true);", googleSearchPage.nextPage);

            List<WebElement> urlList = googleSearchPage.url;
            List<WebElement> descriptionList = googleSearchPage.description;
            List<WebElement> titleList = googleSearchPage.title;

            String url = "", description = "", title = "";

            List<SearchResultClass> searchResultList = new ArrayList<>();
            int totalSearchResultNumber = 0;
            String previousUrl="";
            while (totalSearchResultNumber < 10) {


                for (int i = 0; i < descriptionList.size() && totalSearchResultNumber < 10; i++) {

                    SearchResultClass searchResultObject = new SearchResultClass(url, description, title);
                    url = urlList.get(i).getAttribute("href");
                    System.out.println("url = " + url);
                    String actualMainUrl = url.split("/")[2];//this will find main url
                    if (actualMainUrl.equals(previousUrl)) {
                        break;
                    }
                    previousUrl=actualMainUrl;

                    searchResultObject.setUrl(url);


                    description = descriptionList.get(i).getText();
                    searchResultObject.setDescription(description);

                    title = titleList.get(i).getText();
                    searchResultObject.setTitle(title);

                    searchResultList.add(searchResultObject);
                    totalSearchResultNumber++;
                }
                js.executeScript("arguments[0].scrollIntoView(true);", googleSearchPage.nextPage);
                googleSearchPage.nextPage.click();


            }


            allSearchResulListFromGoogle.put(eachSearchItem, searchResultList);
            googleSearchPage.searchInput.clear();

        }

        System.out.println("--------------------------------------------------------");

        ConfigurationReader.setSearchEngine("bing");
        searchEngine = ConfigurationReader.getProperty("searchEngine");
        Driver.getDriver().get(searchEngine);
        Driver.getDriver().manage().deleteAllCookies();

        Map<String, List<SearchResultClass>> allSearchResulListFromBing = new LinkedHashMap<>();

        for (String eachSearchItem : searchItemList) {
            BingSearchPage bingSearchPage = new BingSearchPage();
            if (eachSearchItem.isEmpty()) {
                continue;
            }
            bingSearchPage.searchInput.sendKeys(eachSearchItem + Keys.ENTER);
            js.executeScript("arguments[0].scrollIntoView(true);", bingSearchPage.nextPage);

            List<WebElement> urlList = bingSearchPage.url;
            List<WebElement> descriptionList = bingSearchPage.description;
            List<WebElement> titleList = bingSearchPage.title;

            String url = "", description = "", title = "";

            List<SearchResultClass> searchResultList = new ArrayList<>();
            int totalSearchResultCounter = 0;
            while (totalSearchResultCounter < 10) {

                for (int i = 0; i < descriptionList.size() && totalSearchResultCounter < 10; i++) {


                    SearchResultClass searchResultObject = new SearchResultClass(url, description, title);
                    url = urlList.get(i).getText();
                    searchResultObject.setUrl(url);


                    description = descriptionList.get(i).getText();
                    searchResultObject.setDescription(description);

                    title = titleList.get(i).getText();
                    searchResultObject.setTitle(title);

                    searchResultList.add(searchResultObject);
                    totalSearchResultCounter++;
                }
                js.executeScript("arguments[0].scrollIntoView(true);", bingSearchPage.nextPage);

                bingSearchPage.nextPage.click();


            }


            allSearchResulListFromBing.put(eachSearchItem, searchResultList);
            bingSearchPage.searchInput.clear();

        }
        allSearchResulListFromGoogle.forEach((x,y)->{
            for (SearchResultClass searchResultClass : y) {
                System.out.println("searchResultClass.getTitle() = " + searchResultClass.getTitle());
                System.out.println("searchResultClass.getUrl() = " + searchResultClass.getUrl());
                System.out.println("searchResultClass.getDescription() = " + searchResultClass.getDescription());
            }

        });

        allSearchResulListFromBing.forEach((x,y)->{
            for (SearchResultClass searchResultClass : y) {
                System.out.println("searchResultClass.getTitle() = " + searchResultClass.getTitle());
                System.out.println("searchResultClass.getUrl() = " + searchResultClass.getUrl());
                System.out.println("searchResultClass.getDescription() = " + searchResultClass.getDescription());
            }

        });


    }
}
