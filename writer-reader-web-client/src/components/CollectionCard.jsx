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
      <Typography variant="subtitle">Frank Smith</Typography>
      <Typography variant="h5"> Poems I-II </Typography>
      <Typography variant="body2" gutterBottom></Typography>
      <Divider />
      <Stack
        direction="row"
        sx={{ justifyContent: "space-between", alignItems: "center" }}
      >
        <Typography variant="overline" component="div">
          dicta, quaerat, ad, voluptas, voluptatum, earum, aut
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

export default function CollectionCard() {
  return (
    <Box sx={{ minWidth: 275 }}>
      <Card variant="elevation">
        {cardContent}
        <CardActions>
          <Button size="small">Megosztás</Button>
          <Button size="small">Megnyitás</Button>
        </CardActions>
      </Card>
    </Box>
  );
}
