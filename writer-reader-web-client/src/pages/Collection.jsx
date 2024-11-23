import { Grid2 } from "@mui/material";
import WorkCard from "../components/WorkCard";

export default function Collection() {
  return (
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
  );
}
