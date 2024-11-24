import { createContext, useContext, useState } from "react";
import axios from "../api/axios";
import Cookies from "js-cookie";

const AuthContext = createContext({
  user: null,
  setUser: () => {},
  updateUser: () => {},
});

const getXSRFCookie = () => {
  return Cookies.get("XSRF-TOKEN");
};

export const useAuthContext = () => useContext(AuthContext);

export const ContextProvider = ({ children }) => {
  const [user, setUser] = useState();

  const updateUser = async () => {
    try {
      const { data } = await axios.get("/api/user", JSON.stringify({}), {
        headers: {
          "X-XSRF-TOKEN": getXSRFCookie(),
        },
      });
      setUser(data);
    } catch (error) {
      console.log("User not logged in.");
    }
  };

  return (
    <AuthContext.Provider
      value={{
        user,
        setUser,
        updateUser,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
};
