import { createBrowserRouter, Navigate } from "react-router-dom";
import Works from "./pages/Works";
import Login from "./pages/Login";
import Register from "./pages/Register";
import NotFound from "./pages/NotFound";
import Profile from "./pages/Profile";
import Collections from "./pages/Collections";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Navigate to="/works" />,
  },
  {
    path: "/register",
    element: <Register />,
  },
  {
    path: "/login",
    element: <Login />,
  },
  {
    path: "/works",
    element: <Works />,
  },
  {
    path: "/collections",
    element: <Collections />,
  },
  {
    path: "/profile",
    element: <Profile />,
  },
  {
    path: "*",
    element: <NotFound />,
  },
]);

export default router;
