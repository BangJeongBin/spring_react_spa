package com.example.semiprojectv2.controller;

import com.example.semiprojectv2.domain.Pds;
import com.example.semiprojectv2.domain.PdsReplyDTO;
import com.example.semiprojectv2.service.PdsService;
import com.example.semiprojectv2.utils.GoogleRecaptchaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/api/pds")
@RequiredArgsConstructor
@CrossOrigin(origins= {"http://localhost:5173", "http://localhost:3000"})

public class PdsController {

    @Value("${savePdsDir}") private String savePdsDir;
    private final PdsService pdsService;
    private final GoogleRecaptchaService googleRecaptchaService;


//    @GetMapping("/list")
//    public String list(Model m) {
//        m.addAttribute("gals", galleryService.selectGallery());
//
//        return "views/gallery/list";
//    }


    /*Query String (질의문자열)
    URL의 ? 뒤에 key=value 형태로 데이터를 전달하는 방식
    ex) /users?name=John&age=30에서 name과 age가 Query String 매개변수
    스프링 부트에서는 @RequestParam 어노테이션을 사용하여 처리
    검색 조건, 필터링, 정렬 등 복잡한 데이터를 전달하기에 적합

    Path Variable (경로 변수)
    URL 경로 자체에 데이터를 포함시키는 방식
    ex) /users/John/30에서 John, 30이 Path Variable로 사용
    스프링 부트에서는 @PathVariable 어노테이션을 사용하여 처리
    RESTful API 설계에서 자원의 식별자로 사용하기에 적합*/

    // /pds/view/{글번호}
    @GetMapping("/view/{pno}")
    public ResponseEntity<?> view(@PathVariable int pno) {
        PdsReplyDTO pdsReply = pdsService.readOnePdsReply(pno);

        return new ResponseEntity<>(pdsReply, HttpStatus.OK);
    }


    @PostMapping("/write")
    public ResponseEntity<?> writeok(Pds pds, List<MultipartFile> panames,
                                     @RequestParam("g-recaptcha-response") String gRecaptchaResponse) {

        ResponseEntity<?> response = ResponseEntity.internalServerError().build();
        log.info("submit 된 자료실 정보1 : {}", pds);
        log.info("submit 된 자료실 정보2 : {}", panames);

        try {
            if (!googleRecaptchaService.verifyRecaptcha(gRecaptchaResponse)) {
                throw new IllegalStateException("자동가입방지 코드 오류");
            }

            if (pdsService.newPds(pds, panames)) {
                response = ResponseEntity.ok().body("파일 업로드 성공");
            }
        } catch (IllegalStateException ex) {
            response = ResponseEntity.badRequest().body(ex.getMessage());
        }
        return response;
    }


    @GetMapping("/down/{fname}")
    public ResponseEntity<?> down(@PathVariable String fname) {
        // 다운로드 할 실제 파일 경로를 알아냄
        File file = new File(savePdsDir + fname);

        if (!file.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("파일이 존재하지 않습니다.");
        }

        HttpHeaders headers = new HttpHeaders();
        // 다운로드 시 저장 할 파일명 지정
        headers.setContentDisposition(ContentDisposition.attachment().filename(fname).build());
        // 다운로드 시 다운로드 할 파일의 유형 지정 - 다운로드 대화상자가 무조건 뜨도록 OCTET_STREAM으로 설정
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return new ResponseEntity<>(new FileSystemResource(file), headers, HttpStatus.OK);
    }
}
