import { Navigate } from "react-router-dom";
import { useAuthContext } from "../contexts/ContextProvider";

export default function Register() {
  const { user } = useAuthContext();

  if (user) {
    return <Navigate to="/" />;
  }

  return <>Signup</>;
}
