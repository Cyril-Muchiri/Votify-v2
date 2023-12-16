package com.votifysoft.app.utils;
   
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class CloudinaryUploader {



    public void uploadImage() {
        // Retrieve Cloudinary credentials from environment variables
        String cloudName = System.getenv("CLOUDINARY_CLOUD_NAME");
        String apiKey = System.getenv("CLOUDINARY_API_KEY");
        String apiSecret = System.getenv("CLOUDINARY_API_SECRET");

        if (cloudName == null || apiKey == null || apiSecret == null) {
            System.out.println("Cloudinary credentials are not set.");
            return;
        }

        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret
        ));

        try {
            // Either provide the local file path or a URL to the image
            String imagePathOrUrl = "https://example.com/path/to/your/image.jpg"; // or "/path/to/your/local/image.jpg"

            // Check if the provided path is a URL
            if (imagePathOrUrl.startsWith("http") || imagePathOrUrl.startsWith("https")) {
                // Image path is a URL
                URL imageUrl = new URL(imagePathOrUrl);

                // Upload the image from the URL to Cloudinary
                Map<String, Object> uploadResult = cloudinary.uploader().upload(imageUrl, ObjectUtils.emptyMap());

                // Get the image URL from the Cloudinary response
                String cloudinaryImageUrl = (String) uploadResult.get("secure_url");

                // Save the cloudinaryImageUrl to your database or use it as needed
                System.out.println("Image uploaded successfully. URL: " + cloudinaryImageUrl);
            } else {
                // Image path is a local file path
                File imageFile = new File(imagePathOrUrl);

                // Upload the local image to Cloudinary
                Map<String, Object> uploadResult = cloudinary.uploader().upload(imageFile, ObjectUtils.emptyMap());

                // Get the image URL from the Cloudinary response
                String cloudinaryImageUrl = (String) uploadResult.get("secure_url");

                // Save the cloudinaryImageUrl to your database or use it as needed
                System.out.println("Image uploaded successfully. URL: " + cloudinaryImageUrl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void downloadImage(){
        
    }
}

    

