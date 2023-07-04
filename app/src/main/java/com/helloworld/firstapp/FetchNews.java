package com.helloworld.firstapp;

import java.util.ArrayList;

public class FetchNews {

    private String status;
    private String totalResuuls;
    private ArrayList<ModelClass> articles;

    public FetchNews(String status, String totalResuuls, ArrayList<ModelClass> articles) {
        this.status = status;
        this.totalResuuls = totalResuuls;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalResuuls() {
        return totalResuuls;
    }

    public void setTotalResuuls(String totalResuuls) {
        this.totalResuuls = totalResuuls;
    }

    public ArrayList<ModelClass> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<ModelClass> articles) {
        this.articles = articles;
    }
}
