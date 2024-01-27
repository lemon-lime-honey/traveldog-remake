import React from 'react';
import { Grid } from '@mui/material';
import SinglePlace from './SinglePlace';
import { Place, PlaceResponse } from '../types';

interface PlaceCardsProps {
  places: PlaceResponse[];
  showSinglePlace: (place: PlaceResponse) => void;
}

const PlaceCards: React.FC<PlaceCardsProps> = ({ places, showSinglePlace }) => {
  return (
    <Grid container spacing={4} direction="row" justifyContent="flex-start" sx={{ gridAutoRows: 'fit-content' }}>
      {places.map((place) => (
        <Grid item key={place.pk} xs={12} sm={6} md={4} lg={3}>
          <SinglePlace place={place} showSinglePlace={showSinglePlace} />
        </Grid>
      ))}
    </Grid>
  );
};

export default PlaceCards;
