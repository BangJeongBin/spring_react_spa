import React from "react";

const Login = () => {
    // localStorage.clear();

    if (localStorage.getItem("kakao")) { // 카카오 로그인 한 경우
        localStorage.removeItem('kakao');
    }
    localStorage.removeItem('accessToken');


    return (
        location.href = "/"
    );
}

export default Login;
