import {
  Container,
  Grid2,
  TextField,
  IconButton,
  Box,
  InputAdornment,
  Pagination,
} from "@mui/material";
import WorkCard from "../components/WorkCard";
import { Search as SearchIcon } from "@mui/icons-material";
import { useEffect, useState } from "react";
import axios from "../api/axios";
import LoadingBar from "../components/LoadingBar";
import { useSearchParams } from "react-router-dom";

export default function Works() {
  const [page, setPage] = useState(1);
  const [works, setWorks] = useState();
  //const [searchParams, setSearchParams] = useSearchParams();

  const handleChange = (event, value) => {
    setPage(value);
    //setSearchParams("page", value);
    getWorks(value);
  };

  //const pageParam = parseInt(searchParams.get("page"), 10); // base 10

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

  const getWorks = async (param) => {
    //let getPage = !pageParam ? 1 : pageParam;
    let getPage = !param ? page : param;
    await axios
      .get(`/api/works?page=${getPage}`)
      .then((response) => {
        setWorks(response.data);
        //setPage(response.data.meta.current_page);
      })
      .catch((error) => console.error(error));
  };

  useEffect(() => {
    if (!works) {
      getWorks();
    }
  }, []);

  if (!works) {
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
        {works?.data?.map((work) => (
          <Grid2 size={{ xs: 12, sm: 6, md: 4, lg: 3 }} key={work.id}>
            <WorkCard work={work} />
          </Grid2>
        ))}
      </Grid2>
      <Container maxWidth="xs" sx={{ marginTop: 2 }}>
        <Pagination
          color="primary"
          count={works.meta?.last_page}
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
