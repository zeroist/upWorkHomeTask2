package com.upwork.tests;

public class SearchResultClass {

    private String url;
    private String title;
    private String description;


    public SearchResultClass(String url, String title, String description) {
      setUrl(url);
      setTitle(title);
      setDescription(description);
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
        return "SearchResultClass{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
