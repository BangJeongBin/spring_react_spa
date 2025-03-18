import React from "react";
import "../styles/member.css";

// Join 함수 컴포넌트 정의
const Join = () => {
    return (
        <main id="content">
            <h2>회원가입</h2>
            <form name="joinfrm" id="joinfrm" method="post" noValidate>

                <div className="form-floating my-2">
                    <input type="text" name="userid" id="userid" className="form-control" placeholder="아이디"
                           required minLength="6" maxLength="18"/>
                    <label htmlFor="userid" className="form-label">아이디</label>
                </div>

                <div className="form-floating my-2">
                    <input type="password" name="passwd" id="passwd" className="form-control" placeholder="비밀번호"
                           required minLength="6" maxLength="18"/>
                    <label htmlFor="passwd" className="form-label">비밀번호</label>
                </div>

                <div className="form-floating my-2">
                    <input type="password" name="repasswd" id="repasswd" className="form-control" placeholder="비밀번호 확인"
                           required minLength="6" maxLength="18"/>
                    <label htmlFor="repasswd" className="form-label">비밀번호 확인</label>
                </div>

                <div className="form-floating my-2">
                    <input type="text" name="name" id="name" className="form-control" placeholder="이름" required/>
                    <label htmlFor="name" className="form-label">이름</label>
                </div>

                <div className="form-floating my-2">
                    <input type="email" name="email" id="email" className="form-control" placeholder="이메일" required/>
                    <label htmlFor="email" className="form-label">이메일</label>
                </div>

                <div className="my-2 d-flex justify-content-center">
                    <img src="/image/captcha.png"/>
                </div>

                <div className="my-2 d-flex justify-content-between">
                    <button type="submit" className="btn btn-primary"><i
                        className="fa-sharp fa-solid fa-file-signature"></i> 입력완료
                    </button>
                    <button type="reset" className="btn btn-danger"><i className="fa-sharp fa-solid fa-eraser"></i> 다시입력
                    </button>
                </div>

            </form>
        </main>
    )
}

export default Join;
