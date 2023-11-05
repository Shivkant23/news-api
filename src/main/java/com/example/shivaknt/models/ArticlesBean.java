package com.example.shivaknt.models;

import java.util.Date;

public class ArticlesBean {

    private SourceBean source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
	private Date publishedAt;
    private String content;
    private String myContent;
    public void setSource(SourceBean source) {
         this.source = source;
     }
     public SourceBean getSource() {
         return source;
     }

    public void setAuthor(String author) {
         this.author = author;
     }
     public String getAuthor() {
         return author;
     }

    public void setTitle(String title) {
         this.title = title;
     }
     public String getTitle() {
         return title;
     }
     public String getUrlToImage() {
 		return urlToImage;
 	}
 	public void setUrlToImage(String urlToImage) {
 		this.urlToImage = urlToImage;
 	}
 	public Date getPublishedAt() {
 		return publishedAt;
 	}
 	public void setPublishedAt(Date publishedAt) {
 		this.publishedAt = publishedAt;
 	}
    public void setDescription(String description) {
         this.description = description;
     }
     public String getDescription() {
         return description;
     }

    public void setUrl(String url) {
         this.url = url;
     }
     public String getUrl() {
         return url;
     }
    public void setContent(String content) {
         this.content = content;
    }
    public String getContent() {
         return content;
    } 
    public void setMyContent(String myContent) {
         this.myContent = myContent;
    }
    public String getMyContent() {
         return myContent;
    }

}