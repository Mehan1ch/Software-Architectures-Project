import { RouterProvider } from "react-router-dom";
import router from "./router.jsx";
import { ContextProvider } from "./contexts/ContextProvider.jsx";
import NavBar from "./components/Navbar.jsx";

function App() {
  return (
    <>
      <ContextProvider>
        <NavBar />
        <RouterProvider router={router} />
      </ContextProvider>
    </>
  );
}

export default App;
