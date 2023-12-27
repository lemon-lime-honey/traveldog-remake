import { useState } from 'react';
import { useQuery } from '@tanstack/react-query';
import Snackbar from '@mui/material/Snackbar';
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

  return <></>;
}

export default Placelist;
