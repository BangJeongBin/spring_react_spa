import React from "react";

// Nav 함수 컴포넌트 정의
const Nav = () => {
    return (
        <nav role="navigation" className="navbar navbar-light bg-light">
            <ul className="nav space-between">
                <li className="nav-item"><a href="/" className="nav-link">Home</a></li>

                <li className="nav-item">
                    <a href="/member/join" className="nav-link">회원가입</a></li>

                <li className="nav-item">
                    <a href="/member/login" className="nav-link">로그인</a></li>

                <li className="nav-item"><a href="/board/list/1" className="nav-link">게시판</a></li>
                <li className="nav-item"><a href="/gallery/list/1" className="nav-link">갤러리</a></li>
                <li className="nav-item"><a href="/member/myinfo" className="nav-link">회원정보</a></li>
            </ul>
        </nav>
    )
}

export default Nav;
