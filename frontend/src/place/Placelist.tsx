import { useState } from 'react';
import { useQuery } from '@tanstack/react-query';
import { getPlaces } from './placeapi';
import AddPlace from './AddPlace';
import { Place } from '../types';
import PlaceCards from './PlaceCards';
import CardModal from './PlaceDetail';
import '../assets/Styles/Placelist.css';

function Placelist() {
  const { data, error, isSuccess } = useQuery({
    queryKey: ['place'],
    queryFn: getPlaces,
  });

  const [selectedPlace, setSelectedPlace] = useState<Place | null>(null);

  const showSinglePlace = (place: Place) => {
    setSelectedPlace(place);
  };

  const closeModal = () => {
    setSelectedPlace(null);
  };

  if (!isSuccess) {
    return <span>Loading...</span>;
  } else if (error) {
    return <span>Error!</span>;
  }

  return (
    <>
      <AddPlace />
      <PlaceCards places={data} showSinglePlace={showSinglePlace} />
      <CardModal place={selectedPlace} closeModal={closeModal} />
    </>
  );
}

export default Placelist;
