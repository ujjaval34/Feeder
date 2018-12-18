package com.feederfox.ujaval.politician.bean;

public class Articles_bean {

     int Article_ID ;
     int FK_Article_PID;
     String Article_Name;
    String Article_Title;
    String Article_Image;
    String Article_Description;
    String Article_Date;
    String Article_Month;

    public int getArticle_ID() {
        return Article_ID;
    }

    public void setArticle_ID(int article_ID) {
        Article_ID = article_ID;
    }

    public int getFK_Article_PID() {
        return FK_Article_PID;
    }

    public void setFK_Article_PID(int FK_Article_PID) {
        this.FK_Article_PID = FK_Article_PID;
    }

    public String getArticle_Name() {
        return Article_Name;
    }

    public void setArticle_Name(String article_Name) {
        Article_Name = article_Name;
    }

    public String getArticle_Title() {
        return Article_Title;
    }

    public void setArticle_Title(String article_Title) {
        Article_Title = article_Title;
    }

    public String getArticle_Image() {
        return Article_Image;
    }

    public void setArticle_Image(String article_Image) {
        Article_Image = article_Image;
    }

    public String getArticle_Description() {
        return Article_Description;
    }

    public void setArticle_Description(String article_Description) {
        Article_Description = article_Description;
    }

    public String getArticle_Date() {
        return Article_Date;
    }

    public void setArticle_Date(String article_Date) {
        Article_Date = article_Date;
    }

    public String getArticle_Month() {
        return Article_Month;
    }

    public void setArticle_Month(String article_Month) {
        Article_Month = article_Month;
    }
}
