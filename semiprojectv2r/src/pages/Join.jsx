import {useRef, useState} from "react";
import "../styles/member.css";

// 폼 재설정 함수 - 외부에서 사용
const resetForm = (formJoinRef, setErrors) => {
    formJoinRef.current.reset();
    setErrors({});
};

// Join 함수 컴포넌트 정의
const Join = () => {

    // 폼 요소 참조를 위한 ref 변수 생성
    const formJoinRef = useRef(null);

    // 오류상태를 위한 변수 선언
    // errors : 상태를 저장하기 위한 변수
    // setErrors : errors 변수의 상태를 변경하는 함수
    const [errors, setErrors] = useState({});


    // 폼 재설정 처리 - 외부에서 사용
    const handleReset = () => resetForm(formJoinRef, setErrors);


    // 폼 제출시 데이터 처리 및 유효성 검사
    const handleJoinSubmit = (e) => {
        e.preventDefault();

        // FormData API를 사용해서 폼 데이터 수집
        const formData = new FormData(formJoinRef.current);
        const formValues = Object.fromEntries(formData.entries());

        // 전체 폼 유효성 검사
        const formErrors = validateJoinForm(formValues);

        if (Object.keys(formErrors).length === 0) { // 유효성 검사 후 오류가 하나도 없다면
            console.log('입력한 회원 정보 : ', formValues);
        } else {    // 오류가 하나라도 존재하는 경우
            setErrors(formErrors);
            console.log('오류 정보 : ', formErrors);
        }
    };


    // 전체 폼 유효성 검사 함수
    const validateJoinForm = (values) => {
        let formErrors = {};

        // 아이디 검사
        if (!values.userid) {
            formErrors.userid = "아이디를 입력하세요!!";
        } else if (values.userid.length < 6) {
            formErrors.userid = "아이디는 6자 이상이어야 합니다.";
        }

        // 비밀번호 검사
        if (!values.passwd) {
            formErrors.passwd = "비밀번호를 입력하세요!!";
        } else if (values.passwd.length < 6) {
            formErrors.passwd = "비밀번호는 6자 이상이어야 합니다.";
        }

        // 비밀번호 확인 검사
        if (!values.repasswd) {
            formErrors.repasswd = "비밀번호확인을 입력하세요!!";
        } else if (values.passwd !== values.repasswd) {
            formErrors.repasswd = "비밀번호가 일치하지 않습니다.";
        }

        // 이름 검사
        if (!values.name) {
            formErrors.name = "이름을 입력하세요!!";
        }
        
        // 이메일 검사
        if (!values.email) {
            formErrors.email = "이메일을 입력하세요!!";
        } else if (!/\S+@\S+\.\S+/.test(values.email)) {
            formErrors.email = "유효한 이메일 주소를 입력하세요.";
        }

        return formErrors;
    };


    return (
        <main id="content">
            <h2>회원가입</h2>
            <form name="joinfrm" id="joinfrm" method="post"
                  ref={formJoinRef} onSubmit={handleJoinSubmit} noValidate>

                <div className="form-floating my-2">
                    <input type="text" name="userid" id="userid" className={`form-control ${errors.userid ? 'is-invalid' : ''}`} placeholder="아이디"
                           required minLength="6" maxLength="18"/>
                    <label htmlFor="userid" className="form-label">아이디</label>
                    {errors.userid && <div className="invalid-feedback">{errors.userid}</div>}
                </div>

                <div className="form-floating my-2">
                    <input type="password" name="passwd" id="passwd" className={`form-control ${errors.passwd ? 'is-invalid' : ''}`} placeholder="비밀번호"
                           required minLength="6" maxLength="18"/>
                    <label htmlFor="passwd" className="form-label">비밀번호</label>
                    {errors.passwd && <div className="invalid-feedback">{errors.passwd}</div>}
                </div>

                <div className="form-floating my-2">
                    <input type="password" name="repasswd" id="repasswd" className={`form-control ${errors.repasswd ? 'is-invalid' : ''}`} placeholder="비밀번호 확인"
                           required minLength="6" maxLength="18"/>
                    <label htmlFor="repasswd" className="form-label">비밀번호 확인</label>
                    {errors.repasswd && <div className="invalid-feedback">{errors.repasswd}</div>}
                </div>

                <div className="form-floating my-2">
                    <input type="text" name="name" id="name" className={`form-control ${errors.name ? 'is-invalid' : ''}`} placeholder="이름" required/>
                    <label htmlFor="name" className="form-label">이름</label>
                    {errors.name && <div className="invalid-feedback">{errors.name}</div>}
                </div>

                <div className="form-floating my-2">
                    <input type="email" name="email" id="email" className={`form-control ${errors.email ? 'is-invalid' : ''}`} placeholder="이메일" required/>
                    <label htmlFor="email" className="form-label">이메일</label>
                    {errors.email && <div className="invalid-feedback">{errors.email}</div>}
                </div>

                <div className="my-2 d-flex justify-content-center">
                    <img src="/image/captcha.png"/>
                </div>

                <div className="my-2 d-flex justify-content-between">
                    <button type="submit" className="btn btn-primary"><i
                        className="fa-sharp fa-solid fa-file-signature"></i> 입력완료
                    </button>
                    <button type="button" className="btn btn-danger" onClick={handleReset}>
                        <i className="fa-sharp fa-solid fa-eraser"></i> 다시입력
                    </button>
                </div>

            </form>
        </main>
    )
}

export default Join;
