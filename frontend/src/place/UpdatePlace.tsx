import { useMutation, useQueryClient } from '@tanstack/react-query';
import { Place, PlaceEntry, PlaceResponse } from '../types';
import { useState } from 'react';
import { updatePlace } from './placeapi';
import { Button, Dialog, DialogActions, DialogTitle, IconButton, Tooltip } from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';
import PlaceDialogContent from './PlaceDialogContent';

type FormProps = {
  placedata: PlaceResponse;
};

function UpdatePlace({ placedata }: FormProps) {
  const queryClient = useQueryClient();
  const [open, setOpen] = useState(false);
  const [place, setPlace] = useState<Place>({
    name: '',
    description: '',
    address: '',
    coordinate: {
      x: 0,
      y: 0,
    },
  });

  const { mutate } = useMutation({
    mutationFn: updatePlace,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['place'] });
    },
    onError: (err) => {
      console.error(err);
    },
  });

  const handleClickOpen = () => {
    setPlace({
      name: placedata.name,
      description: placedata.description,
      address: placedata.address,
      coordinate: placedata.coordinate,
    });

    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    const lat = document.getElementById('latitude');
    const lng = document.getElementById('longitude');
    const address = document.getElementById('address');
    place.coordinate.x = Number(lat.value);
    place.coordinate.y = Number(lng.value);
    place.address = address.value;
    place.pk = placedata.pk;
    setPlace(place);
    const url = placedata._links.self.href;
    const placeEntry: PlaceEntry = { place, url };
    mutate(placeEntry);
    setPlace({
      name: '',
      description: '',
      address: '',
      coordinate: {
        x: 0,
        y: 0,
      },
    });
    setOpen(false);
  };

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPlace({ ...place, [event.target.name]: event.target.value });
  };

  return (
    <>
      <Tooltip title="장소 수정">
        <IconButton aria-label="edit" size="small" onClick={handleClickOpen}>
          <EditIcon fontSize="small" />
        </IconButton>
      </Tooltip>
      <form onSubmit={handleSubmit}>
        <Dialog open={open} onClose={handleClose} fullWidth={true} maxWidth={'md'}>
          <DialogTitle>장소 수정하기</DialogTitle>
          <PlaceDialogContent place={place} handleChange={handleChange} />
          <DialogActions>
            <Button onClick={handleClose}>취소</Button>
            <Button type="submit">저장</Button>
          </DialogActions>
        </Dialog>
      </form>
    </>
  );
}

export default UpdatePlace;
