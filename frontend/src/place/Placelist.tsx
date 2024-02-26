import { useState } from 'react';
import { useQuery } from '@tanstack/react-query';
import { getPlaces } from './placeapi';
import AddPlace from './AddPlace';
import { Place, PlaceResponse } from '../types';
import PlaceCards from './PlaceCards';
import CardModal from './PlaceDetail';
import '../assets/Styles/Placelist.css';

function Placelist() {
  const { data, error, isSuccess } = useQuery({
    queryKey: ['place'],
    queryFn: getPlaces,
  });

  const [selectedPlace, setSelectedPlace] = useState<PlaceResponse | null>(null);

  const showSinglePlace = (place: PlaceResponse) => {
    setSelectedPlace(place);
  };

  const closeModal = () => {
    setSelectedPlace(null);
  };

  if (!isSuccess) {
    return <AddPlace />;
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
