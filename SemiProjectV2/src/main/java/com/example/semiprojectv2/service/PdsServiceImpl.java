package com.example.semiprojectv2.service;

import com.example.semiprojectv2.domain.*;
import com.example.semiprojectv2.repository.PdsAttachRepository;
import com.example.semiprojectv2.repository.PdsReplyRepository;
import com.example.semiprojectv2.repository.PdsRepository;
import com.example.semiprojectv2.utils.PdsUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PdsServiceImpl implements PdsService {

    private final PdsRepository pdsMapper;
    private final PdsAttachRepository pdsAttachMapper;
    private final PdsReplyRepository pdsReplyMapper;
    private final PdsUploadService pdsUploadService;


//    @Override
//    public List<GalleryListDTO> selectGallery() {
//        return galleryMapper.selectGallery();
//    }
//
//
//    @Transactional
//    @Override
//    public GalleryImageDTO readOneGalleryImage(int gno) {
//
//        galleryMapper.updateViewOne(gno);   // 조회수 증가
//        GalleryViewDTO gal = galleryMapper.selectOneGallery(gno);    // 본문글 가져오기
//        List<GalleryImage> gi = galleryMapper.selectGalleryImages(gno); // 본문글에 포함된 이미지들 가져오기
//
//        return new GalleryImageDTO(gal, gi);
//    }

    @Transactional
    @Override
    public boolean newPds(Pds pds, List<MultipartFile> panames) {
        boolean result = false;

        // 작성한 게시글을 pds에 저장하고, 생성된 글번호를 알아냄
        int pno = -999;

        try {
            // JPA를 이용해서 방금 입력한 데이터의 id 추출
            Pds newOne = pdsMapper.save(pds);
            pno = newOne.getPno();
        } catch (Exception ex) {
            throw new IllegalStateException("insertPds 오류 발생!!!");
        }

        // 첨부된 파일을 업로드 처리하고 가져온 글 번호로 첨부된 파일에 대한 정보를 pds_attach에 저장
        List<PdsAttach> pas = null;
        if (!panames.isEmpty()) {   // 첨부파일이 존재한다면
            try {
                // 업로드 처리 후 업로드된 파일들의 정보를 리스트 형태로 받아옴.
                pas = pdsUploadService.processUpload(panames, pno);
            } catch (Exception e) {
                throw new IllegalStateException("processUpload 오류 발생!!!");
            }

            // 업로드 된 파일의 정보를 pds_attach 테이블에 저장
            // 즉, 첨부 된 파일정보를 개별 행으로 저장
            try {
                // JPA를 이용해서 List 형식의 데이터를 한번에 테이블에 저장
                pdsAttachMapper.saveAll(pas);
            } catch (Exception e) {
                throw new IllegalStateException("insertPdsAttach 오류 발생!!!");
            }

            result = true;
        }
        return result;
    }


    @Transactional
    @Override
    public PdsReplyDTO readOnePdsReply(int pno) {
        //pdsMapper.updateView(pno); // 조회수 증가
        Pds pds = pdsMapper.findByPno(pno);
        List<PdsAttach> pas = pdsAttachMapper.findByPno(pno);
        List<PdsReply> prs = pdsReplyMapper.findByPno(pno);

        return new PdsReplyDTO(pds, pas, prs);
    }
}
