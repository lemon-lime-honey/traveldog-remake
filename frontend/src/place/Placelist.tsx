import { useQuery } from '@tanstack/react-query';
import { Card, CardHeader, CardContent, Grid, Typography } from '@mui/material';
import { getPlaces } from './placeapi';

function Placelist() {
  const { data, error, isSuccess } = useQuery({
    queryKey: ['place'],
    queryFn: getPlaces,
  });

  if (!isSuccess) {
    return <span>Loading...</span>;
  } else if (error) {
    return <span>Error!</span>;
  }

  return (
    <>
      <Grid container spacing={4} direction="row" justifyContent="flex-start">
        {data.map((place) => (
          <Grid item xs={12} sm={6} md={4} lg={3} key={place.pk}>
            <Card variant="outlined">
              <CardHeader
                title={place.name}
                titleTypographyProps={{ noWrap: true, textAlign: 'center' }}
                sx={{ display: 'flex', overflow: 'hidden', '& .MuiCardHeader-content': { overflow: 'hidden' } }}
              />
              <CardContent>
                <Typography noWrap>
                  Did you hear that? They've shut down the main reactor. We'll be destroyed for sure. This is madness!
                  We're doomed! There'll be no escape for the Princess this time. What's that? Artoo! Artoo-Detoo, where
                  are you? At last! Where have you been? They're heading in this direction.
                </Typography>
              </CardContent>
            </Card>
          </Grid>
        ))}
      </Grid>
    </>
  );
}

export default Placelist;
