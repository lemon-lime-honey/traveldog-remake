import { Place, PlaceResponse, PlaceEntry } from '../types';
import axios from 'axios';
import Placelist from './Placelist';

export const getPlaces = async (): Promise<PlaceResponse[]> => {
  const response = await axios.get(`${import.meta.env.VITE_API_URL}/place`, {
    headers: {
      'Content-Type': 'application/json',
    },
  });
  return response.data._embedded.placeResponseDtoList;
};

export const addPlace = async (place: Place): Promise<PlaceResponse> => {
  const response = await axios.post(`${import.meta.env.VITE_API_URL}/place`, place, {
    headers: {
      'Content-Type': 'application/json',
    },
  });
  console.log(place);
  console.log(response);

  return response.data;
};

export const updatePlace = async (placeEntry: PlaceEntry): Promise<PlaceResponse> => {
  console.log(placeEntry);
  const response = await axios.put(placeEntry.url, placeEntry.place, {
    headers: {
      'Content-Type': 'application/json',
    },
  });
  console.log(response);
  return response.data;
};

export const deletePlace = async (url: string): Promise<PlaceResponse> => {
  const response = await axios.delete(url, {
    headers: {
      'Content-Type': 'application/json',
    },
  });
  return response.data;
};
