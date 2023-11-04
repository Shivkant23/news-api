package com.example.shivaknt.services;

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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.shivaknt.models.ArticlesBean;
import com.example.shivaknt.models.NewsBean;
import com.example.shivaknt.utils.CommonUtils;

@Service
public class NewsServiceImplementation implements NewsService{
	public static String newsApiKey;
	public static String[] listOfKeywords = new String[]{"in", "de", "us", "cn", "is", "rs", "jp", "ca", "business", "entertainment", "general", "health", "science", "sports", "technology"};
	
	private String getApiString(String countryCode, String category, String search){
		String apiLink = "";
		if(!category.isEmpty()) {
			apiLink = "https://newsapi.org/v2/top-headlines?country=us&category="+category+"&apiKey="+newsApiKey;
		}else if(!countryCode.isEmpty()) {
			apiLink = "https://newsapi.org/v2/top-headlines?country="+countryCode+"&category="+category+"&apiKey="+newsApiKey;
		}else if(!search.isEmpty()) {
			apiLink = "https://newsapi.org/v2/everything?q="+search+"&apiKey="+newsApiKey;
		}
		return apiLink;
	}
	
	public NewsBean getNews(String country, String category, String search, int reqId) throws IOException, InterruptedException, ExecutionException {
		System.out.println("News api is get called");
//		String url = "https://newsapi.org/v2/everything?q=bitcoin&apiKey=a04694ffe29145218220b70933537562";
//		String url2 = "https://newsapi.org/v2/top-headlines?country=de&category=business&apiKey=a04694ffe29145218220b70933537562";
		String url = getApiString(country, category, search);
		RestTemplate restTemplate = new RestTemplate();
		NewsBean result = restTemplate.getForObject(url, NewsBean.class);
		int timeCounter = 0, counter = 0;
		System.out.println("2News api is get called  :---  " + result.getArticles().size());
//		for(int i = 0; i<result.getArticles().size(); i++) {
//			try {
//				String urlOfWeb = result.getArticles().get(i).getUrl();
//				String strTitle = CommonUtils.getString(result.getArticles().get(i).getTitle());
//				String strDescription = CommonUtils.getString(result.getArticles().get(i).getDescription());
//				String strContent = CommonUtils.getString(result.getArticles().get(i).getContent());
//				String content = "";
//				
//				content = readWebUrl(urlOfWeb, strTitle, strDescription, strContent);
//				
//				String fixedContent = "";
//				String arr [] = content.split("content|meta|title|IE=edge|link|window|script|https|div|http|document|path|href=|&quot;|class=|data-");
//				ArrayList<String> list = new ArrayList<>();
//				for(int j = 0; j<arr.length; j++) {
//					if(arr[j].startsWith("=")) {
//						list.add(arr[j]);
//					}
//				}
//
//				fixedContent = String.join(" ", list);
//				if(!fixedContent.isEmpty()) {
////					counter++;
//					timeCounter++;
//					try {
//						fixedContent = fixedContent.length() < 13300 ? fixedContent : fixedContent.substring(0, 13300);
//						fixedContent = ChatGptService.chatGPT(fixedContent);
//						if(timeCounter == 3) {
//							TimeUnit.MINUTES.sleep(1);
//							TimeUnit.SECONDS.sleep(30);
//							timeCounter = 0;
//						}
//	
//					}catch(Exception e) {
//						System.out.println(e);
//					}
//				}
//				result.getArticles().get(i).setMyContent(fixedContent);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		FireBaseService service = new FireBaseService();
		String collectionName = !(category.isEmpty()) ? category : !(country.isEmpty()) ? country : search;
		service.pushDataOnDatabaseService(result.getArticles(), collectionName, reqId);
		return result; 
	}
	
//	public String getChat(String prompt) throws IOException {
//		System.out.println("Chat gpt api is calling");
//		String asd = "";
//		asd = ChatGptService.chatGPT(prompt);
//		System.out.println("Chat gpt api is calling - 2 :-= "+ asd);
//		return asd;
//	}
	
	public String readWebUrl(String urlOfWeb, String title, String desc, String content) throws IOException{
		String parseLine = "";
		StringBuffer sb2 = new StringBuffer();
		
		try {
			String str1 = title;
			String str2 = desc;
			String str3 = content;
			
            URL URL = new URL(urlOfWeb); 
            BufferedReader br = new BufferedReader(new InputStreamReader(URL.openStream()));
            
            while ((parseLine = br.readLine()) != null) {
            	if(parseLine.contains(str1)) {
            		sb2.append(parseLine+ " ");
            	}            	
            	if(parseLine.contains(str2)) {
            		sb2.append(parseLine+ " ");
            	}
            	if(parseLine.contains(str3)) {
            		sb2.append(parseLine+ " ");
            	}
            }
            br.close();
        } catch (MalformedURLException me) {
            System.out.println(me);
        }
		System.out.println();
		System.out.println("This is the String Object: " + sb2);
		return sb2.toString();
	}
	
	
	
	@Scheduled(fixedDelay = 240, initialDelay = 60, timeUnit = TimeUnit.MINUTES)
	public void scheduleTask() throws InterruptedException, ExecutionException, IOException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
		String strDate = dateFormat.format(new Date());
		System.out.println("Fixed Delay Scheduler: Task running at - "+ strDate);
		try {
			for(int i = 0; i<listOfKeywords.length; i++) {
				if(i < 8) {
					getNews(listOfKeywords[i], "", "", 1);
				}else {
					getNews("", listOfKeywords[i], "", 1);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		List<String> collections = (List<String>) getAllCollectionsNames();
		FireBaseService service = new FireBaseService();
		for(int i = 0; i<collections.size(); i++) {
			if(!Arrays.stream(listOfKeywords).anyMatch(collections.get(i)::equals)) {
				service.deletePreviousArticles(collections.get(i));
			}
		}
	}
	
	
	public ResponseEntity<NewsBean> fetchNewsFromFireStore(String search) throws IOException, InterruptedException, ExecutionException {
		FireBaseService service = new FireBaseService();
		List<ArticlesBean> list = service.getParamArticles(search);
		NewsBean newsBean = new NewsBean();
		if(list.isEmpty()) {
			newsBean = getNews("", "", search, 0);
			list = newsBean.getArticles();
		}
		if(!list.isEmpty()) {
			newsBean.setArticles(list);
			newsBean.setStatus("OK");
			newsBean.setTotalresults(list.size());
		}
		return new ResponseEntity<NewsBean>(newsBean, HttpStatus.OK);
	}
	
	public ResponseEntity<NewsBean> fetchNewsFromFireStore(String country, String category) throws IOException, InterruptedException, ExecutionException {
		FireBaseService service = new FireBaseService();
		String collectionName = !(category.isEmpty()) ? category  : country;
		List<ArticlesBean> list = service.getParamArticles(collectionName);
		NewsBean newsBean = new NewsBean();
		
		if(!list.isEmpty()) {
			newsBean = getNews(country, category, "", 0);
		}
		if(!list.isEmpty()) {
			newsBean.setArticles(list);
			newsBean.setStatus("OK");
			newsBean.setTotalresults(list.size());
		}
		return new ResponseEntity<NewsBean>(newsBean, HttpStatus.OK);
	}
	
	public ResponseEntity<List<String>> getAllCollectionsNames(){
		FireBaseService service = new FireBaseService();
		List<String> list = service.getAllCollectionsNames();
		return new ResponseEntity<List<String>>(list, HttpStatus.OK);
	}  

//	@Override
//	public NewsBean getNews(String country, String categoty, String search) throws IOException {
//		// TODO Auto-generated method stub
//		return null;
//	}
}