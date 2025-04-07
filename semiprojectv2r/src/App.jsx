import React from 'react';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom"
import Header from './pages/layout/Header.jsx';
import Nav from './pages/layout/Nav.jsx';
import Footer from './pages/layout/Footer.jsx';
import Main from './pages/Main.jsx';
import Join from './pages/Join.jsx';
import Login from './pages/Login.jsx';
import Logout from "./pages/Logout.jsx";
import Myinfo from './pages/Myinfo.jsx';
import BoardList from './pages/BoardList.jsx';
import BoardWrite from './pages/BoardWrite.jsx';
import GalleryWrite from './pages/GalleryWrite.jsx';
import PdsWrite from './pages/PdsWrite.jsx';
import BoardView from './pages/BoardView.jsx';
import PdsView from './pages/PdsView.jsx';
import NotFound from './pages/NotFound.jsx';
import './styles/App.css'

function App() {

  return (
      <Router>
          <Header />
          <Nav />
          <Routes>
            <Route path="/" element={<Main />} />
            <Route path="/member/join" element={<Join />} />
            <Route path="/member/login" element={<Login />} />
            <Route path="/member/logout" element={<Logout />} />
            <Route path="/board/list/:cpg" element={<BoardList />} /> {/* @PathVariable 사용 */}
            <Route path="/board/find/:ftype/:fkey/:cpg" element={<BoardList />} /> {/* @PathVariable 사용 */}
            <Route path="/board/write" element={<BoardWrite />} />
            <Route path="/gallery/write" element={<GalleryWrite />} />
            <Route path="/pds/write" element={<PdsWrite />} />
            <Route path="/board/view/:bno" element={<BoardView />} />
            <Route path="/pds/view/:pno" element={<PdsView />} />
            {/*<Route path="/gallery/list" element={<GalleryList />} />*/}
            <Route path="/member/myinfo" element={<Myinfo />} />
            <Route path="*" element={<NotFound />} />
          </Routes>
          <Footer />
      </Router>
  )
}

export default App
