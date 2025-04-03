import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './styles/index.css'
import App from './App.jsx'
import {AuthProvider} from "./contexts/AuthContext.jsx";

createRoot(document.getElementById('root')).render(
  // StrictMode : 리엑트 자체의 무결성을 위한 테스트코드를 먼저 실행 후 다시 실제코드를 작동시킴. 조회수 상승 메서드 호출 시 2번씩 오르는 오류가 생김.
  <StrictMode>
      <AuthProvider>
        <App />
      </AuthProvider>
  </StrictMode>,
)
