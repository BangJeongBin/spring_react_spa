import React, {useEffect, useState} from "react";
import "../styles/board.css";

// BoardList 함수 컴포넌트 정의
const BoardList = () => {
    const [boardData, setBoardData] = useState({}); // fetch한 board 데이터 받아주기


    // react에서 side effect(부수작업)을 하기 위한 hook
    // side effect : 데이터 가져오기, DOM 조작, 로그
    useEffect(() => {
        fetch("http://localhost:8080/api/board/list")
        .then(res => res.json())
        .then(data => {
            console.log(data);
            setBoardData(data);
        })
        .catch(err => console.log('오류발생! ', err));
    }, []);

    const goBoardWrite = () => {
        location.href = '/board/write';
    };

    return (
        <main id="content">
            <h2>게시판</h2>

            <table className="table table-striped">
                <colgroup>
                    <col style={{width: "7%"}} />
                    <col className="truncate"/>
                    <col style={{width: "12%"}} />
                    <col style={{width: "12%"}} />
                    <col style={{width: "7%"}} />
                    <col style={{width: "7%"}} />
                </colgroup>

                <thead>
                <tr>
                    <td colSpan="3" className="text-start">
                        <div className="d-flex align-items-center gap-3">
                            <select className="form-select" style={{width: "120px"}}
                                    id="findtype" name="findtype" defaultValue="title">
                                <option value="title">제목</option>
                                <option value="userid">작성자</option>
                                <option value="contents">내용</option>
                            </select>
                            <input className="form-control" style={{width: "250px"}} id="findkey" name="findkey" />
                            <button className="btn btn-success" id="findbtn">
                                <i className="fa-solid fa-magnifying-glass" /> 검색
                            </button>
                        </div>
                    </td>
                    <td colSpan="3" className="text-end">
                        <button type="button" className="btn btn-primary" id="newbdbtn" onClick={goBoardWrite}>
                            <i className="fas fa-pen" /> 글쓰기
                        </button>
                    </td>
                </tr>
                <tr className="table-light">
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>작성일</th>
                    <th>조회</th>
                    <th>추천</th>
                </tr>
                </thead>

                <tbody>
                {
                    (!Array.isArray(boardData.bdlist) || boardData.bdlist.length === 0) ?
                        <tr><td colSpan="6">게시글이 없습니다.</td></tr>
                        :
                        (boardData.bdlist.map(bd => (
                            <tr key={bd.bno}>
                                <td>{bd.bno}</td>
                                <td><a href="/board/view?bno=${bd.bno}">{bd.title}</a></td>
                                <td>{bd.userid}</td>
                                <td>{bd.regdate.substring(0, 10)}</td>
                                <td>{bd.views}</td>
                                <td>{bd.thumbs}</td>
                            </tr>
                        )))
                }
                </tbody>

                <tfoot>
                <tr>
                    <td colSpan="6">
                        <ul className="pagination">
                            <li className="page-item">
                                <a href="#" className="page-link">이전</a>
                            </li>

                            <li><a href="#" className="page-link">1</a></li>
                            <li><a href="#" className="page-link">2</a></li>
                            <li><a href="#" className="page-link">3</a></li>
                            <li><a href="#" className="page-link">4</a></li>
                            <li><a href="#" className="page-link">5</a></li>
                            <li><a href="#" className="page-link">6</a></li>
                            <li><a href="#" className="page-link">7</a></li>
                            <li><a href="#" className="page-link">8</a></li>
                            <li><a href="#" className="page-link">9</a></li>
                            <li><a href="#" className="page-link">10</a></li>

                            <li className="page-item">
                                <a href="#" className="page-link">다음</a>
                            </li>
                        </ul>
                    </td>
                </tr>
                </tfoot>
            </table>
        </main>
    )
}

export default BoardList;
