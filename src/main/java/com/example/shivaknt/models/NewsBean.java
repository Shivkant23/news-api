package com.example.shivaknt.models;

import java.util.List;

public class NewsBean {

    private String status;
    private int totalresults;
    private List<ArticlesBean> articles;
    public void setStatus(String status) {
         this.status = status;
     }
     public String getStatus() {
         return status;
     }

    public void setTotalresults(int totalresults) {
         this.totalresults = totalresults;
     }
     public int getTotalresults() {
         return totalresults;
     }

    public void setArticles(List<ArticlesBean> articles) {
         this.articles = articles;
     }
     public List<ArticlesBean> getArticles() {
         return articles;
     }

}