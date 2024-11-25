import MDEditor from "@uiw/react-md-editor";
import { Alert, Paper } from "@mui/material";
import { useAuthContext } from "../contexts/ContextProvider";
import { Navigate } from "react-router-dom";
import { useState } from "react";

export default function Editor() {
  const [value, setValue] = useState("");
  const { user } = useAuthContext();

  /*if (!user) {
    return <Navigate to="/login" />;
  }*/

  return (
    <>
      <Alert severity="warning" sx={{ marginBottom: 2 }}>
        Építkezés alatt
      </Alert>
      <Paper>
        <div data-color-mode="light">
          <MDEditor height={200} value={value} onChange={setValue} />
        </div>
      </Paper>
    </>
  );
}
