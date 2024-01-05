import { useQuery } from '@tanstack/react-query';
import { Card, CardHeader, CardContent, Grid, Typography } from '@mui/material';
import { getPlaces } from './placeapi';
import AddPlace from './AddPlace';
import { ListMap } from './Map';
import '../assets/Styles/Placelist.css';

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
      <AddPlace />
      <Grid container spacing={4} direction="row" justifyContent="flex-start" sx={{ gridAutoRows: 'fit-content' }}>
        {data.map((place) => (
          <Grid item xs={12} sm={6} md={4} lg={3} key={place.pk}>
            <Card variant="outlined">
              <CardHeader title={place.name} titleTypographyProps={{ noWrap: true, textAlign: 'center' }} />
              <CardContent>
                <ListMap Lat={place.coordinate.x} Lng={place.coordinate.y} pk={place.pk} />
                <Typography>{place.description}</Typography>
                <p>{place.address}</p>
              </CardContent>
            </Card>
          </Grid>
        ))}
      </Grid>
    </>
  );
}

export default Placelist;
