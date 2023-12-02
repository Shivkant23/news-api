package com.example.shivaknt.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.shivaknt.models.NewsBean;
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
	
//	
//	@GetMapping(path = "/news-fetch-search-api")
//	public NewsBean fetchNewsFromNewsApi(@RequestParam String search) throws IOException {
//		return newsService.getNews("", "", search);
//	}
	
	@GetMapping(path = "/news-fetch")
	public ResponseEntity<NewsBean> fetchNewsFromFireStore(@RequestParam String country, @RequestParam String category) throws IOException, InterruptedException, ExecutionException {
		return newsService.fetchNewsFromFireStore(country, category);
	}
	
	@GetMapping(path = "/news-fetch-search")
	public ResponseEntity<NewsBean> fetchNewsFromFireStore(@RequestParam String search) throws IOException, InterruptedException, ExecutionException {
		return newsService.fetchNewsFromFireStore(search);
	}
	
	@GetMapping(path = "/collections")
	public ResponseEntity<List<String>> getAllCollectionsNames(){
		return newsService.getAllCollectionsNames();
	}
}

//http://localhost:8080/news-fetch-api?country=in&categoty=business
//http://localhost:8080/news-fetch-search-api?search=bitcoin
//http://localhost:8080/news-fetch?country=in&categoty=health
//http://localhost:8080/news-fetch-search?search=pk