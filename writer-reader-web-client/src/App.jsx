import { RouterProvider } from "react-router-dom";
import router from "./router.jsx";
import { ContextProvider } from "./contexts/ContextProvider.jsx";
import NavBar from "./components/Navbar.jsx";
import { Container } from "@mui/material";

function App() {
  return (
    <>
      <ContextProvider>
        <NavBar />
        <Container sx={{ marginY: 5 }}>
          <RouterProvider router={router} />
        </Container>
      </ContextProvider>
    </>
  );
}

export default App;
