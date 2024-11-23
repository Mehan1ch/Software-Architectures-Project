import { ThumbUp } from "@mui/icons-material";
import { Box, Card, CardContent, Typography, Stack } from "@mui/material";
import { DateFormatter } from "../utility/DateFormatter";

export default function Comment({ comment }) {
  return (
    <Box>
      <Card variant="elevation">
        <CardContent>
          <Typography variant="h6" gutterBottom>
            {comment.use_name}
          </Typography>
          <Typography variant="body1" sx={{ color: "text.secondary" }}>
            {comment.content}
          </Typography>
          <Stack
            direction="row"
            sx={{ justifyContent: "space-between", alignItems: "center" }}
          >
            <Typography variant="overline" component="div">
              {DateFormatter(comment.created_at)}
            </Typography>
            <Box sx={{ display: "flex", alignItems: "center" }}>
              <ThumbUp sx={{ width: 20 }} />
              <Typography variant="body2" marginLeft={0.5}>
                {comment.likes}
              </Typography>
            </Box>
          </Stack>
        </CardContent>
      </Card>
    </Box>
  );
}
