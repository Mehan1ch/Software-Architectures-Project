import { useState } from "react";
import { Link as RouterLink, Navigate } from "react-router-dom";
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
} from "@mui/material";

export default function Register() {
  const { user } = useAuthContext();
  const [passwordError, setPasswordError] = useState(false);
  const [passwordErrorMessage, setPasswordErrorMessage] = useState("");
  const [confirmPasswordError, setConfirmPasswordError] = useState(false);
  const [confirmPasswordErrorMessage, setConfirmPasswordErrorMessage] =
    useState("");
  const [emailError, setEmailError] = useState(false);
  const [emailErrorMessage, setEmailErrorMessage] = useState("");
  const [usernameError, setUsernameError] = useState(false);
  const [usernameErrorMessage, setUsernameErrorMessage] = useState("");

  if (user) {
    return <Navigate to="/" />;
  }

  const validateInputs = () => {
    const password = document.getElementById("password");
    const confirmPassword = document.getElementById("confirmPassword");
    const email = document.getElementById("email");
    const username = document.getElementById("username");

    let isValid = true;

    if (!password.value || password.value.length < 1) {
      setPasswordError(true);
      setPasswordErrorMessage("A jelszó megadása szükséges.");
      isValid = false;
    } else {
      setPasswordError(false);
      setPasswordErrorMessage("");
    }

    if (!confirmPassword.value || confirmPassword.value.length < 1) {
      setConfirmPasswordError(true);
      setConfirmPasswordErrorMessage("A jelszó megerősítése szükséges.");
      isValid = false;
    } else if (confirmPassword.value !== password.value) {
      setConfirmPasswordError(true);
      setConfirmPasswordErrorMessage("A megadott jelszavak nem egyeznek.");
    } else {
      setConfirmPasswordError(false);
      setConfirmPasswordErrorMessage("");
    }

    if (!email.value || !/\S+@\S+\.\S+/.test(email.value)) {
      setEmailError(true);
      setEmailErrorMessage("Érvénytelen e-mail cím.");
      isValid = false;
    } else {
      setEmailError(false);
      setEmailErrorMessage("");
    }

    if (!username.value || username.value.length < 1) {
      setUsernameError(true);
      setUsernameErrorMessage("A felhasználónév megadása szükséges.");
      isValid = false;
    } else {
      setUsernameError(false);
      setUsernameErrorMessage("");
    }

    return isValid;
  };

  const handleSubmit = (event) => {
    if (usernameError || passwordError || confirmPasswordError || emailError) {
      event.preventDefault();
      return;
    }
    const data = new FormData(event.currentTarget);
    console.log({
      username: data.get("username"),
      password: data.get("password"),
    });
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
            Fiók létrehozása
          </Typography>
          <Box
            component="form"
            onSubmit={handleSubmit}
            sx={{ display: "flex", flexDirection: "column", gap: 2 }}
          >
            <TextField
              label="Felhasználónév"
              autoComplete="username"
              name="username"
              required
              fullWidth
              id="username"
              variant="outlined"
              error={usernameError}
              helperText={usernameErrorMessage}
              color={usernameError ? "error" : "primary"}
            />
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
              autoComplete="new-password"
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
            <TextField
              label="Jelszó megerősítése"
              name="confirmPassword"
              type="confirmPassword"
              required
              fullWidth
              id="confirmPassword"
              variant="outlined"
              error={confirmPasswordError}
              helperText={confirmPasswordErrorMessage}
              color={confirmPasswordError ? "error" : "primary"}
            />
            <Button
              type="submit"
              fullWidth
              variant="contained"
              onClick={validateInputs}
            >
              Regisztrálás
            </Button>
            <Divider />
            <Typography sx={{ textAlign: "center" }}>
              {"Már van felhasználói fiókja? "}
              <Link component={RouterLink} to="/login">
                Itt
              </Link>
              {" bejelentkezhet."}
            </Typography>
          </Box>
        </CardContent>
      </Card>
    </Container>
  );
}
