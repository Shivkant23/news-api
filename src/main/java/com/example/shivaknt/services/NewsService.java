package com.example.shivaknt.services;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.http.ResponseEntity;

import com.example.shivaknt.models.ArticlesBean;
import com.example.shivaknt.models.NewsBean;


public interface NewsService {
//	NewsBean getNews(String country, String categoty, String search) throws IOException;
	
	ResponseEntity<NewsBean> fetchNewsFromFireStore(String search) throws IOException, InterruptedException, ExecutionException;
	
	ResponseEntity<NewsBean> fetchNewsFromFireStore(String country, String categoty) throws IOException, InterruptedException, ExecutionException;
	
	ResponseEntity<List<String>> getAllCollectionsNames();
}
