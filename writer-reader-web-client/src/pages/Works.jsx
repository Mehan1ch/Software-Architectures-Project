import { Container, Grid2 } from "@mui/material";
import WorkCard from "../components/WorkCard";

export default function Works() {
  return (
    <Container>
      <Grid2 container spacing={4}>
        <Grid2 size={3}>
          <WorkCard />
        </Grid2>
        <Grid2 size={3}>
          <WorkCard />
        </Grid2>
        <Grid2 size={3}>
          <WorkCard />
        </Grid2>
        <Grid2 size={3}>
          <WorkCard />
        </Grid2>

        <Grid2 size={3}>
          <WorkCard />
        </Grid2>
        <Grid2 size={3}>
          <WorkCard />
        </Grid2>
        <Grid2 size={3}>
          <WorkCard />
        </Grid2>
        <Grid2 size={3}>
          <WorkCard />
        </Grid2>
      </Grid2>
    </Container>
  );
}
