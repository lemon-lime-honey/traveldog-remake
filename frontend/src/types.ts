export type Place = {
  name: string;
  coordinate: {
    x: number;
    y: number;
  };
};

export type PlaceResponse = {
  pk: number;
  name: string;
  coordinate: {
    x: number;
    y: number;
  };
};

export type DialogFormProps = {
  place: Place;
  handleChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
};
