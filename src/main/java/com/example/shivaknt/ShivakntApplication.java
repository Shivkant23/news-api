package com.example.shivaknt;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@SpringBootApplication
@EnableScheduling
public class ShivakntApplication {

	public static void main(String[] args)  throws IOException{
		initialiseFirebase();
		SpringApplication.run(ShivakntApplication.class, args);
	}
	
	public static void initialiseFirebase() throws IOException{
		ClassLoader classLoader = ShivakntApplication.class.getClassLoader();
		System.out.println("FIrebase service initialising....");
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

           } catch (IOException e) {
               e.printStackTrace();
           }
	}

}
