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

                for (int i = 0; i < urlList.size() && totalSearchResultNumber < 10; i++) {
                    SearchResultClass searchResultObject = new SearchResultClass(url, description, title);
                    url = urlList.get(i).getAttribute("href");
                    searchResultObject.setUrl(url);

                    description = descriptionList.get(i).getText();
                    searchResultObject.setDescription(description);

                    title = titleList.get(i).getText();
                    searchResultObject.setTitle(title);

                    searchResultList.add(searchResultObject);
                    totalSearchResultNumber++;
                }
                googleSearchPage.googleNextPage.click();


            }


            allSearchResulList.put(eachSearchItem, searchResultList);
            googleSearchPage.searchInput.clear();

        }
        System.out.println("allSearchResulList = " + allSearchResulList);


//        for (WebElement webElement : googleSearchPage.allSearchResultList) {
//            List<Map<String, String>> searchResultList = new ArrayList<>();
//            String[] split = webElement.getText().split("\\n");
//            for (String s : split) {
//                System.out.println("s = " + s);
//            }
//            for (int i = 0; i < split.length; i++) {
//                Map<String, String> searchResult = new LinkedHashMap<>();
//                searchResult.put("title",split[0]);
//                searchResult.put("url",split[1]);
//                searchResult.put("description",split[2]);
//                searchResultList.add(searchResult);
//            }
//            System.out.println("searchResultList = " + searchResultList);

//        }


//        for (String eachSearcItem : googleSearchItemList) {
//            Map<String,String>saachDetail=new LinkedHashMap<>();
//            googleSearchPage.searchInput.sendKeys(eachSearcItem + Keys.ENTER);
//            if(GoogleUtils.doesSearchResultExist(googleSearchPage.resultStats.getText())){
//
//            }
//
//
//        }

    }
}
