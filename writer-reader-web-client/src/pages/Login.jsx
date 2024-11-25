import { Link as RouterLink, Navigate, useNavigate } from "react-router-dom";
import { useAuthContext } from "../contexts/ContextProvider";
import {
  Box,
  Card,
  Typography,
  TextField,
  Button,
  Divider,
  CardContent,
  Container,
  Link,
  Alert,
} from "@mui/material";
import { useState } from "react";
import axios from "../api/axios";
import Cookies from "js-cookie";

export default function Login() {
  const { user, updateUser } = useAuthContext();
  const [passwordError, setPasswordError] = useState(false);
  const [passwordErrorMessage, setPasswordErrorMessage] = useState("");
  const [emailError, setEmailError] = useState(false);
  const [emailErrorMessage, setEmailErrorMessage] = useState("");
  const [loginErrors, setLoginErrors] = useState([]);
  const [invalidCred, setInvalidCred] = useState(false);
  const navigate = useNavigate();

  if (user) {
    navigate(-1, { replace: true });
    //return <Navigate to="/" />;
  }

  const getXSRFCookie = () => {
    return Cookies.get("XSRF-TOKEN");
  };

  const validateInputs = () => {
    const password = document.getElementById("password");
    const email = document.getElementById("email");

    let isValid = true;

    if (!password.value || password.value.length < 1) {
      setPasswordError(true);
      setPasswordErrorMessage("A jelszó megadása szükséges.");
      isValid = false;
    } else {
      setPasswordError(false);
      setPasswordErrorMessage("");
    }

    if (!email.value || !/\S+@\S+\.\S+/.test(email.value)) {
      setEmailError(true);
      setEmailErrorMessage("Érvénytelen e-mail cím.");
      isValid = false;
    } else {
      setEmailError(false);
      setEmailErrorMessage("");
    }

    return isValid;
  };

  const handleLogin = async (event) => {
    setLoginErrors([]);
    setInvalidCred(false);
    event.preventDefault();
    if (emailError || passwordError) {
      return;
    }
    const formData = new FormData(event.currentTarget);
    try {
      const response = await axios.post(
        "/login",
        JSON.stringify({
          email: formData.get("email"),
          password: formData.get("password"),
        }),
        {
          headers: {
            "X-XSRF-TOKEN": getXSRFCookie(),
          },
        }
      );
      console.log(JSON.stringify(response));
      if (response.status === 204) {
        updateUser();
      }
    } catch (error) {
      if (error.response.status === 422) {
        setLoginErrors(error.response.data.errors);
      } else if (error.response.status === 401) {
        setInvalidCred(true);
      }
    }
  };

  return (
    <Container maxWidth="xs">
      <Card variant="outlined" sx={{ width: 500 }}>
        <CardContent>
          <Typography
            align="center"
            component="h1"
            variant="h4"
            sx={{ width: "100%" }}
            gutterBottom
          >
            Író-Olvasó
          </Typography>
          <Box
            component="form"
            onSubmit={handleLogin}
            sx={{ display: "flex", flexDirection: "column", gap: 2 }}
          >
            <TextField
              label="E-mail cím"
              autoComplete="email"
              name="email"
              required
              fullWidth
              id="email"
              variant="outlined"
              error={emailError}
              helperText={emailErrorMessage}
              color={emailError ? "error" : "primary"}
            />
            <TextField
              label="Jelszó"
              autoComplete="current-password"
              name="password"
              type="password"
              required
              fullWidth
              id="password"
              variant="outlined"
              error={passwordError}
              helperText={passwordErrorMessage}
              color={passwordError ? "error" : "primary"}
            />
            {loginErrors.email && (
              <Alert severity="error">Érvénytelen e-mail cím.</Alert>
            )}
            {invalidCred && (
              <Alert severity="error">Hibás e-mail cím vagy jelszó!</Alert>
            )}
            <Button
              type="submit"
              fullWidth
              variant="contained"
              onClick={validateInputs}
            >
              Bejelentkezés
            </Button>
            <Divider />
            <Typography sx={{ textAlign: "center" }}>
              {"Még nincsen felhasználói fiókja? "}
              <Link component={RouterLink} to="/register">
                Itt
              </Link>
              {" regisztrálhat."}
            </Typography>
          </Box>
        </CardContent>
      </Card>
    </Container>
  );
}
