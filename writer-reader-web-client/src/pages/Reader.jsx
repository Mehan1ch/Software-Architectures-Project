import {
  Chip,
  Container,
  Divider,
  Pagination,
  Paper,
  Stack,
  Typography,
} from "@mui/material";
import MuiMarkdown from "mui-markdown";
import { useEffect, useState } from "react";
import Comment from "../components/Comment";
import { DateFormatter } from "../utility/DateFormatter";

export default function Reader() {
  const [work, setWork] = useState(null);
  const [chapter, setChapter] = useState(1);

  const handleChange = (event, value) => {
    setChapter(value);
  };

  useEffect(() => {
    fetch("./examplework.json")
      .then((response) => response.json())
      .then((data) => setWork(data))
      .catch((error) => console.error(error));
  }, []);

  if (!work) {
    return <div>Loading...</div>;
  }

  return (
    <Stack>
      <Typography variant="h4">{work.data.title}</Typography>
      <Divider />
      <Typography variant="overline">
        Szerző: {work.data.creator_name}
      </Typography>
      <Typography variant="caption">Nyelv: {work.data.language}</Typography>
      <Typography variant="caption">
        Létrehozva: {DateFormatter(work.data.created_at)}
      </Typography>
      <Typography variant="caption">
        Utoljára módosítva: {DateFormatter(work.data.updated_at)}
      </Typography>
      <Stack
        direction="row"
        spacing={1}
        sx={{ marginTop: 1, flexWrap: "wrap" }}
        useFlexGap
      >
        {work.data.characters.map((character) => (
          <Chip
            key={character}
            label={character}
            size="small"
            color="info"
            variant="outlined"
          />
        ))}
      </Stack>
      <Stack
        direction="row"
        spacing={1}
        sx={{ marginTop: 1, flexWrap: "wrap" }}
        useFlexGap
      >
        {work.data.categories.map((category) => (
          <Chip key={category} label={category} size="small" />
        ))}
      </Stack>
      <Stack
        direction="row"
        spacing={1}
        sx={{ marginTop: 1, flexWrap: "wrap" }}
        useFlexGap
      >
        {work.data.tags.map((tag) => (
          <Chip key={tag} label={tag} size="small" />
        ))}
      </Stack>
      <Paper sx={{ padding: 2, marginTop: 2 }}>
        <Typography variant="h5" color="primary">
          {work.data.chapters[chapter - 1].title}
        </Typography>
        <Divider sx={{ marginY: 2 }} />
        <MuiMarkdown>{work.data.chapters[chapter - 1].content}</MuiMarkdown>
      </Paper>
      <Container maxWidth="xs" sx={{ marginTop: 2 }}>
        <Pagination
          color="primary"
          count={work.data.chapters.length}
          page={chapter}
          onChange={handleChange}
          siblingCount={1}
          boundaryCount={0}
          showFirstButton
          showLastButton
        />
      </Container>
      <Typography variant="h5" gutterBottom>
        Hozzászólások ({work.data.comments.length})
      </Typography>
      <Stack spacing={0.5}>
        {work.data.comments.map((comment) => (
          <Comment comment={comment} />
        ))}
      </Stack>
    </Stack>
  );
}
