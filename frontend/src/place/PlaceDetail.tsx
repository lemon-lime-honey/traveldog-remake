import React from 'react';
import { Box, Dialog, DialogContent, DialogTitle, Typography } from '@mui/material';
import { Place, PlaceResponse } from '../types';
import { StaticMap } from './Map';
import UpdatePlace from './UpdatePlace';
import DeletePlace from './DeletePlace';
import { useQuery } from '@tanstack/react-query';
import { getReviews } from '../review/reviewapi';
import Reviews from '../review/Reviews';

interface PlaceDetailProps {
  place: PlaceResponse | null;
  closeModal: () => void;
}

const PlaceDetail: React.FC<PlaceDetailProps> = ({ place, closeModal }) => {
  const { data } = useQuery({
    queryKey: ['review', place?.pk],
    queryFn: () => getReviews(place?.pk),
  });

  return (
    <Dialog open={Boolean(place)} onClose={closeModal}>
      <Box display="flex" justifyContent="space-between" sx={{ paddingRight: '1rem' }}>
        <DialogTitle variant="h4">{place?.name}</DialogTitle>
        <Box display="flex">
          <UpdatePlace placedata={place} />
          <DeletePlace url={place?._links.self.href} closeModal={closeModal} />
        </Box>
      </Box>
      <DialogContent>
        <StaticMap Lat={place?.coordinate.x} Lng={place?.coordinate.y} />
        <Typography variant="body2">{place?.address}</Typography>
        <Typography>{place?.description}</Typography>
        <Reviews reviews={data} />
      </DialogContent>
    </Dialog>
  );
};

export default PlaceDetail;
