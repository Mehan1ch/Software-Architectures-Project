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
import { useState } from "react";

export default function Login() {
  const { user } = useAuthContext();
  const [passwordError, setPasswordError] = useState(false);
  const [passwordErrorMessage, setPasswordErrorMessage] = useState("");
  const [usernameError, setUsernameError] = useState(false);
  const [usernameErrorMessage, setUsernameErrorMessage] = useState("");

  if (user) {
    return <Navigate to="/" />;
  }

  const validateInputs = () => {
    const password = document.getElementById("password");
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
    if (usernameError || passwordError) {
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
            Író-Olvasó
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
