package com.amit.alumniManagement.service;

import com.cloudinary.Cloudinary;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FileUploadService {

    private final Cloudinary cloudinary;

    private static final Set<String> ALLOWED_TYPES = Set.of(
            "image/jpeg",
            "image/png",
            "image/webp",
            "image/gif",
            "image/heic"
    );


    public String uploadFile(MultipartFile file) {
        if(file == null || file.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Image file is required"
            );
        }

        String contentType = file.getContentType();

        if (contentType == null || !ALLOWED_TYPES.contains(contentType)) {
            throw new ResponseStatusException(
                    HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                    "Unsupported image type"
            );
        }

        try{
            String publicId = UUID.randomUUID().toString();
            Map<String, Object> options = Map.of(
                    "folder", "alumni/profile-picture",
                    "public_id", publicId,
                    "resource_type", "image"
            );

            // it returns file from cloudinary
            Map<?, ?> result = cloudinary.uploader()
                    .upload(file.getBytes(), options);

            if(result.containsKey("secure_url")){
                return result.get("secure_url").toString();
            }else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred: URL not found in Cloudinary response.");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Image upload failed",
                    e
            );
        }
    }

    public boolean deleteFile(String imgUrl){
        try{
            String publicId = getPublicIdFromUrl(imgUrl);
            if(publicId == null){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Public ID not found in Cloudinary response.");
            }

            Map deleteOptions = new HashMap<>();
            deleteOptions.put("resource_type", "image");
            Map result = cloudinary.uploader().destroy(publicId, deleteOptions);
            return "ok".equals(result.get("result"));

        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "An error occurred while deleting the file.", e);
        }
    }

    // helper method to get public id
    private String getPublicIdFromUrl(String imgUrl){
        try{
            int startIndex = imgUrl.indexOf("/upload/") + "/upload/".length();
            startIndex = imgUrl.indexOf('/', startIndex) +  1;
            int endIndex = imgUrl.lastIndexOf('.');

            if(startIndex > 0 && endIndex > startIndex){
                return imgUrl.substring(startIndex, endIndex);
            }
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while getting public ID from URL.");
        }
        return null;
    }
}
