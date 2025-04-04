import React, {useEffect, useRef, useState} from "react";
import "../styles/gallery.css";

// 폼 재설정 처리 함수


// 게시글 등록 처리 함수
const processWriteOk = async (formValues) => {

    fetch('http://localhost:8080/api/board/write', {
        method: 'post',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(formValues)
    }).then(async reponse => {
        if (reponse.ok) {
            alert('게시글이 등록되었습니다.');
            location.href='/board/list';
        } else if (reponse === 400) {
            alert(await reponse.text());
        } else {
            alert('게시글 등록에 실패했습니다.');
        }
    }).catch(error => {
        console.log('write error : ', error);
        alert('서버와 통신 실패!');
    });
};


// GalleryWrite 함수 컴포넌트 정의
const GalleryWrite = () => {

    // 폼 처리 관련 함수 선언
    const formWriteRef = useRef(null);
    const [errors, setErrors] = useState({});
    const [sitekey, setSitekey] = useState(null);


    const handleWriteSubmit = (e) => {
        e.preventDefault();

        // FormData API를 사용해서 폼 데이터 수집
        const formDate = new FormData(formWriteRef.current);
        const formValues = Object.fromEntries(formDate.entries());

        // 전체 폼 유효성 검사
        const formErrors = validateWriteForm(formValues);

        if (Object.keys(formErrors).length === 0) {
            console.log('입력한 글 정보 : ', formValues);
            // 게시글 등록 API 호출
            processWriteOk(formValues);
        } else {
            setErrors(formErrors);
            console.log('오류 정보 : ', formErrors);
        }
    };


    // recaptcha 모듈 적재
    useEffect(() => {
        const script = document.createElement('script');
        script.src = 'https://www.google.com/recaptcha/api.js';
        script.async = true;
        script.defer = true;
        document.body.appendChild(script);

        const site_key = import.meta.env.VITE_APP_RECAPTCHA_SITEKEY;
        setSitekey(site_key);
    }, []);


    const validateWriteForm = (values) => {
        let formErrors = {};

        // 아이디 검사

        // 제목 검사
        if (!values.title) {
            formErrors.title = "제목을 입력하세요.";
        }

        // 본문글 검사
        if (!values.contents) {
            formErrors.contents = "본문을 입력하세요.";
        }

        // recaptcha 검사
        if (!values["g-recaptcha-response"]) {
            formErrors.recaptcha = "자동가입방지를 확인하세요!!";
        }

        return formErrors;
    };

    return (
        <main id="content">
            <h2>갤러리 글쓰기</h2>

            <form name="galleryfrm" id="galleryfrm" method="post"
                  ref={formWriteRef} onSubmit={handleWriteSubmit} noValidate>

                <div className="form-floating my-2">
                    <input type="text" name="userid" id="userid"
                           className={`form-control ${errors.userid ? 'is-invalid' : ''}`}
                           placeholder="아이디" required readOnly value="abc123"/>
                    <label htmlFor="userid" className="form-label">아이디</label>
                    {errors.userid && <div className="invalid-feedback">{errors.userid}</div>}
                </div>

                <div className="form-floating my-2">
                    <input type="text" name="title" id="title"
                           className={`form-control ${errors.title ? 'is-invalid' : ''}`}
                           placeholder="제목" required/>
                    <label htmlFor="title" className="form-label">제목</label>
                    {errors.title && <div className="invalid-feedback">{errors.title}</div>}
                </div>

                <div className="form-floating my-2">
                    <textarea name="contents" id="contents"
                              className={`form-control h-100 ${errors.contents ? 'is-invalid' : ''}`}
                              rows="10" placeholder="본문글" required></textarea>
                    <label htmlFor="contents" className="form-label">본문글</label>
                    {errors.contents && <div className="invalid-feedback">{errors.contents}</div>}
                </div>

                <div className="my-2">
                    <input type="file" name="ginames" id="ginames"
                           className={`form-control h-100 ${errors.ginames ? 'is-invalid' : ''}`} multiple required/>
                    <input type="hidden" name="simgname" id="simgname"/>
                    {errors.contents && <div className="invalid-feedback">{errors.ginames}</div>}
                </div>

                <div className="my-2 d-flex justify-content-center">
                    <div className="g-recaptcha" id="recaptcha" data-sitekey={sitekey}></div>
                </div>
                {errors.recaptcha && <div className="alert alert-danger">{errors.recaptcha}</div>}

                <div className="my-2 d-flex justify-content-between">
                    <button type="submit" className="btn btn-primary">
                        <i className="fa-sharp fa-solid fa-file-signature"></i> 입력완료
                    </button>
                    <button type="reset" className="btn btn-danger"><i className="fa-sharp fa-solid fa-eraser"></i> 다시입력
                    </button>
                </div>

            </form>
        </main>
    )
}

export default GalleryWrite;
