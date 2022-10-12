package com.upwork.pages;

import com.upwork.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class GoogleSearchPage {
    public GoogleSearchPage() {
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(name = "q")
    public WebElement searchInput;

    @FindBy(id = "result-stats")
    public WebElement resultStats;


    @FindBy(xpath = "//*[@id='pnnext']")
    public WebElement nextPage;


    @FindBy(xpath = "(//div[@class=\"MjjYud\"])//div[starts-with(@class,\"VwiC3b\")]")
    public List<WebElement> description;

    @FindBy(xpath = "(//div[@class=\"MjjYud\"])//div[starts-with(@class,\"VwiC3b\")]/../preceding-sibling::div[contains(@href,\"\")]//h3/..")
    public List<WebElement> url;

    @FindBy(xpath = "(//div[@class=\"MjjYud\"])//div[starts-with(@class,\"VwiC3b\")]/../preceding-sibling::div[contains(@href,\"\")]//h3")
    public List<WebElement> title;

}
