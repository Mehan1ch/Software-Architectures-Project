import { createContext, useContext, useState } from "react";

const AuthContext = createContext({
  user: null,
  setUser: () => {},
});

export const useAuthContext = () => useContext(AuthContext);

export const ContextProvider = ({ children }) => {
  const [user, setUser] = useState("John Doe");

  return (
    <AuthContext.Provider
      value={{
        user,
        setUser,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
};
