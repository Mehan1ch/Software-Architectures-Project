import {
  Box,
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
import { useParams } from "react-router-dom";
import axios from "../api/axios";
import { ThumbUp } from "@mui/icons-material";
import LoadingBar from "../components/LoadingBar";

export default function Reader() {
  const [work, setWork] = useState();
  const [chapter, setChapter] = useState(1);
  let { workID } = useParams();

  const languageNames = new Intl.DisplayNames(["hu-HU"], { type: "language" });

  const handleChange = (event, value) => {
    setChapter(value);
  };

  const getWork = async () => {
    await axios
      .get(`/api/works/${workID}`, JSON.stringify())
      .then((response) => {
        setWork(response.data);
      })
      .catch((error) => console.error(error));
  };

  useEffect(() => {
    getWork();
  }, []);

  /*useEffect(() => {
    fetch("./examplework.json")
      .then((response) => response.json())
      .then((data) => setWork(data))
      .catch((error) => console.error(error));
  }, []);*/

  if (!work) {
    //return <div>Loading...</div>;
    return <LoadingBar />;
  }

  return (
    <Stack>
      <Typography variant="h4">{work.data?.title}</Typography>
      <Divider />
      <Typography variant="overline">
        Szerző: {work.data?.creator_name}
      </Typography>
      <Typography variant="caption">
        Nyelv: {languageNames.of(!work.data?.language)}
      </Typography>
      <Typography variant="caption">
        Létrehozva: {DateFormatter(work.data?.created_at)}
      </Typography>
      <Typography variant="caption">
        Utoljára módosítva: {DateFormatter(work.data?.updated_at)}
      </Typography>
      <Stack
        direction="row"
        spacing={1}
        sx={{ marginTop: 1, flexWrap: "wrap" }}
        useFlexGap
      >
        {work.data?.characters.map((character) => (
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
        {work.data?.categories?.map((category) => (
          <Chip key={category} label={category} size="small" />
        ))}
      </Stack>
      <Stack
        direction="row"
        spacing={1}
        sx={{ marginTop: 1, flexWrap: "wrap" }}
        useFlexGap
      >
        {work.data?.tags.map((tag) => (
          <Chip key={tag} label={tag} size="small" />
        ))}
      </Stack>
      {work.data?.chapters?.length > 0 ? (
        <>
          <Paper sx={{ padding: 2, marginTop: 2 }}>
            <Typography variant="h5" color="primary">
              {work.data?.chapters[chapter - 1]?.title}
            </Typography>
            <Divider sx={{ marginY: 2 }} />
            <MuiMarkdown>
              {work.data?.chapters[chapter - 1]?.content}
            </MuiMarkdown>
          </Paper>
          <Container maxWidth="xs" sx={{ marginTop: 2 }}>
            <Pagination
              color="primary"
              count={work.data?.chapters.length}
              page={chapter}
              onChange={handleChange}
              siblingCount={1}
              boundaryCount={0}
              showFirstButton
              showLastButton
            />
          </Container>
        </>
      ) : (
        <Paper sx={{ padding: 2, marginTop: 2 }}>
          <MuiMarkdown>{work.data?.content}</MuiMarkdown>
        </Paper>
      )}
      <Stack
        direction="row"
        sx={{
          justifyContent: "space-between",
          alignItems: "center",
          marginTop: 2,
        }}
      >
        <Typography variant="h5" gutterBottom>
          Hozzászólások ({work.data?.comments?.length})
        </Typography>
        <Box sx={{ display: "flex", alignItems: "center" }}>
          <ThumbUp sx={{ width: 30 }} />
          <Typography variant="body2" marginLeft={0.5}>
            {work.data?.likes}
          </Typography>
        </Box>
      </Stack>
      <Stack spacing={0.5} sx={{ marginTop: 1 }}>
        {work.data?.comments?.map((comment) => (
          <Comment comment={comment} key={comment.id} />
        ))}
      </Stack>
    </Stack>
  );
}
