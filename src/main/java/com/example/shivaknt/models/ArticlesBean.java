package com.example.shivaknt.models;

import java.util.Date;

public class ArticlesBean {

    private SourceBean source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urltoimage;
    private Date publishedat;
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

    public void setUrltoimage(String urltoimage) {
         this.urltoimage = urltoimage;
     }
     public String getUrltoimage() {
         return urltoimage;
     }

    public void setPublishedat(Date publishedat) {
         this.publishedat = publishedat;
     }
     public Date getPublishedat() {
         return publishedat;
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