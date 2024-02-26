import { useEffect, useState } from 'react';
import { Box, DialogContent, Stack, TextField } from '@mui/material';
import { GetMap } from './Map';
import { DialogFormProps } from '../types';

function PlaceDialogContent({ place, handleChange }: DialogFormProps) {
  const [inputAddress, setInputAddress] = useState(place.address);
  const [address, setAddress] = useState('');
  const [coord, setCoord] = useState(() =>
    place.address === '' ? { x: 37.5758772, y: 126.9768121 } : place.coordinate
  );

  const handleCoord = (event: React.ChangeEvent<HTMLInputElement>) => {
    if (event.target.name === 'x') {
      setCoord({ ...coord, x: { ...coord.x, x: event.target.value } });
    } else {
      setCoord({ ...coord, y: { ...coord.y, y: event.target.value } });
    }
  };

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
        <GetMap address={address} handleCoord={handleCoord} />
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
            name="x"
            InputProps={{ readOnly: true }}
            InputLabelProps={{ shrink: true }}
            sx={{ width: '50%', paddingRight: 1 }}
            value={coord.x}
            error={coord.x < 33 || coord.x > 43 ? false : true}
            required
          />
          <TextField
            label="경도"
            id="longitude"
            type="number"
            name="y"
            InputProps={{ readOnly: true }}
            InputLabelProps={{ shrink: true }}
            value={coord.y}
            error={coord.y < 124 || coord.y > 132 ? false : true}
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
