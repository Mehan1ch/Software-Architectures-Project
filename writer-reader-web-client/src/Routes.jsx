import { Navigate, Route, Routes } from "react-router-dom";
import Collections from "./pages/Collections.jsx";
import NotFound from "./pages/NotFound.jsx";
import Register from "./pages/Register.jsx";
import Login from "./pages/Login.jsx";
import Profile from "./pages/Profile.jsx";
import Works from "./pages/Works.jsx";
import Account from "./pages/Account.jsx";
import MyWorks from "./components/MyWorks.jsx";
import MyCollections from "./components/MyCollections.jsx";
import Reader from "./pages/Reader.jsx";
import Collection from "./pages/Collection.jsx";
import Editor from "./pages/Editor.jsx";

export default function AppRoutes() {
  return (
    <Routes>
      <Route path="/" element={<Navigate to="/works" />} />
      <Route path="/works" element={<Works />} />
      <Route path="/collections" element={<Collections />} />
      <Route
        exact
        path="/profile"
        element={<Navigate to="/profile/myworks" />}
      />
      <Route path="/profile" element={<Profile />}>
        <Route path="myworks" element={<MyWorks />} />
        <Route path="mycollections" element={<MyCollections />} />
      </Route>
      <Route path="/account" element={<Account />} />
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Register />} />
      <Route path="/reader/:workID" element={<Reader />} />
      <Route path="/editor" element={<Editor />} />
      <Route path="/collection/:collectionID" element={<Collection />} />
      <Route path="*" element={<NotFound />} />
    </Routes>
  );
}
