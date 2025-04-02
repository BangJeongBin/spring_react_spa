import React, {useEffect, useState} from "react";
import "../styles/member.css";

// Myinfo 함수 컴포넌트 정의
// 회원정보 출력 시 JWT 토큰을 인증 헤더에 포함시켜 http://localhost:8080/api/member/myinfo에 요청
const Myinfo = () => {

    const [userInfo, setUserInfo] = useState({});
    const fetchURL = 'http://localhost:8080/api/member/myinfo';


    useEffect(() => {
        const token = localStorage.getItem("accessToken");
        
        const headers = {}
        headers['Content-Type'] = `application/json`;
        headers['Accept'] = `application/json`;
        // 토큰이 존재하면 안증 헤더에 토큰을 설정하고  
        if (token != null) headers['Authorization'] = `Bearer ${token}`
        // 토큰이 존재하지 않으면 로그인 페이지로 이동
        else location.href = "/member/login";
        
        fetch(fetchURL, {
            headers: headers
        }).then(res => res.json())
            .then(data => {
                console.log(data);
                setUserInfo(data);
            })
            .catch(err => {
                console.log('오류발생! ', err);
                location.href = "/member/login";
            });
    }, []);

    return (
        <main id="content">
            <h2>회원정보</h2>
            <div id="myinfo">

                <table className="table table-bordered mb-0">
                    <colgroup>
                        <col style={{width: "30%"}}/>
                        <col/>
                    </colgroup>
                    <tbody>
                    <tr>
                        <td>아이디</td>
                        <td><span>{userInfo.loginUser}</span></td>
                    </tr>

                    <tr>
                        <td>이름</td>
                        <td><span>abc123</span></td>
                    </tr>

                    <tr>
                        <td>이메일</td>
                        <td><span>abc123</span></td>
                    </tr>

                    <tr>
                        <td>가입일</td>
                        <td><span>abc123</span>
                        </td>
                    </tr>
                    </tbody>
                </table>

            </div>
        </main>
    )
}

export default Myinfo;
