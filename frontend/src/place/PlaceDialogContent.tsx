import { useEffect, useState } from 'react';
import { Box, DialogContent, Stack, TextField } from '@mui/material';
import { GetMap } from './Map';

function PlaceDialogContent() {
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
        <TextField label="이름" id="name" variant="outlined" required />
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
          required
        />
        <TextField label="설명" id="description" multiline rows={4} placeholder="설명" />
      </Stack>
    </DialogContent>
  );
}

export default PlaceDialogContent;
