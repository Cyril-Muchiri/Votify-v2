package com.votifysoft.app.utils;
   
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class CloudinaryService {

    Dotenv dotenv=Dotenv.configure().load();

    public void uploadImage() {
        // Retrieve Cloudinary credentials from environment variables
        String cloudName = dotenv.get("CLOUDINARY_CLOUD_NAME");
        String apiKey = dotenv.get("CLOUDINARY_API_KEY");
        String apiSecret = dotenv.get("CLOUDINARY_API_SECRET");

        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret
        ));

        try {
              // Image path is a local file path
                File imageFile = new File("");

                // Upload the local image to Cloudinary
                Map<String, Object> uploadResult = cloudinary.uploader().upload(imageFile, ObjectUtils.emptyMap());

                // Get the image URL from the Cloudinary response
                String cloudinaryImageUrl = (String) uploadResult.get("secure_url");

                // Save the cloudinaryImageUrl to your database or use it as needed
                System.out.println("Image uploaded successfully. URL: " + cloudinaryImageUrl);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void downloadImage(){

    }
}

    

