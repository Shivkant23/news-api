package com.example.shivaknt.services;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.example.shivaknt.models.ArticlesBean;
import com.example.shivaknt.models.NewsBean;


public interface NewsService {
	NewsBean getNews(String country, String categoty, String search) throws IOException;
	
	List<ArticlesBean> fetchNewsFromFireStore(String search) throws IOException, InterruptedException, ExecutionException;
	
	List<ArticlesBean> fetchNewsFromFireStore(String country, String categoty) throws IOException, InterruptedException, ExecutionException;
	
	List<String> getAllCollectionsNames();
}
