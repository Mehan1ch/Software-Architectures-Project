import { BrowserRouter } from "react-router-dom";
import { ContextProvider } from "./contexts/ContextProvider.jsx";
import NavBar from "./components/Navbar.jsx";
import Container from "@mui/material/Container";
import AppRoutes from "./Routes.jsx";

function App() {
  return (
    <>
      <ContextProvider>
        <BrowserRouter>
          <NavBar />
          <Container sx={{ marginY: 3 }}>
            <AppRoutes />
          </Container>
        </BrowserRouter>
      </ContextProvider>
    </>
  );
}

export default App;
