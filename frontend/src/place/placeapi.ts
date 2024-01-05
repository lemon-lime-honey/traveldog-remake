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

export const addPlace = async (place: Place): Promise<PlaceResponse> => {
  console.log(place);
  const response = await axios.post(`${import.meta.env.VITE_API_URL}/place`, place, {
    headers: {
      'Content-Type': 'application/json',
    },
  });

  return response.data;
};
