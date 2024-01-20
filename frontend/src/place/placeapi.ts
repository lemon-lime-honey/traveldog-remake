import { Place, PlaceResponse, PlaceEntry } from '../types';
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
  const response = await axios.post(`${import.meta.env.VITE_API_URL}/place`, place, {
    headers: {
      'Content-Type': 'application/json',
    },
  });

  return response.data;
};

export const updatePlace = async (placeEntry: PlaceEntry): Promise<PlaceResponse> => {
  const response = await axios.put(`${import.meta.env.VITE_API_URL}/place/${placeEntry.pk}`, placeEntry.place, {
    headers: {
      'Content-Type': 'application/json',
    },
  });
  return response.data;
};
