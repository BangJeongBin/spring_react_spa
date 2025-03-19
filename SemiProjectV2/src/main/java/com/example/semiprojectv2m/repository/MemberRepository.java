package com.example.semiprojectv2m.repository;

import com.example.semiprojectv2m.domain.MemberDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberRepository {

    @Insert("insert into members (userid, passwd, name, email) values (#{userid}, #{passwd}, #{name}, #{email})")
    int insertMember(MemberDTO member); // sql 명령어 + 도메인 이름을 조합하여 메서드 이름 생성이 보편적임

    @Select("select count(userid) from members where userid = #{userid}")
    int countByUserid(String userid);

    @Select("select count(userid) from members where email = #{eamil}")
    int countByEmail(String email);
}
