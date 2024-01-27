import React from 'react';
import { Card, CardActionArea, CardContent, CardHeader } from '@mui/material';
import { Place, PlaceResponse } from '../types';
import { ListMap } from './Map';

interface SinglePlaceProps {
  place: PlaceResponse;
  showSinglePlace: (place: PlaceResponse) => void;
}

const SinglePlace: React.FC<SinglePlaceProps> = ({ place, showSinglePlace }) => {
  return (
    <Card variant="outlined">
      <CardActionArea onClick={() => showSinglePlace(place)}>
        <CardHeader title={place.name} titleTypographyProps={{ noWrap: true, textAlign: 'center' }} />
        <CardContent>
          <ListMap Lat={place.coordinate.x} Lng={place.coordinate.y} pk={place.pk} />
        </CardContent>
      </CardActionArea>
    </Card>
  );
};

export default SinglePlace;
