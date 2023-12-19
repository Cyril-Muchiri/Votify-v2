package com.votifysoft.app.utils;

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.Map;

import java.io.File;
import java.io.IOException;


public class CloudinaryService {

    private static final Dotenv dotenv = Dotenv.configure().load();

    public static String uploadImage(File imageFile) {
        Cloudinary cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
        cloudinary.config.secure = true;
        System.out.println(cloudinary.config.cloudName);
        try {

            // Upload the image
            Map params1 = ObjectUtils.asMap(
                    "use_filename", true,
                    "unique_filename", false,
                    "overwrite", true);

            System.out.println("Take note of diiissss ==>>>>"+
                    cloudinary.uploader().upload(
                            imageFile, params1));
            

            
            return"image uploaded";

        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception, e.g., log or respond to the user
            return null;
        }
    }

    public static void downloadImage() {
        // Add implementation as needed
    }
}
