//do not touch this class.
// if you want to configure the Search item or browser go to configuration.properties in the root.
package com.upwork.tests;

import com.upwork.pages.BingSearchPage;
import com.upwork.pages.GoogleSearchPage;
import com.upwork.utilities.ConfigurationReader;
import com.upwork.utilities.Driver;
import com.upwork.utilities.SearchUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


import java.util.*;

import static org.openqa.selenium.support.locators.RelativeLocator.with;


public class SearchFunctionality {

    public static void main(String[] args) {
        String searchText = ConfigurationReader.getProperty("searchKeyword");
        ConfigurationReader.setBrowser(ConfigurationReader.getProperty("browser"));
        ConfigurationReader.setSearchEngine("google");

        String searchEngine = ConfigurationReader.getProperty("searchEngine");
        Driver.getDriver().get(searchEngine);
        Driver.getDriver().manage().deleteAllCookies();
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        Actions actions = new Actions(Driver.getDriver());

        /*
          allSearchResulList is map which will store keyword and SearchResultClass object
         */
        Map<String, List<SearchResultClass>> allSearchResulList = new LinkedHashMap<>();

        GoogleSearchPage googleSearchPage = new GoogleSearchPage();//page object model object
        searchText = searchText.toLowerCase();
        googleSearchPage.searchInput.sendKeys(searchText + Keys.ENTER);

// I create list of Webelements which is needed to create SearchResultClass
        List<WebElement> urlList = googleSearchPage.url;
        List<WebElement> descriptionList = googleSearchPage.description;
        List<WebElement> titleList = googleSearchPage.title;

        String url, description, title;
//to collect all SearchResult objects into a list, I create a List
        List<SearchResultClass> searchResultList = new ArrayList<>();

        int totalSearchResultNumber = 0;//since total search result has to be 10, I create totalSearchResultNumber to count
        String previousUrl = "";//this helps us to check if search result is a nested or not.

        System.out.println("////////////////////NOW I AM PREPARING SEARCH RESULT REPORT/////////////////////////");
        System.out.println("/////////////////////////////////FOR GOOGLE///////////////////////////////////////////");


        /*
          I create a while and for loop to make sure that I have 10 search result.if first page of result
          is not enough to have 10 result, it will go to next page and so on.
         */
        while (true) {

            /*
              Some search keyword may not result a search result, so I put a condition.
              for the condition I create method under the utilities/SearchUtils/doesSearchResultExist
             */
            if (!SearchUtils.doesSearchResultExist(googleSearchPage.resultStats.getText())) {
                System.out.println("There is no search result for \"" + searchText + "\" Please try a different search text");
                break;
            }
            js.executeScript("arguments[0].scrollIntoView(true);", googleSearchPage.nextPage);


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
            if (totalSearchResultNumber >= 10) {//this make sure that we have 10 results and now we can break the loop.
                break;
            }

            googleSearchPage.nextPage.click();


        }


        allSearchResulList.put(searchText, searchResultList);
        googleSearchPage.searchInput.clear();

        allSearchResulList.forEach((keyword, listOfSearchResult) -> {
            int numberofItem = 0;
            int totalCount = 0;
            for (SearchResultClass eachResult : listOfSearchResult) {
                int count = 0;

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

                System.out.print("Result Report for Search Item " + numberofItem + "/10 = ");
                if (count > 0) {

                    System.out.println("=> keyword \"" + keyword + "\" is found " + count + " times in the search item below\n" + eachResult);

                } else {

                    System.out.println("keyword \"" + keyword + "\" is NOT FOUND in following search item\n" + eachResult);

                }
                if (count > 0) {
                    totalCount++;
                }
            }
            System.out.println(keyword + " is found in " + totalCount + " search items out of 10 with GOOGLE");


        });

        System.out.println("////////////////////NOW I AM PREPARING SEARCH RESULT REPORT/////////////////////////");
        System.out.println("/////////////////////////////////FOR BING///////////////////////////////////////////");

        ConfigurationReader.setSearchEngine("bing");

        searchEngine = ConfigurationReader.getProperty("searchEngine");
        Driver.getDriver().get(searchEngine);
        Driver.getDriver().manage().deleteAllCookies();


        allSearchResulList = new LinkedHashMap<>();

        BingSearchPage bingSearchPage = new BingSearchPage();
        searchText = searchText.toLowerCase();
        bingSearchPage.searchInput.sendKeys(searchText + Keys.ENTER);
        actions.scrollByAmount(0, 10000);


        urlList = bingSearchPage.url;
        descriptionList = bingSearchPage.description;
        titleList = bingSearchPage.title;


        searchResultList = new ArrayList<>();
        totalSearchResultNumber = 0;


        while (true) {
            actions.scrollByAmount(0, 10000);
            if (SearchUtils.doesSearchResultExist(bingSearchPage.resultStats.getText())) {
                System.out.println("There is no search result for \"" + searchText + "\" Please try a different search text");
                break;
            }

            for (int i = 0; i < descriptionList.size() && totalSearchResultNumber < 10; i++) {


                description = descriptionList.get(i).getText().toLowerCase();
                title = titleList.get(i).getText().toLowerCase();
                url = urlList.get(i).getAttribute("href").toLowerCase();

                if (url.startsWith("https://www.bing.com/ck/a?")) {
                    url = Driver.getDriver().findElement(with(By.tagName("cite")).below(titleList.get(i))).getText();

                }

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
            int numberofItem = 0;
            int totalCount = 0;
            for (SearchResultClass eachResult : listOfSearchResult) {
                int count = 0;

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
                System.out.print("Result Report for Search Item " + numberofItem + "/10 = ");
                if (count > 0) {

                    System.out.println("=> keyword \"" + keyword + "\" is found " + count + " times in the search item below\n" + eachResult);

                } else {

                    System.out.println("keyword \"" + keyword + "\" is NOT FOUND in following search item\n" + eachResult);

                }
                if (count > 0) {
                    totalCount++;
                }
            }
            System.out.println(keyword + " is found in " + totalCount + " search items out of 10 with BING");


        });


    }
}



