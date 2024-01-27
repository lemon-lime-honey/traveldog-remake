import { useEffect, useState } from 'react';
import { Box, DialogContent, Stack, TextField } from '@mui/material';
import { GetMap } from './Map';
import { DialogFormProps } from '../types';

function PlaceDialogContent({ place, handleChange }: DialogFormProps) {
  const [inputAddress, setInputAddress] = useState(place.address);
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
        <TextField
          label="이름"
          name="name"
          id="name"
          variant="outlined"
          value={place.name}
          onChange={handleChange}
          error={place.name === '' ? true : false}
          required
        />
        <Box sx={{ pb: 1, width: '100%' }} hidden>
          <TextField
            label="위도"
            id="latitude"
            type="number"
            InputProps={{ readOnly: true }}
            InputLabelProps={{ shrink: true }}
            sx={{ width: '50%', paddingRight: 1 }}
            required
          />
          <TextField
            label="경도"
            id="longitude"
            type="number"
            InputProps={{ readOnly: true }}
            InputLabelProps={{ shrink: true }}
            sx={{ width: '50%' }}
            required
          />
        </Box>
        <TextField
          label="주소"
          id="address"
          value={inputAddress}
          onChange={(e) => setInputAddress(e.target.value)}
          error={inputAddress === '' ? true : false}
          required
        />
        <TextField
          label="설명"
          id="description"
          name="description"
          multiline
          rows={4}
          placeholder="설명"
          value={place.description}
          onChange={handleChange}
        />
      </Stack>
    </DialogContent>
  );
}

export default PlaceDialogContent;
