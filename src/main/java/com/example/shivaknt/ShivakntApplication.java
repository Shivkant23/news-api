package com.example.shivaknt;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.shivaknt.services.ChatGptService;
import com.example.shivaknt.services.NewsServiceImplementation;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException;
import com.google.firebase.remoteconfig.Parameter;
import com.google.firebase.remoteconfig.ParameterValue;
import com.google.firebase.remoteconfig.Template;

@SpringBootApplication
@EnableScheduling
public class ShivakntApplication {

	public static void main(String[] args)  throws IOException, FirebaseRemoteConfigException, InterruptedException, ExecutionException{
		Logger logger = LoggerFactory.getLogger(ShivakntApplication.class);
		logger.info("this the starting of the project.");
		initialiseFirebase();
		logger.info("firebase has been initialise.");
		SpringApplication.run(ShivakntApplication.class, args);
	}
	
	public static void initialiseFirebase() throws IOException, FirebaseRemoteConfigException, InterruptedException, ExecutionException{
		ClassLoader classLoader = ShivakntApplication.class.getClassLoader();
		String fileName = "serviceAccountKey.json";
//		URL url = ClassLoader.getSystemClassLoader().getResource(fileName);
////        File file = new File(Objects.requireNonNull(classLoader.getResource("serviceAccountKey.json").getFile()));
//        
//        FileInputStream serviceAccount = new FileInputStream(url.getFile());
//
//        FirebaseOptions options = new FirebaseOptions.Builder()
//        		  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//        		  .build();
//
//        FirebaseApp.initializeApp(options);
        
        
        try (InputStream inputStream = classLoader.getResourceAsStream(fileName)) {

        	FirebaseOptions options = new FirebaseOptions.Builder()
          		  .setCredentials(GoogleCredentials.fromStream(inputStream))
          		  .build();

	          FirebaseApp.initializeApp(options);
	          
	          FirebaseRemoteConfig mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
	          
	          Template template = mFirebaseRemoteConfig.getTemplateAsync().get();
	          ParameterValue newsParameterValue = template.getParameters().get("news_key").getDefaultValue();
	          String newsApiKey = ((ParameterValue.Explicit) newsParameterValue).getValue();
	          
	          ParameterValue chatParameterValue = template.getParameters().get("chat_key").getDefaultValue();
	          String chatApiKey = ((ParameterValue.Explicit) chatParameterValue).getValue();
	          
	          NewsServiceImplementation.newsApiKey = newsApiKey;
	          ChatGptService.chatApiKey = chatApiKey;
	          
	          System.out.println(NewsServiceImplementation.newsApiKey);
	          System.out.println(ChatGptService.chatApiKey);

           } catch (IOException e) {
               e.printStackTrace();
           }
	}

}
