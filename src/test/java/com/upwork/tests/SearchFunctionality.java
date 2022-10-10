package com.upwork.tests;

import com.upwork.pages.GoogleSearchPage;
import com.upwork.utilities.ConfigurationReader;
import com.upwork.utilities.Driver;
import com.upwork.utilities.SearchUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;


import java.util.*;


public class SearchFunctionality {

    public static void main(String[] args) {
        String searchText = "serdar olgun";
        ConfigurationReader.setBrowser("chrome");
        ConfigurationReader.setSearchEngine("google");

        String searchEngine = ConfigurationReader.getProperty("searchEngine");
        Driver.getDriver().get(searchEngine);
        Driver.getDriver().manage().deleteAllCookies();
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();


        Map<String, List<SearchResultClass>> allSearchResulListFromGoogle = new LinkedHashMap<>();

        GoogleSearchPage googleSearchPage = new GoogleSearchPage();
        searchText = searchText.toLowerCase();
        googleSearchPage.searchInput.sendKeys(searchText + Keys.ENTER);


        List<WebElement> urlList = googleSearchPage.url;
        List<WebElement> descriptionList = googleSearchPage.description;
        List<WebElement> titleList = googleSearchPage.title;

        String url, description, title;

        List<SearchResultClass> searchResultList = new ArrayList<>();
        int totalSearchResultNumber = 0;
        String previousUrl = "";

        while (true) {
            if (SearchUtils.doesSearchResultExist(googleSearchPage.resultStats.getText())) {
                System.out.println("There is no search result for \"" + searchText + "\" Please try a different search text");
                System.exit(1);
            }

            for (int i = 0; i < descriptionList.size() && totalSearchResultNumber < 10; i++) {

                url = urlList.get(i).getAttribute("href").toLowerCase();
                description = descriptionList.get(i).getText().toLowerCase();
                title = titleList.get(i).getText().toLowerCase();

                SearchResultClass searchResultObject = new SearchResultClass(url, title, description);


                String actualMainUrl = url.split("/")[2];//this will find main url
                if (actualMainUrl.equals(previousUrl)) {
                    break;
                }
                previousUrl = actualMainUrl;

                searchResultList.add(searchResultObject);
                totalSearchResultNumber++;
            }
            if (totalSearchResultNumber >= 10) {
                break;
            }
            js.executeScript("arguments[0].scrollIntoView(true);", googleSearchPage.nextPage);
            googleSearchPage.nextPage.click();


        }


        allSearchResulListFromGoogle.put(searchText, searchResultList);
        googleSearchPage.searchInput.clear();

        allSearchResulListFromGoogle.forEach((keyword, listOfSearchResult) -> {
          //  Map<String, Boolean> doesSearchResultContainKeyword = new LinkedHashMap<>();
            int totalCount=0;
            for (SearchResultClass eachResult : listOfSearchResult) {
                int count = 0;
               // doesSearchResultContainKeyword = new LinkedHashMap<>();

                if (eachResult.getDescription().contains(keyword)) {
                    count++;
                }

                if (eachResult.getTitle().contains(keyword)) {
                    count++;
                }

                if (eachResult.getUrl().contains(keyword)) {
                    count++;
                }


                if (count > 0) {
                    System.out.println("count = " + count);
                 //   doesSearchResultContainKeyword.put(keyword, true);
                    System.out.println("keyword \"" + keyword + "\" is contained "+count+" times in the search item below \n " + eachResult);
                    System.out.println("---------------------------------------------------------------------------");

                } else {
                    System.out.println("count = " + count);
                  //  doesSearchResultContainKeyword.put(keyword, false);
                    System.out.println("keyword \"" + keyword + "\" is NOT CONTAINED in following search item " + eachResult);
                    System.out.println("---------------------------------------------------------------------------");

                }
                if (count>0){
                    totalCount++;
                }
            }
            System.out.println(keyword + " is contained in " + totalCount + " search items out of 10");

          //  doesSearchResultContainKeyword.forEach((x, y) -> {
           //     System.out.println(x + "-" + y);
          //  });


        });

//        System.out.println("--------------------------------------------------------");
//
//        ConfigurationReader.setSearchEngine("bing");
//        searchEngine = ConfigurationReader.getProperty("searchEngine");
//        Driver.getDriver().get(searchEngine);
//        Driver.getDriver().manage().deleteAllCookies();
//
//        Map<String, List<SearchResultClass>> allSearchResulListFromBing = new LinkedHashMap<>();
//
//        for (String eachSearchItem : searchItemList) {
//            BingSearchPage bingSearchPage = new BingSearchPage();
//            if (eachSearchItem.isEmpty()) {
//                continue;
//            }
//            bingSearchPage.searchInput.sendKeys(eachSearchItem + Keys.ENTER);
//            js.executeScript("arguments[0].scrollIntoView(true);", bingSearchPage.nextPage);
//
//            List<WebElement> urlList = bingSearchPage.url;
//            List<WebElement> descriptionList = bingSearchPage.description;
//            List<WebElement> titleList = bingSearchPage.title;
//
//            String url = "", description = "", title = "";
//
//            List<SearchResultClass> searchResultList = new ArrayList<>();
//            int totalSearchResultCounter = 0;
//            while (totalSearchResultCounter < 10) {
//
//                for (int i = 0; i < descriptionList.size() && totalSearchResultCounter < 10; i++) {
//
//
//                    SearchResultClass searchResultObject = new SearchResultClass(url, description, title);
//                    url = urlList.get(i).getText();
//                    searchResultObject.setUrl(url);
//
//
//                    description = descriptionList.get(i).getText();
//                    searchResultObject.setDescription(description);
//
//                    title = titleList.get(i).getText();
//                    searchResultObject.setTitle(title);
//
//                    searchResultList.add(searchResultObject);
//                    totalSearchResultCounter++;
//                }
//                js.executeScript("arguments[0].scrollIntoView(true);", bingSearchPage.nextPage);
//
//                bingSearchPage.nextPage.click();
//
//
//            }
//
//
//            allSearchResulListFromBing.put(eachSearchItem, searchResultList);
//            bingSearchPage.searchInput.clear();
//
//        }
//        allSearchResulListFromGoogle.forEach((x,y)->{
//            for (SearchResultClass searchResultClass : y) {
//                System.out.println("searchResultClass.getTitle() = " + searchResultClass.getTitle());
//                System.out.println("searchResultClass.getUrl() = " + searchResultClass.getUrl());
//                System.out.println("searchResultClass.getDescription() = " + searchResultClass.getDescription());
//            }
//
//        });
//
//        allSearchResulListFromBing.forEach((x,y)->{
//            for (SearchResultClass searchResultClass : y) {
//                System.out.println("searchResultClass.getTitle() = " + searchResultClass.getTitle());
//                System.out.println("searchResultClass.getUrl() = " + searchResultClass.getUrl());
//                System.out.println("searchResultClass.getDescription() = " + searchResultClass.getDescription());
//            }
//
//        });
//
//
    }
}
