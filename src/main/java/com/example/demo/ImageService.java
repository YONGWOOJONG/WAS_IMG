package com.example.demo;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

@Service
public class ImageService {


//    public Resource getImageAsResource(String imageName) {
//        try {
//            Path imagePath = Paths.get(imageDirectory, imageName);
//            Resource resource = new UrlResource(imagePath.toUri());
//            if (resource.exists()) {
//                return resource;
//            } else {
//                throw new FileNotFoundException("Image not found: " + imageName);
//            }
//        } catch (MalformedURLException | FileNotFoundException e) {
//            throw new RuntimeException("Could not read image file: " + imageName, e);
//        }
//    }
	@Value("${image.directory}")
    private String imageDirectory;
	
	public Resource getImageAsResource(String imageName) {
	    try {
	        Path imagePath = Paths.get(imageDirectory).resolve(imageName).normalize();
	        Resource resource = new UrlResource(imagePath.toUri());
	        if (resource.exists()) {
	            return resource;
	        } else {
	            throw new FileNotFoundException("File not found: " + imageName);
	        }
	    } catch (MalformedURLException | FileNotFoundException ex) {
	        throw new RuntimeException("Could not retrieve the image: " + imageName, ex);
	    }
	}
}