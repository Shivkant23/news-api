package com.example.shivaknt.services;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.shivaknt.models.ChatGptResponseModel;
import com.google.gson.Gson;


public class ChatGptService {

   public static String chatGPT(String prompt) throws IOException {
       String url = "https://api.openai.com/v1/chat/completions";
       String apiKey = "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";
       String model = "gpt-3.5-turbo";

       RestTemplate template = new RestTemplate();

	   HttpHeaders headers = new HttpHeaders();
	   headers.add("Content-Type", "application/json");
	   headers.setBearerAuth(apiKey);
	   Map<String, Object> map = new HashMap<>();
	   map.put("model", model);
	   List<Map<String, String>> list = new ArrayList<>();
	   Map<String, String> map2 = new HashMap<>();
	   map2.put("role", "user");
	   map2.put("content", prompt);
	   list.add(map2);
	   map.put("messages", list);
	   HttpEntity<Map<String, Object>> requestEntity2 = new HttpEntity<>(map, headers);
	   ResponseEntity<ChatGptResponseModel> response = template.postForEntity(url, requestEntity2, ChatGptResponseModel.class);
	   ChatGptResponseModel modelBody =  response.getBody();
	   System.out.println(" modelBody:- "+ modelBody);
	   return modelBody.getChoices().get(0).getMessage().getContent();
   }
}