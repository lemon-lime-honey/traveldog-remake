import { useState } from 'react';
import { Button, Dialog, DialogActions, DialogTitle } from '@mui/material';
import { useMutation, useQueryClient } from '@tanstack/react-query';
import { Place } from '../types';
import { addPlace } from './placeapi';
import PlaceDialogContent from './PlaceDialogContent';

function AddPlace() {
  const queryClient = useQueryClient();

  const [open, setOpen] = useState(false);

  const [place, setPlace] = useState<Place>({
    name: '',
    description: '',
    address: '',
    coordinate: { x: 0, y: 0 },
  });

  const handleSave = () => {
    const lat = document.getElementById('latitude');
    const lng = document.getElementById('longitude');
    const desc = document.getElementById('description');
    const address = document.getElementById('address');
    place.coordinate.x = lat.value;
    place.coordinate.y = lng.value;
    place.description = desc.value;
    place.address = address.value;
    setPlace(place);
    mutate(place);
    setPlace({ name: '', description: '', address: '', coordinate: { x: 0, y: 0 } });
    handleClose();
  };

  const { mutate } = useMutation({
    mutationFn: addPlace,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['place'] });
    },
    onError: (err) => {
      console.error(err);
    },
  });

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const p = { ...place };
    p.name = event.target.value;
    setPlace(p);
  };

  return (
    <>
      <Button onClick={handleClickOpen}>장소 추가하기</Button>
      <Dialog open={open} onClose={handleClose} fullWidth={true} maxWidth={'md'}>
        <DialogTitle>새 장소</DialogTitle>
        <PlaceDialogContent place={place} handleChange={handleChange} />
        <DialogActions>
          <Button onClick={handleClose}>취소</Button>
          <Button onClick={handleSave}>저장</Button>
        </DialogActions>
      </Dialog>
    </>
  );
}

export default AddPlace;
