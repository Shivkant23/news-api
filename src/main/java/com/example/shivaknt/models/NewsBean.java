package com.example.shivaknt.models;

import java.util.List;

public class NewsBean {

    private String status;
    private int totalResults;
    private List<ArticlesBean> articles;
    public void setStatus(String status) {
         this.status = status;
     }
     public String getStatus() {
         return status;
     }

   

    public int getTotalResults() {
		return totalResults;
	}
	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}
	public void setArticles(List<ArticlesBean> articles) {
         this.articles = articles;
     }
     public List<ArticlesBean> getArticles() {
         return articles;
     }

}