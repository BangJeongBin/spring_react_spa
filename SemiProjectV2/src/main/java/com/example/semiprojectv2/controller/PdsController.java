package com.example.semiprojectv2.controller;

import com.example.semiprojectv2.domain.Pds;
import com.example.semiprojectv2.domain.PdsReplyDTO;
import com.example.semiprojectv2.service.PdsService;
import com.example.semiprojectv2.utils.GoogleRecaptchaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/api/pds")
@RequiredArgsConstructor
@CrossOrigin(origins= {"http://localhost:5173", "http://localhost:3000"})

public class PdsController {

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
}
