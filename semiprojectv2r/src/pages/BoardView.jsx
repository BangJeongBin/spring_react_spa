import React, {useEffect, useState, useRef} from "react";
import {Link, useParams} from "react-router-dom"; // @PathVariable 사용을 위한 import
import "../styles/board.css";
import BoardList from "./BoardList.jsx";

const BoardView = () => {
    const [boardData, setBoardData] = useState({}); // fetch한 board 데이터 받아주기
    const params = useParams();
    const bno = params.bno;

    const fetchURL = `http://localhost:8080/api/board/view/${bno}`;

    // react에서 side effect(부수작업)을 하기 위한 hook
    // side effect : 데이터 가져오기, DOM 조작, 로그
    useEffect(() => {
        fetch(fetchURL, {
            headers: {'Accept' : 'application/json'}
        }).then(res => res.json())
            .then(data => {
                console.log(data);
                setBoardData(data);
            })
            .catch(err => console.log('오류발생! ', err));
    }, []);

    return (
        <main id="content">
            <h2>게시판 본문 글</h2>

            <div className="row offset-1 col-10 my-3">
                <table className="table">
                    <thead>
                    <tr>
                        <td>
                            <button type="button" className="btn btn-light">이전게시물</button>
                            &nbsp;
                            <button type="button" className="btn btn-light">다음게시물</button>
                        </td>
                        <td className="text-end">
                            <button type="button" className="btn btn-primary" id="newbdbtn"
                                    sec:authorize="isAuthenticated()">새글쓰기
                            </button>
                        </td>
                    </tr>
                    </thead>

                    <tbody id="boardView">
                    </tbody>

                    <tfoot>
                    <tr>
                        <td>
                            <button type="button" className="btn btn-warning">수정하기</button>
                            &nbsp;
                            <button type="button" className="btn btn-danger" id="rmvbdbtn">삭제하기</button>
                        </td>
                        <td className="text-end">
                            <button type="button" className="btn btn-light" id="lstbdbtn">목록으로</button>
                        </td>
                    </tr>
                    </tfoot>
                </table>

                <div className="my-3">
                    <h3><i className="fa fa-commenting">나도 한마디!!</i></h3>
                </div>
            </div>
        </main>
);
}

export default BoardView;
