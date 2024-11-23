import { Navigate } from "react-router-dom";
import { useState } from "react";
import { useAuthContext } from "../contexts/ContextProvider";
import {
  Accordion,
  AccordionDetails,
  AccordionSummary,
  Button,
  Container,
  Stack,
  TextField,
  Typography,
} from "@mui/material";

export default function Account() {
  const { user } = useAuthContext();
  const [newPasswordError, setNewPasswordError] = useState(false);
  const [newPasswordErrorMessage, setNewPasswordErrorMessage] = useState("");
  const [confirmNewPasswordError, setConfirmNewPasswordError] = useState(false);
  const [confirmNewPasswordErrorMessage, setConfirmNewPasswordErrorMessage] =
    useState("");
  const [passwordError, setPasswordError] = useState(false);
  const [passwordErrorMessage, setPasswordErrorMessage] = useState("");

  if (!user) {
    return <Navigate to="/register" />;
  }

  const validateInputs = () => {
    const newPassword = document.getElementById("new-password");
    const confirmNewPassword = document.getElementById("confirm-new-password");
    const password = document.getElementById("password");

    let isValid = true;

    if (!newPassword.value || newPassword.value.length < 1) {
      setNewPasswordError(true);
      setNewPasswordErrorMessage("Az új jelszó megadása szükséges.");
      isValid = false;
    } else {
      setNewPasswordError(false);
      setNewPasswordErrorMessage("");
    }

    if (!confirmNewPassword.value || confirmNewPassword.value.length < 1) {
      setConfirmNewPasswordError(true);
      setConfirmNewPasswordErrorMessage("A új jelszó megerősítése szükséges.");
      isValid = false;
    } else if (confirmNewPassword.value !== newPassword.value) {
      setConfirmNewPasswordError(true);
      setConfirmNewPasswordErrorMessage("A új jelszavak nem egyeznek.");
    } else {
      setConfirmNewPasswordError(false);
      setConfirmNewPasswordErrorMessage("");
    }

    if (!password.value || password.value.length < 1) {
      setPasswordError(true);
      setPasswordErrorMessage("A jelenlegi jelszó megadása szükséges.");
      isValid = false;
    } else {
      setPasswordError(false);
      setPasswordErrorMessage("");
    }

    return isValid;
  };

  const handleSubmit = (event) => {
    if (newPasswordError || confirmNewPasswordError || passwordError) {
      event.preventDefault();
      return;
    }
    const data = new FormData(event.currentTarget);
    console.log({
      newPassword: data.get("new-password"),
    });
  };

  return (
    <Container maxWidth="xs">
      <Stack spacing={2}>
        <Container>
          <Typography variant="h3">Fiók kezelése</Typography>
        </Container>
        <Accordion>
          <AccordionSummary
            aria-controls="passwordpanel-content"
            id="papasswordpanel-header"
          >
            <Typography>Jelszó megváltoztatása</Typography>
          </AccordionSummary>
          <AccordionDetails>
            <Stack spacing={2} component="form" onSubmit={handleSubmit}>
              <TextField
                label="Új jelszó"
                name="new-password"
                type="new-password"
                required
                fullWidth
                id="new-password"
                variant="outlined"
                error={newPasswordError}
                helperText={newPasswordErrorMessage}
                color={newPasswordError ? "error" : "primary"}
              />
              <TextField
                label="Új jelszó megerősítése"
                name="confirm-new-password"
                type="confirm-new-password"
                required
                fullWidth
                id="confirm-new-password"
                variant="outlined"
                error={confirmNewPasswordError}
                helperText={confirmNewPasswordErrorMessage}
                color={confirmNewPasswordError ? "error" : "primary"}
              />
              <TextField
                label="Jelenlegi jelszó"
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
                {" "}
                Változtatás
              </Button>
            </Stack>
          </AccordionDetails>
        </Accordion>
      </Stack>
    </Container>
  );
}
