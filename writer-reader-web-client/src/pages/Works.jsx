import {
  Container,
  Grid2,
  TextField,
  IconButton,
  Box,
  InputAdornment,
} from "@mui/material";
import WorkCard from "../components/WorkCard";
import { Search as SearchIcon } from "@mui/icons-material";
import { useState } from "react";

export default function Works() {
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

  const SearchBar = () => (
    <Box
      component="form"
      onSubmit={handleSubmit}
      sx={{ display: "flex", flexDirection: "column" }}
    >
      <TextField
        id="searchBar"
        label="Keresés cím alapján"
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
    <Container>
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
        <Grid2 size={{ xs: 12, sm: 6, md: 4, lg: 3 }}>
          <WorkCard />
        </Grid2>
        <Grid2 size={{ xs: 12, sm: 6, md: 4, lg: 3 }}>
          <WorkCard />
        </Grid2>
        <Grid2 size={{ xs: 12, sm: 6, md: 4, lg: 3 }}>
          <WorkCard />
        </Grid2>
        <Grid2 size={{ xs: 12, sm: 6, md: 4, lg: 3 }}>
          <WorkCard />
        </Grid2>

        <Grid2 size={{ xs: 12, sm: 6, md: 4, lg: 3 }}>
          <WorkCard />
        </Grid2>
        <Grid2 size={{ xs: 12, sm: 6, md: 4, lg: 3 }}>
          <WorkCard />
        </Grid2>
        <Grid2 size={{ xs: 12, sm: 6, md: 4, lg: 3 }}>
          <WorkCard />
        </Grid2>
        <Grid2 size={{ xs: 12, sm: 6, md: 4, lg: 3 }}>
          <WorkCard />
        </Grid2>
      </Grid2>
    </Container>
  );
}
