package com.upwork.tests;

import com.upwork.pages.GoogleSearchPage;
import com.upwork.utilities.GoogleUtils;
import com.upwork.utilities.ConfigurationReader;
import com.upwork.utilities.Driver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;


import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class GoogleSearchFunctionality {

    public static void main(String[] args) {
        GoogleSearchPage googleSearchPage = new GoogleSearchPage();
        ConfigurationReader.setBrowser("chrome");
        ConfigurationReader.setSearchEngine("google");
        String searchEngine = ConfigurationReader.getProperty("searchEngine");
        Driver.getDriver().get(searchEngine);
        Driver.getDriver().manage().deleteAllCookies();
        List<String> googleSearchItemList = GoogleUtils.createGoogleSearchItemList();
        Map<String, List<SearchResultClass>> allSearchResulList = new LinkedHashMap<>();

        for (String eachSearchItem : googleSearchItemList) {
            googleSearchPage.searchInput.sendKeys(eachSearchItem + Keys.ENTER);

            List<WebElement> urlList = googleSearchPage.url;
            List<WebElement> descriptionList = googleSearchPage.description;
            List<WebElement> titleList = googleSearchPage.title;

            String url = "", description = "", title = "";

            List<SearchResultClass> searchResultList = new ArrayList<>();
            int totalSearchResultNumber = 0;
            while (totalSearchResultNumber < 10) {
                List<String> checkNestedUrlList = new ArrayList<>();
                for (int i = 0; i < urlList.size() && totalSearchResultNumber < 10; i++) {
                    SearchResultClass searchResultObject = new SearchResultClass(url, description, title);
                    url = urlList.get(i).getAttribute("href");
                    String mainUrl=url.split("/")[2];//this will find main url
                    if (checkNestedUrlList.contains(mainUrl)){
                        break;
                    }
                    searchResultObject.setUrl(url);
                    checkNestedUrlList.add(mainUrl); //this will add each url to check for nested

                    description = descriptionList.get(i).getText();
                    searchResultObject.setDescription(description);

                    title = titleList.get(i).getText();
                    searchResultObject.setTitle(title);

                    searchResultList.add(searchResultObject);
                    totalSearchResultNumber++;
                }
                googleSearchPage.nextPage.click();


            }


            allSearchResulList.put(eachSearchItem, searchResultList);
            googleSearchPage.searchInput.clear();

        }
        System.out.println("allSearchResulList = " + allSearchResulList);


    }
}
