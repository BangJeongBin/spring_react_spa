package com.example.semiprojectv2m.controller;

import com.example.semiprojectv2m.domain.Member;
import com.example.semiprojectv2m.domain.MemberDTO;
import com.example.semiprojectv2m.domain.User;
import com.example.semiprojectv2m.service.MemberService;
import com.example.semiprojectv2m.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// 교차출처 리소스 공유 CORS(Cross Origin Resource sharing)
@CrossOrigin(origins="http://localhost:5173/")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final MemberService memberService;
    private final UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<?> joinok(@RequestBody User user) {
        ResponseEntity<?> response = ResponseEntity.internalServerError().build();

        log.info("submit된 회원정보 : {}", user);

        try {
            userService.newUser(user);
            response =  ResponseEntity.ok().build();
        } catch (IllegalStateException e) {
            response = ResponseEntity.badRequest().body(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }


    @PostMapping("/signin")
    public ResponseEntity<?> loginok(@RequestBody MemberDTO member) {
        ResponseEntity<?> response = ResponseEntity.internalServerError().build();

        log.info("submit된 로그인 정보 : {}", member);

        try {
            Member loginUser = memberService.loginMember(member);
            // 세션 처리


            response =  ResponseEntity.ok().build();
        } catch (IllegalStateException e) {
            response = ResponseEntity.badRequest().body(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }
}
