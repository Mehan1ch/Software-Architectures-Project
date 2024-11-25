import { Grid2 } from "@mui/material";
import WorkCard from "./WorkCard";
import { useEffect, useState } from "react";
import LoadingBar from "./LoadingBar";
import axios from "../api/axios";
import { useAuthContext } from "../contexts/ContextProvider";

export default function MyWorks() {
  const { user } = useAuthContext();
  const [works, setWorks] = useState();

  const getWorks = async () => {
    await axios
      .get(`/api/users/${user?.id}`)
      .then((response) => {
        setWorks(response.data.data.works);
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

  return (
    <Grid2 container spacing={4}>
      {works?.data?.map((work) => (
        <Grid2 size={{ xs: 12, sm: 6, md: 4, lg: 3 }} key={work.id}>
          <WorkCard work={work} />
        </Grid2>
      ))}
    </Grid2>
  );
}
