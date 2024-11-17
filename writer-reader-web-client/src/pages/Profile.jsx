import { Navigate } from "react-router-dom";
import { useAuthContext } from "../contexts/ContextProvider";

export default function Profile() {
  const { user } = useAuthContext();

  if (!user) {
    return <Navigate to="/register" />;
  }

  return <>Profile</>;
}
