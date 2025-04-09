package com.example.semiprojectv2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

// 교차출처 리소스 공유 CORS(Cross Origin Resource sharing)
@CrossOrigin(origins= {"http://localhost:5173", "http://localhost:3000"})
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/oauth/kakao")
public class KakaoController {

    @Value("${kakao.client.id}")
    private String clientId;

    @Value("${kakao.redirect.uri}")
    private String redirectUri;

    private RestTemplate restTemplate;
    private static String AccessToken = null;


    // 카카오 로그인 - 로그인 후 인가코드 받기
    @GetMapping("/login")
    public String kakaoLogin() {
        String authorizeUrl = "https://kauth.kakao.com/oauth/authorize";
        String params = "?client_id=%s&redirect_uri=%s&response_type=code";
        String kakaoUrl = String.format(authorizeUrl + params, clientId, redirectUri);

        return "redirect:" + kakaoUrl;
    }


    // 카카오 인증 후 redirect 엔드포인트 - 인가코드를 이용해서 엑세스토큰 받기
    @GetMapping("/callback")
    public ResponseEntity<?> kakaoCallback(@RequestParam String code) {
        // 1단계 : 인가 코드 출력(테스트 용)
        log.info("인가 코드: " + code);
        
        // 2단계 : 엑세스 토큰 요청

        return null;
    }

    // 카카오 로그아웃
}
