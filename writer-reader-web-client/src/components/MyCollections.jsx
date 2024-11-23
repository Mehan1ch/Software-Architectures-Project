import { Grid2 } from "@mui/material";
import CollectionCard from "./CollectionCard";

export default function MyCollections() {
  return (
    <>
      <Grid2 container spacing={4}>
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
        </Grid2>
        <Grid2 size={{ xs: 12, sm: 6, md: 4, lg: 3 }}>
          <CollectionCard />
        </Grid2>
      </Grid2>
    </>
  );
}
