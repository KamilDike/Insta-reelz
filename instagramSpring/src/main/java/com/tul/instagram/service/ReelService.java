package com.tul.instagram.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.tul.instagram.model.Reel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

//CRUD operations
@Service
public class ReelService {

    public static final String COL_NAME="categories";

    public String saveReel(Reel reel, String category) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        int size = reel.getUrl().length();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME)
                .document(category).collection("reels")
                .document(reel.getUrl().substring(size - 20, size)).set(reel);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public void getReels() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference reels = dbFirestore.collection("reels");
        ApiFuture<QuerySnapshot> querySnapshotApiFuture = reels.get();
        List<QueryDocumentSnapshot> documents = querySnapshotApiFuture.get().getDocuments();
        documents.forEach(doc -> {
            System.out.println(doc.getData());
        });
    }
}
