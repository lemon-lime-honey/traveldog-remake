import { DialogContent, Stack, TextField } from '@mui/material';
import { DialogFormProps } from '../types';
import { GetMap } from './Map';
import { useEffect, useState } from 'react';

function PlaceDialogContent({ place, handleChange }: DialogFormProps) {
  const [inputAddress, setInputAddress] = useState('');
  const [address, setAddress] = useState('');

  useEffect(() => {
    const timer = setTimeout(() => {
      setAddress(inputAddress);
    }, 500);
    return () => {
      clearTimeout(timer);
    };
  }, [inputAddress]);

  return (
    <DialogContent>
      <Stack spacing={2} mt={1}>
        <GetMap address={address} />
        <TextField label="이름" name="name" value={place.name} onChange={handleChange} variant="outlined" required />
        <br />
        <TextField label="주소" value={inputAddress} onChange={(e) => setInputAddress(e.target.value)} required />
        <br />
        <TextField
          label="위도"
          id="latitude"
          type="number"
          InputProps={{ readOnly: true }}
          InputLabelProps={{ shrink: true }}
          onChange={handleChange}
          required
        />
        <TextField
          label="경도"
          id="longitude"
          type="number"
          InputProps={{ readOnly: true }}
          InputLabelProps={{ shrink: true }}
          onChange={handleChange}
          required
        />
      </Stack>
    </DialogContent>
  );
}

export default PlaceDialogContent;
