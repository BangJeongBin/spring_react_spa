import React from "react";
import "../styles/board.css";

// BoardWrite 함수 컴포넌트 정의
const BoardWrite = () => {
    return (
        <main id="content">
            <h2>게시판 글쓰기</h2>

            <form name="boardfrm" id="boardfrm" method="post" noValidate>

                <div className="form-floating my-2">
                    <input type="text" name="userid" id="userid" className="form-control" placeholder="아이디"
                           required readOnly value="abc123"/>
                    <label htmlFor="userid" className="form-label">아이디</label>
                </div>

                <div className="form-floating my-2">
                    <input type="text" name="title" id="title" className="form-control" placeholder="제목" required/>
                    <label htmlFor="title" className="form-label">제목</label>
                </div>

                <div className="form-floating my-2">
                    <textarea name="contents" id="contents" className="form-control h-100" rows="10" placeholder="본문글"
                              required></textarea>
                    <label htmlFor="contents" className="form-label">본문글</label>
                </div>

                <div className="my-2 d-flex justify-content-center">
                    <div className="g-recaptcha" id="recaptcha" data-sitekey=""></div>
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

export default BoardWrite;
