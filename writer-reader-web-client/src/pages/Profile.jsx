import { Link as RouterLink, Navigate, Outlet } from "react-router-dom";
import { useAuthContext } from "../contexts/ContextProvider";
import { Box, Tab, Tabs } from "@mui/material";
import { useState } from "react";

export default function Profile() {
  const { user } = useAuthContext();
  const [value, setValue] = useState(0);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  if (!user) {
    return <Navigate to="/login" />;
  }

  return (
    <Box sx={{ width: "100%", bgcolor: "background.paper" }}>
      <Tabs
        value={value}
        onChange={handleChange}
        centered
        role="navigation"
        sx={{ marginBottom: 2 }}
      >
        <Tab
          label="Saját műveim"
          LinkComponent={RouterLink}
          to="/profile/myworks"
        />
        <Tab
          label="Saját gyűjteményeim"
          LinkComponent={RouterLink}
          to="/profile/mycollections"
        />
      </Tabs>
      <Outlet />
    </Box>
  );
}
