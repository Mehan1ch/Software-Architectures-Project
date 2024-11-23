import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import { ContextProvider } from "./contexts/ContextProvider.jsx";
import NavBar from "./components/Navbar.jsx";
import Container from "@mui/material/Container";
import Collections from "./pages/Collections.jsx";
import NotFound from "./pages/NotFound.jsx";
import Register from "./pages/Register.jsx";
import Login from "./pages/Login.jsx";
import Profile from "./pages/Profile.jsx";
import Works from "./pages/Works.jsx";
import Account from "./pages/Account.jsx";
import MyWorks from "./components/MyWorks.jsx";
import MyCollections from "./components/MyCollections.jsx";

function App() {
  return (
    <>
      <ContextProvider>
        <BrowserRouter>
          <NavBar />
          <Container sx={{ marginY: 5 }}>
            <Routes>
              <Route path="/" element={<Navigate to="/works" />} />
              <Route path="/works" element={<Works />} />
              <Route path="/collections" element={<Collections />} />
              <Route path="/profile" element={<Profile />}>
                <Route path="myworks" element={<MyWorks />} />
                <Route path="mycollections" element={<MyCollections />} />
              </Route>
              <Route path="/account" element={<Account />} />
              <Route path="/login" element={<Login />} />
              <Route path="/register" element={<Register />} />
              <Route path="*" element={<NotFound />} />
            </Routes>
          </Container>
        </BrowserRouter>
      </ContextProvider>
    </>
  );
}

export default App;
