export type Place = {
  name: string;
  description: string;
  address: string;
  coordinate: {
    x: number;
    y: number;
  };
};

export type PlaceResponse = {
  pk: number;
  name: string;
  description: string;
  address: string;
  coordinate: {
    x: number;
    y: number;
  };
};

export type DialogFormProps = {
  place: Place;
  handleChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
};

export type PlaceEntry = {
  place: Place;
  pk: string;
};
