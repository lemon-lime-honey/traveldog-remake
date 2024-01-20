import React from 'react';
import { Box, Dialog, DialogContent, DialogTitle, Typography } from '@mui/material';
import { Place } from '../types';
import { StaticMap } from './Map';
import UpdatePlace from './UpdatePlace';

interface PlaceDetailProps {
  place: Place | null;
  closeModal: () => void;
}

const PlaceDetail: React.FC<PlaceDetailProps> = ({ place, closeModal }) => {
  return (
    <Dialog open={Boolean(place)} onClose={closeModal}>
      <Box display="flex" justifyContent="space-between" sx={{ paddingRight: '1rem' }}>
        <DialogTitle variant="h4">{place?.name}</DialogTitle>
        <UpdatePlace placedata={place} />
      </Box>
      <DialogContent>
        <StaticMap Lat={place?.coordinate.x} Lng={place?.coordinate.y} />
        <Typography variant="body2">{place?.address}</Typography>
        <Typography>{place?.description}</Typography>
      </DialogContent>
    </Dialog>
  );
};

export default PlaceDetail;
