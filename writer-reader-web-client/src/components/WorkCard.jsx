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
  Chip,
} from "@mui/material";
import * as React from "react";
import { DateFormatter } from "../utility/DateFormatter";
import { Link as RouterLink } from "react-router-dom";

export default function WorkCard({ work }) {
  const languageNames = new Intl.DisplayNames(["hu-HU"], { type: "language" });

  const cardContent = (
    <React.Fragment>
      <CardContent>
        <Typography variant="subtitle" noWrap>
          {work.creator_name}
        </Typography>
        <Typography variant="subtitle2" sx={{ color: "text.secondary" }}>
          {DateFormatter(work.created_at)}
        </Typography>
        <Typography variant="h5" noWrap>
          {work.title}
        </Typography>
        <Typography variant="body2" gutterBottom>
          {languageNames.of(work.language)}
        </Typography>
        <Divider />
        <Stack
          direction="row"
          sx={{ justifyContent: "space-between", alignItems: "center" }}
        >
          {/*<Typography variant="overline" component="div">
            dicta, quaerat, ad, voluptas, voluptatum, earum, aut
          </Typography>*/}
          <Stack
            direction="row"
            spacing={1}
            sx={{ marginTop: 1, flexWrap: "wrap" }}
            useFlexGap
          >
            {work?.category?.map((category, index) => (
              <Chip key={index.toString()} label={category} size="small" />
            ))}
          </Stack>
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

  return (
    <Box sx={{ minWidth: 275 }}>
      <Card variant="elevation">
        {cardContent}
        <CardActions>
          <Button size="small" component={RouterLink} to={`/reader/${work.id}`}>
            Olvasás
          </Button>
        </CardActions>
      </Card>
    </Box>
  );
}
