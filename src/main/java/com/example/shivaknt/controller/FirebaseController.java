//package com.example.shivaknt.controller;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.TimeZone;
//import java.util.concurrent.ExecutionException;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.shivaknt.models.ArticlesBean;
//import com.example.shivaknt.models.DataModel;
//import com.example.shivaknt.models.SourceBean;
//import com.example.shivaknt.services.FireBaseService;
//import com.google.api.client.util.DateTime;
//
//@RestController
//public class FirebaseController {
//	public FireBaseService fireBaseService;
//	
//	public FirebaseController(FireBaseService service) {
//		this.fireBaseService = service;
//	}
//	
////	@GetMapping(path = "/push-articles") //@RequestBody List<ArticlesBean> list
////	public boolean pushDataOnDatabase() throws InterruptedException, ExecutionException {
////		ArrayList<ArticlesBean> list = new ArrayList<ArticlesBean>();
////		ArticlesBean articlesBean = new ArticlesBean();
////		SourceBean sourceBean = new SourceBean();
////		sourceBean.setId("bbc-news");
////		sourceBean.setName("BBC News");
////		articlesBean.setSource(sourceBean);
////		articlesBean.setAuthor("https://www.facebook.com/bbcnews");
////		articlesBean.setTitle("Pakistan police bust organ trafficking ring that took kidneys from hundreds");
////		articlesBean.setContent("Eight members of an organ trafficking ring in north-east Pakistan have been arrested, police say. \r\nThe ring's alleged leader, Fawad Mukhtar, is accused of extracting the kidneys of more than 300 peo… [+1536 chars]");
////		articlesBean.setDescription("The alleged leader is accused of illegally harvesting kidneys from more than 300 people in Pakistan.");
////		articlesBean.setMyContent("Eight members of an organ trafficking ring in north-east Pakistan have been arrested, police say. \r\nThe ring's alleged leader, Fawad Mukhtar, is accused of extracting the kidneys of more than 300 peo… [+1536 chars]");
////		articlesBean.setPublishedat(null);
////		articlesBean.setUrl("https://www.bbc.co.uk/news/world-asia-66988933");
////		articlesBean.setUrltoimage("https://ichef.bbci.co.uk/news/1024/branded_news/9AC5/production/_131312693_gettyimages-1447339495.jpg");
////		list.add(articlesBean);
////		return fireBaseService.pushDataOnDatabaseService(list);
////	}
//	
////	@GetMapping(path = "/get-articles")
////	public DataModel getParamArticles(@RequestParam String param) throws InterruptedException, ExecutionException{
////		return fireBaseService.getParamArticles(param);
////	}
//	
//	@GetMapping(path = "/test")
//	public ResponseEntity<String> testGetEndPoint(){ return ResponseEntity.ok("Test get endpoint is working");}
//	
//}
