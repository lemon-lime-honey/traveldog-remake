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
    <Dialog open={Boolean(place)} onClose={closeModal} fullWidth={true} PaperProps={{ sx: { height: '60%' } }}>
      <Box>
        <Box display="flex" justifyContent="space-between" sx={{ paddingRight: '1rem', pb: 0 }}>
          <DialogTitle variant="h4" sx={{ pb: 0 }}>
            {place?.name}
          </DialogTitle>
          <Box display="flex">
            <UpdatePlace placedata={place} />
            <DeletePlace url={place?._links.self.href} closeModal={closeModal} />
          </Box>
        </Box>
        <DialogTitle variant="subtitle1" sx={{ pt: 1, pb: 0 }}>
          {place?.address}
        </DialogTitle>
      </Box>
      <DialogContent sx={{ display: 'flex' }}>
        <Box sx={{ width: '45%', mr: 2 }}>
          <StaticMap Lat={place?.coordinate.x} Lng={place?.coordinate.y} />
          <Typography>{place?.description}</Typography>
        </Box>
        <Box flexGrow="1">
          <Typography variant="h5" sx={{ pt: 0 }}>
            후기
          </Typography>
          <Reviews reviews={data} />
        </Box>
      </DialogContent>
    </Dialog>
  );
};

export default PlaceDetail;
