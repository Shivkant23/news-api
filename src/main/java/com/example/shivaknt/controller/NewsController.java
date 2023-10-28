package com.example.shivaknt.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.shivaknt.models.ArticlesBean;
import com.example.shivaknt.models.NewsBean;
import com.example.shivaknt.services.ChatGptService;
import com.example.shivaknt.services.FireBaseService;
import com.example.shivaknt.services.NewsService;


@RestController
public class NewsController {
	public NewsService newsService;
	
	public NewsController(NewsService service) {
		this.newsService = service;
	}

	@GetMapping(path = "/test-api")
	public ResponseEntity<String> testGetEndPoint(){ 
		return ResponseEntity.ok("Test get endpoint is working");
	}
	
//	@GetMapping(path = "/news-fetch-api")
//	public NewsBean fetchNewsFromNewsApi(@RequestParam String country, @RequestParam String categoty) throws IOException {
//		return newsService.getNews(country, categoty, "");
//	}
//	
//	@GetMapping(path = "/news-fetch-search-api")
//	public NewsBean fetchNewsFromNewsApi(@RequestParam String search) throws IOException {
//		return newsService.getNews("", "", search);
//	}
	
	@GetMapping(path = "/news-fetch")
	public List<ArticlesBean> fetchNewsFromFireStore(@RequestParam String country, @RequestParam String categoty) throws IOException, InterruptedException, ExecutionException {
		return newsService.fetchNewsFromFireStore(country, categoty);
	}
	
	@GetMapping(path = "/news-fetch-search")
	public List<ArticlesBean> fetchNewsFromFireStore(@RequestParam String search) throws IOException, InterruptedException, ExecutionException {
		return newsService.fetchNewsFromFireStore(search);
	}
	
	@GetMapping(path = "/collections")
	public List<String> getAllCollectionsNames(){
		return newsService.getAllCollectionsNames();
	}
}

//http://localhost:8080/news-fetch-api?country=in&categoty=business
//http://localhost:8080/news-fetch-search-api?search=bitcoin
//http://localhost:8080/news-fetch?country=in&categoty=health
//http://localhost:8080/news-fetch-search?search=pk