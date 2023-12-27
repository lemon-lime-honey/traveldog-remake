import { Place, PlaceResponse } from '../types';
import axios from 'axios';

export const getPlaces = async (): Promise<PlaceResponse[]> => {
  const response = await axios.get(`${import.meta.env.VITE_API_URL}/place`, {
    headers: {
      'Content-Type': 'application/json',
    },
  });
  return response.data;
};
