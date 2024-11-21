import { ThumbUp } from "@mui/icons-material";
import {
  Box,
  Card,
  CardContent,
  CardActions,
  Typography,
  Button,
  Divider,
  Stack,
} from "@mui/material";
import * as React from "react";

const cardContent = (
  <React.Fragment>
    <CardContent>
      <Typography variant="subtitle">Frank Herbert</Typography>
      <Typography variant="subtitle2" sx={{ color: "text.secondary" }}>
        1981
      </Typography>
      <Typography variant="h5"> God Emperor of Dune </Typography>
      <Typography variant="body2" gutterBottom>
        A Dűne Istencsászára a zsarnoki II. Leto története, aki egyszerre
        elnyomó és áldozat
      </Typography>
      <Divider />
      <Stack
        direction="row"
        sx={{ justifyContent: "space-between", alignItems: "center" }}
      >
        <Typography variant="overline" component="div">
          Sci-fi, magyar
        </Typography>
        <Box sx={{ display: "flex", alignItems: "center" }}>
          <ThumbUp sx={{ width: 20 }} />
          <Typography variant="body2" marginLeft={0.5}>
            12
          </Typography>
        </Box>
      </Stack>
    </CardContent>
  </React.Fragment>
);

export default function WorkCard() {
  return (
    <Box sx={{ minWidth: 275 }}>
      <Card variant="elevation">
        {cardContent}
        <CardActions>
          <Button size="small">Megosztás</Button>
          <Button size="small">Olvasás</Button>
        </CardActions>
      </Card>
    </Box>
  );
}
