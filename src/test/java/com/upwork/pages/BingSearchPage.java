package com.upwork.pages;

import com.upwork.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BingSearchPage {
    public BingSearchPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(name = "q")
    public WebElement searchInput;

    @FindBy(css = "[class=\"sb_count\"]")
    public WebElement resultStats;

    @FindBy(css = "[class=\"sb_pagN sb_pagN_bp b_widePag sb_bp \"]")
    public WebElement nextPage;

    @FindBy(xpath = "//li[@class=\"b_algo\"]//div[@class=\"b_title\"]/h2/a")
    public List<WebElement> title;

    @FindBy(xpath = "//li[@class=\"b_algo\"]//div[contains(@class,'b_caption')]//p[contains(@class,' b_lineclamp')]")
    public List<WebElement> description;

    @FindBy(xpath = "//li[@class=\"b_algo\"]//div[@class=\"b_title\"]/h2/a")
    public List<WebElement> url;

}
