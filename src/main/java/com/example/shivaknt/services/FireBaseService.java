package com.example.shivaknt.services;

import com.example.shivaknt.models.ArticlesBean;
import com.example.shivaknt.models.DataModel;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

@Service
public class FireBaseService {
	
//    FirebaseDatabase db;
//
//    public FireBaseService() throws IOException {
//    	System.out.println("FIrebase service initialising....");
//        File file = new File(
//                getClass().getClassLoader().getResource("serviceAccountKey .json").getFile());
//        
//        FileInputStream serviceAccount =
//        		new FileInputStream(file.getAbsolutePath());
//
//        		FirebaseOptions options = new FirebaseOptions.Builder()
//        		  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//        		  .build();
//
//        		FirebaseApp.initializeApp(options);
//
//        db = FirebaseDatabase.getInstance();
//    }
//
//    public FirebaseDatabase getDb() {
//        return db;
//    }
    
    public boolean pushDataOnDatabaseService(List<ArticlesBean> articlesList, String collection, int reqId) throws InterruptedException, ExecutionException {
    	Firestore dbFireStore = FirestoreClient.getFirestore();
    	if(reqId == 0) {
    		for(ArticlesBean item: articlesList) {
        		ApiFuture<DocumentReference> documentRef = dbFireStore.collection(collection).add(item);
            	System.out.println(documentRef);
        	}
    	}else {
    		deletePreviousArticles(collection);
    		for(ArticlesBean item: articlesList) {
        		ApiFuture<DocumentReference> documentRef2 = dbFireStore.collection(collection).add(item);
            	System.out.println(documentRef2);
        	}
    	}
    	return true;
    }
    
    public void deletePreviousArticles(String collection) throws InterruptedException, ExecutionException {
    	Firestore dbFireStore = FirestoreClient.getFirestore();
    	CollectionReference documentRef = dbFireStore.collection(collection);
    	ApiFuture<QuerySnapshot> snapShotFut = documentRef.get();
    	QuerySnapshot snapShot = snapShotFut.get();
    	List<QueryDocumentSnapshot> documents = snapShot.getDocuments();
    	for(QueryDocumentSnapshot document : documents) {
    	      document.getReference().delete();
	    }
    }
    
    public List<ArticlesBean> getParamArticles(String param) throws InterruptedException, ExecutionException{
    	Firestore dbFireStore = FirestoreClient.getFirestore();
    	CollectionReference documentRef = dbFireStore.collection(param);
    	ApiFuture<QuerySnapshot> snapShotFut = documentRef.get();
    	QuerySnapshot snapShot = snapShotFut.get();
    	List<QueryDocumentSnapshot> documents = snapShot.getDocuments();
		List<ArticlesBean> articles = new ArrayList<>();
		for(int i = 0; i<documents.size();i++) {
			 ArticlesBean articleItem =  documents.get(0).toObject(ArticlesBean.class);
			 articles.add(articleItem);
		}
    	return articles;
    }
    
    public List<String> getAllCollectionsNames(){
    	List<String> list =  new ArrayList<String>();
    	Firestore dbFireStore = FirestoreClient.getFirestore();
    	Iterable<CollectionReference> collectionRef = dbFireStore.listCollections();
    	collectionRef.forEach(item -> list.add(item.getId()));   	
    	return list;
    }
}