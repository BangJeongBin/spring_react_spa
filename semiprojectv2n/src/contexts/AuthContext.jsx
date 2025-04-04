'use client'

import React, {createContext, useEffect, useState} from "react";

// createContext : 컴포넌트 전체에 데이터를 전달할 수 있는 컨택스트 API 생성에 사용
// 보통 전역상태, 인증정보, 기타 프로그램 내에서 공유하고 싶은 값을 생성할 때 주로 사용
// context : 전체 컴포넌트 구조에서 사용할 수 있는 자료구조 (비유 : 사물함)
export const AuthContext = createContext();

// children : 컴포넌트의 내부컨텐츠를 나타내는 특별한 속성(prop)
// 특정 컴포넌트를 사용할 때, 해당 컴포넌트 내부의 jsx를 children으로 전달 가능. 즉, 특정 변수를 다른 컴포넌트로 전달하고자 할 때 사용
export const AuthProvider = ({ children }) => {
    const[login, setLogin] = useState(false);

    useEffect(() => {
        const token = localStorage.getItem("accessToken");
        console.log(">> AuthContext ", token)
        if (token) setLogin(true);
        console.log(">> login ", login);

    }, [login]);

    return (
        <AuthContext.Provider value={{login}}>
            {children}
        </AuthContext.Provider>
    );
}