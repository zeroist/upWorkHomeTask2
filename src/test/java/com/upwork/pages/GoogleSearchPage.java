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

    @FindBy(id = "pnnext")
    public WebElement nextPage;


    @FindBy(xpath = "(//div[@class=\"MjjYud\"])//div[starts-with(@class,\"VwiC3b\")]")
    public List<WebElement> description;

    @FindBy(xpath = "(//div[@class=\"MjjYud\"])//div[starts-with(@class,\"VwiC3b\")]/../preceding-sibling::div[contains(@class,\"jGGQ5e\")]/div[@class=\"yuRUbf\"]/a")
    public List<WebElement> url;

    @FindBy(xpath = "(//div[@class=\"MjjYud\"])//div[starts-with(@class,\"VwiC3b\")]/../preceding-sibling::div[contains(@class,\"jGGQ5e\")]//h3")
    public List<WebElement> title;
    // //div[@class="MjjYud"]
    // //div[@class="MjjYud"]//h3
    // //div[@class="MjjYud"]//h3/..
    //div[@class="TbwUpd NJjxre"]//cite[@class="iUh30 qLRx3b tjvcx"]

    //div[@class="VwiC3b yXK7lf MUxGbd yDYNvb lyLwlc lEBKkf"]

// full 3 lü   (//div[@class="MjjYud"])//div[starts-with(@class,"VwiC3b")]/../preceding-sibling::div[contains(@class,"jGGQ5e")]
// başlık   (//div[@class="MjjYud"])//div[starts-with(@class,"VwiC3b")]/../preceding-sibling::div[contains(@class,"jGGQ5e")]//h3
//link    (//div[@class="MjjYud"])//div[starts-with(@class,"VwiC3b")]/../preceding-sibling::div[contains(@class,"jGGQ5e")]/div[@class="yuRUbf"]/a
// içerik   (//div[@class="MjjYud"])//div[starts-with(@class,"VwiC3b")]

    //div[starts-with(@class,'VwiC3b')]







}
