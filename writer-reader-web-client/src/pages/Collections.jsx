import {
  Grid2,
  TextField,
  IconButton,
  Box,
  InputAdornment,
} from "@mui/material";
import CollectionCard from "../components/CollectionCard";
import { Search as SearchIcon } from "@mui/icons-material";
import { useState, useEffect } from "react";
import axios from "../api/axios";
import LoadingBar from "../components/LoadingBar";

export default function Collections() {
  const [page, setPage] = useState(0);
  const [collections, setCollections] = useState();

  const handleSubmit = (event) => {
    const searchBar = document.getElementById("searchBar");
    if (!searchBar.value || searchBar.value.length < 1) {
      event.preventDefault();
      return;
    }
    const data = new FormData(event.currentTarget);
    console.log({
      searchBar: data.get("searchBar"),
    });
  };

  const getWorks = async () => {
    await axios
      .get("/api/collections")
      .then((response) => {
        setCollections(response.data);
        setPage(response.data.meta.current_page);
      })
      .catch((error) => console.error(error));
  };

  useEffect(() => {
    getWorks();
  }, []);

  if (!collections) {
    return <LoadingBar />;
  }

  const SearchBar = () => (
    <Box
      component="form"
      onSubmit={handleSubmit}
      sx={{ display: "flex", flexDirection: "column" }}
    >
      <TextField
        id="searchBar"
        label="Gyűjtemény keresése"
        variant="outlined"
        placeholder="..."
        size="medium"
        slotProps={{
          input: {
            startAdornment: (
              <InputAdornment position="end">
                <IconButton type="submit">
                  <SearchIcon />
                </IconButton>
              </InputAdornment>
            ),
          },
        }}
      />
    </Box>
  );

  return (
    <>
      <div
        style={{
          display: "flex",
          alignSelf: "center",
          justifyContent: "center",
          flexDirection: "column",
          padding: 20,
        }}
      >
        <SearchBar />
      </div>
      <Grid2 container spacing={4}>
        {/*<Grid2 size={{ xs: 12, sm: 6, md: 4, lg: 3 }}>
          <CollectionCard />
        </Grid2>
        <Grid2 size={{ xs: 12, sm: 6, md: 4, lg: 3 }}>
          <CollectionCard />
        </Grid2>
        <Grid2 size={{ xs: 12, sm: 6, md: 4, lg: 3 }}>
          <CollectionCard />
        </Grid2>
        <Grid2 size={{ xs: 12, sm: 6, md: 4, lg: 3 }}>
          <CollectionCard />
        </Grid2>

        <Grid2 size={{ xs: 12, sm: 6, md: 4, lg: 3 }}>
          <CollectionCard />
        </Grid2>
        <Grid2 size={{ xs: 12, sm: 6, md: 4, lg: 3 }}>
          <CollectionCard />
        </Grid2>
        <Grid2 size={{ xs: 12, sm: 6, md: 4, lg: 3 }}>
          <CollectionCard />
        </Grid2>
        <Grid2 size={{ xs: 12, sm: 6, md: 4, lg: 3 }}>
          <CollectionCard />
        </Grid2>*/}
        {collections?.data?.map((collection) => (
          <Grid2 size={{ xs: 12, sm: 6, md: 4, lg: 3 }} key={collection.id}>
            <CollectionCard collection={collection} />
          </Grid2>
        ))}
      </Grid2>
    </>
  );
}
