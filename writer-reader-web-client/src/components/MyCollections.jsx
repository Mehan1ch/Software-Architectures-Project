import { Grid2 } from "@mui/material";
import CollectionCard from "./CollectionCard";
import { useEffect, useState } from "react";
import LoadingBar from "./LoadingBar";
import axios from "../api/axios";
import { useAuthContext } from "../contexts/ContextProvider";

export default function MyCollections() {
  const { user } = useAuthContext();
  const [collections, setCollections] = useState();

  const getCollections = async () => {
    await axios
      .get(`/api/users/${user?.id}`)
      .then((response) => {
        setCollections(response.data.data.collections);
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

  return (
    <Grid2 container spacing={4}>
      {collections?.data?.map((collection) => (
        <Grid2 size={{ xs: 12, sm: 6, md: 4, lg: 3 }} key={collection.id}>
          <CollectionCard collection={collection} />
        </Grid2>
      ))}
    </Grid2>
  );
}
