package com.CookingMama.dev.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.cloud.StorageClient;
@Service
public class fireBaseService {

    @Value("${app.firebase-bucket}")
    private String firebaseBucket;
    public String fireBaseImage(MultipartFile image, String imageName) throws IOException, FirebaseAuthException{
        Bucket bucket = StorageClient.getInstance().bucket(firebaseBucket);
        InputStream content = new ByteArrayInputStream(image.getBytes());
                bucket.create(imageName.toString(), content, image.getContentType());
        String url = imageName;
        return url;
    }
}
