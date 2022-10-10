package com.upwork.tests;

import com.upwork.pages.BingSearchPage;
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


        Map<String, List<SearchResultClass>> allSearchResulList = new LinkedHashMap<>();

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


        allSearchResulList.put(searchText, searchResultList);
        googleSearchPage.searchInput.clear();

        allSearchResulList.forEach((keyword, listOfSearchResult) -> {
            //  Map<String, Boolean> doesSearchResultContainKeyword = new LinkedHashMap<>();
            int numberofItem=0;
            int totalCount = 0;
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
                numberofItem++;

                if (count > 0) {

                    System.out.print("Result Report for Search Item " + numberofItem + "/10 = ");
                    //   doesSearchResultContainKeyword.put(keyword, true);
                    System.out.println("=> keyword \"" + keyword + "\" is found " + count + " times in the search item below\n" + eachResult);

                } else {
                    //  doesSearchResultContainKeyword.put(keyword, false);
                    System.out.println("keyword \"" + keyword + "\" is NOT FOUND in following search item\n" + eachResult);

                }
                if (count > 0) {
                    totalCount++;
                }
            }
            System.out.println(keyword + " is found in " + totalCount + " search items out of 10");

            //  doesSearchResultContainKeyword.forEach((x, y) -> {
            //     System.out.println(x + "-" + y);
            //  });


        });

        System.out.println("////////////////////bing/////////////////////////");

        ConfigurationReader.setSearchEngine("bing");

        searchEngine = ConfigurationReader.getProperty("searchEngine");
        Driver.getDriver().get(searchEngine);
        Driver.getDriver().manage().deleteAllCookies();


        allSearchResulList = new LinkedHashMap<>();

        BingSearchPage bingSearchPage = new BingSearchPage();
        searchText = searchText.toLowerCase();
        bingSearchPage.searchInput.sendKeys(searchText + Keys.ENTER);


        urlList = bingSearchPage.url;
        descriptionList = bingSearchPage.description;
        titleList = bingSearchPage.title;


        searchResultList = new ArrayList<>();
        totalSearchResultNumber = 0;


        while (true) {
            if (SearchUtils.doesSearchResultExist(bingSearchPage.resultStats.getText())) {
                System.out.println("There is no search result for \"" + searchText + "\" Please try a different search text");
                System.exit(1);
            }

            for (int i = 0; i < descriptionList.size() && totalSearchResultNumber < 10; i++) {


                description = descriptionList.get(i).getText().toLowerCase();
                title = titleList.get(i).getText().toLowerCase();
                url = urlList.get(i).getAttribute("href").toLowerCase();

                SearchResultClass searchResultObject = new SearchResultClass(url, title, description);


                searchResultList.add(searchResultObject);
                totalSearchResultNumber++;
            }
            if (totalSearchResultNumber >= 10) {
                break;
            }
            js.executeScript("arguments[0].scrollIntoView(true);", bingSearchPage.nextPage);
            bingSearchPage.nextPage.click();


        }


        allSearchResulList.put(searchText, searchResultList);
        bingSearchPage.searchInput.clear();

        allSearchResulList.forEach((keyword, listOfSearchResult) -> {
            //  Map<String, Boolean> doesSearchResultContainKeyword = new LinkedHashMap<>();
            int numberofItem = 0;
            int totalCount = 0;
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

                numberofItem++;
                if (count > 0) {
                    System.out.print("Result Report for Search Item " + numberofItem + "/10 = ");
                    //   doesSearchResultContainKeyword.put(keyword, true);
                    System.out.println("=> keyword \"" + keyword + "\" is found " + count + " times in the search item below\n" + eachResult);

                } else {
                    //  doesSearchResultContainKeyword.put(keyword, false);
                    System.out.println("keyword \"" + keyword + "\" is NOT FOUND in following search item\n" + eachResult);

                }
                if (count > 0) {
                    totalCount++;
                }
            }
            System.out.println(keyword + " is found in " + totalCount + " search items out of 10");

            //  doesSearchResultContainKeyword.forEach((x, y) -> {
            //     System.out.println(x + "-" + y);
            //  });


        });


    }
}



