import { Box, LinearProgress } from "@mui/material";

export default function LoadingBar() {
  return (
    <Box sx={{ width: "100%", marginTop: 25 }}>
      <LinearProgress />
    </Box>
  );
}
