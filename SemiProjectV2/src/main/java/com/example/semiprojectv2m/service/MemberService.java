package com.example.semiprojectv2m.service;

import com.example.semiprojectv2m.domain.Member;
import com.example.semiprojectv2m.domain.MemberDTO;

public interface MemberService {

    boolean newMember(MemberDTO member);

    Member loginMember(MemberDTO member);
}
