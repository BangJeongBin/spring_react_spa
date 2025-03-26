package com.example.semiprojectv2.service;

import com.example.semiprojectv2.domain.Member;
import com.example.semiprojectv2.domain.MemberDTO;

public interface MemberService {

    boolean newMember(MemberDTO member);

    Member loginMember(MemberDTO member);
}
