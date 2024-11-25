import { Box, Grid2, Typography, Stack } from "@mui/material";
import WorkCard from "../components/WorkCard";
import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "../api/axios";
import LoadingBar from "../components/LoadingBar";
import { ThumbUp } from "@mui/icons-material";
import Comment from "../components/Comment";

export default function Collection() {
  const [collection, setCollection] = useState();
  let { collectionID } = useParams();

  const getCollection = async () => {
    console.log();
    await axios
      .get(`/api/collections/${collectionID}`, JSON.stringify())
      .then((response) => {
        setCollection(response.data);
      })
      .catch((error) => console.error(error));
  };

  useEffect(() => {
    getCollection();
  }, []);

  if (!collection) {
    return <LoadingBar />;
  }

  return (
    <Stack>
      <Grid2 container spacing={4}>
        {collection?.data?.works?.data?.map((work) => (
          <Grid2 size={{ xs: 12, sm: 6, md: 4, lg: 3 }} key={work.id}>
            <WorkCard work={work} />
          </Grid2>
        ))}
      </Grid2>
      <Stack
        direction="row"
        sx={{
          justifyContent: "space-between",
          alignItems: "center",
          marginTop: 2,
        }}
      >
        <Typography variant="h5" gutterBottom>
          Hozzászólások ({collection?.data?.comments?.data?.length})
        </Typography>
        <Box sx={{ display: "flex", alignItems: "center" }}>
          <ThumbUp sx={{ width: 30 }} />
          <Typography variant="body2" marginLeft={0.5}>
            {collection?.data?.likes}
          </Typography>
        </Box>
      </Stack>
      <Stack spacing={0.5} sx={{ marginTop: 1 }}>
        {collection?.data?.comments?.data?.map((comment) => (
          <Comment comment={comment} key={comment.id} />
        ))}
      </Stack>
    </Stack>
  );
}
