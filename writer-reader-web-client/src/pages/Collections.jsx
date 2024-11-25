import {
  Grid2,
  TextField,
  IconButton,
  Box,
  InputAdornment,
  Container,
  Pagination,
} from "@mui/material";
import CollectionCard from "../components/CollectionCard";
import { Search as SearchIcon } from "@mui/icons-material";
import { useState, useEffect } from "react";
import axios from "../api/axios";
import LoadingBar from "../components/LoadingBar";

export default function Collections() {
  const [page, setPage] = useState(1);
  const [collections, setCollections] = useState();

  const handleChange = (event, value) => {
    setPage(value);
    getCollections(value);
  };

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

  const getCollections = async (param) => {
    let getPage = !param ? page : param;
    await axios
      .get(`/api/collections?page=${getPage}`)
      .then((response) => {
        setCollections(response.data);
        setPage(response.data.meta.current_page);
      })
      .catch((error) => console.error(error));
  };

  useEffect(() => {
    if (!collections) {
      getCollections();
    }
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
        {collections?.data?.map((collection) => (
          <Grid2 size={{ xs: 12, sm: 6, md: 4, lg: 3 }} key={collection.id}>
            <CollectionCard collection={collection} />
          </Grid2>
        ))}
      </Grid2>
      <Container maxWidth="xs" sx={{ marginTop: 2 }}>
        <Pagination
          color="primary"
          count={collections.meta?.last_page}
          page={page}
          onChange={handleChange}
          siblingCount={1}
          boundaryCount={0}
          showFirstButton
          showLastButton
        />
      </Container>
    </>
  );
}
