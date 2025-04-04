package com.example.semiprojectv2.service;

import com.example.semiprojectv2.domain.Gallery;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GalleryService {

//    List<GalleryListDTO> selectGallery();

//    GalleryImageDTO readOneGalleryImage(int gno);

    boolean newGalleryImage(Gallery gal, List<MultipartFile> ginames);
}
