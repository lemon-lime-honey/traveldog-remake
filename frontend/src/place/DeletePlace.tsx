import { IconButton } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import { deletePlace } from './placeapi';
import React from 'react';

interface DeletePlaceProps {
  url: string;
  closeModal: () => void;
}

const DeletePlace: React.FC<DeletePlaceProps> = ({ url, closeModal }) => {
  return (
    <>
      <IconButton
        aria-label="delete"
        size="small"
        onClick={() => {
          deletePlace(url);
          closeModal();
        }}
      >
        <DeleteIcon fontSize="small" />
      </IconButton>
    </>
  );
};

export default DeletePlace;
