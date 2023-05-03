package com.example.demo;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/IMG/player/{team}/{fileName}.jpg")
    public ResponseEntity<Resource> serveImage(
            @PathVariable String team,
            @PathVariable String fileName) {
        String imageName = team + "/" + fileName + ".jpg";
        Resource imageResource = imageService.getImageAsResource(imageName);
        MediaType mediaType = getMediaTypeForFileName(imageName);
        return ResponseEntity.ok().contentType(mediaType).body(imageResource);
    }

    
    private MediaType getMediaTypeForFileName(String fileName) {
        String fileExtension = FilenameUtils.getExtension(fileName).toLowerCase();

        switch (fileExtension) {
            case "jpg":
            case "jpeg":
                return MediaType.IMAGE_JPEG;
            case "png":
                return MediaType.IMAGE_PNG;
            default:
                return MediaType.APPLICATION_OCTET_STREAM;
        }
        
    }
}
