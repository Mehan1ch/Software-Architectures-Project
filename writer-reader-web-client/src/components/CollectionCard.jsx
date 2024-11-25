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
import { Link as RouterLink } from "react-router-dom";
import * as React from "react";
import { DateFormatter } from "../utility/DateFormatter";

export default function CollectionCard({ collection }) {
  const cardContent = (
    <React.Fragment>
      <CardContent>
        <Typography variant="subtitle" noWrap>
          {collection.creator_name}
        </Typography>
        <Typography variant="subtitle2">
          {DateFormatter(collection.created_at)}
        </Typography>
        <Typography variant="h5" /*style={{ maxWidth: 250 }}*/ noWrap>
          {collection.name}
        </Typography>
        <Typography variant="body1" noWrap>
          {collection.description}
        </Typography>
        <Divider />
        <Stack
          direction="row"
          sx={{ justifyContent: "space-between", alignItems: "center" }}
        >
          <Typography variant="overline">
            Művek száma: {collection.number_of_works}
          </Typography>
          <Box sx={{ display: "flex", alignItems: "center" }}>
            <ThumbUp sx={{ width: 20 }} />
            <Typography variant="body2" marginLeft={0.5}>
              {collection.likes}
            </Typography>
          </Box>
        </Stack>
      </CardContent>
    </React.Fragment>
  );

  return (
    <Box sx={{ minWidth: 275 }}>
      <Card variant="elevation">
        {cardContent}
        <CardActions>
          <Button
            size="small"
            component={RouterLink}
            to={`/collection/${collection.id}`}
          >
            Megnyitás
          </Button>
        </CardActions>
      </Card>
    </Box>
  );
}
