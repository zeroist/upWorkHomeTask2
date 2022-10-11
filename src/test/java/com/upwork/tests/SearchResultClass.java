//I create a class to have objects of each Search result item which is consist of url,title and description
//I implement encapsulation oop concept.
package com.upwork.tests;

import java.util.List;

public class SearchResultClass {

    private String url;
    private String title;
    private String description;


    public SearchResultClass(String url, String title, String description) {
      setUrl(url);
      setTitle(title);
      setDescription(description);
    }
    public SearchResultClass(List<String> list) { //method overloading of constructor
        setUrl(list.get(0));
        setTitle(list.get(1));
        setDescription(list.get(2));
    }


    public String getUrl(){
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return  "|----------------------------------------------------------------------------------------------------------------------------------------\n" +
                "|url         ='" + url + '\'' +"\n"+
                "|title       ='" + title + '\'' +"\n"+
                "|description ='" + description + "'\n" +
                "|----------------------------------------------------------------------------------------------------------------------------------------\n";
    }
}
