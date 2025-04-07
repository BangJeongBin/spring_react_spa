package com.example.semiprojectv2.service;

import com.example.semiprojectv2.domain.Gallery;
import com.example.semiprojectv2.domain.Pds;
import com.example.semiprojectv2.domain.PdsReply;
import com.example.semiprojectv2.domain.PdsReplyDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PdsService {

//    List<GalleryListDTO> selectGallery();

//    GalleryImageDTO readOneGalleryImage(int gno);

    boolean newPds(Pds pds, List<MultipartFile> panames);

    PdsReplyDTO readOnePdsReply(int pno);
}
