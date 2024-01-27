import { IconButton } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import { deletePlace } from './placeapi';
import React from 'react';
import { useMutation, useQueryClient } from '@tanstack/react-query';

type DeletePlaceProps = {
  url: string;
  closeModal: () => void;
};

function DeletePlace({ url, closeModal }: DeletePlaceProps) {
  const queryClient = useQueryClient();

  const { mutate } = useMutation({
    mutationFn: deletePlace,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['place'] });
    },
    onError: (err) => {
      console.error(err);
    },
  });

  return (
    <>
      <IconButton
        aria-label="delete"
        size="small"
        onClick={() => {
          mutate(url);
          closeModal();
        }}
      >
        <DeleteIcon fontSize="small" />
      </IconButton>
    </>
  );
}

export default DeletePlace;
