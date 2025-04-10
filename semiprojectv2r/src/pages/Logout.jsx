import React, {useEffect} from "react";

const Login = () => {

    const kakaoLogout = async () => {
        const response = await fetch('https://kapi.kakao.com/v1/user/logout', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8',
                'Authorization': `Bearer ${localStorage.getItem('accessToken')}`,
            }
        });
        if (!response.ok) { throw new Error(`오류 발생 ${response.statusText}`); }
        const data = response.json();
        console.log(data); // 로그아웃 한 사용자의 아이디

        const logoutUrl = 'https://kauth.kakao.com/oauth/logout';
        const params = `?client_id=${import.meta.env.VITE_APP_KAKAO_API_KEY}&logout_redirect_uri=${import.meta.env.VITE_APP_KAKAO_LOGOUT_URI}`;

        window.location.href = logoutUrl + params;
    }

    useEffect(() => {
        const kakao = localStorage.getItem("kakao")

        if (kakao) { // 카카오 로그인 한 경우
            kakaoLogout();
            localStorage.removeItem('kakao');
        }
        //localStorage.clear();
        localStorage.removeItem('accessToken');
    }, []);


    return (
        <></>
    );
}

export default Login;
