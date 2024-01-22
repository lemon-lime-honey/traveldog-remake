export type Place = {
  pk?: number;
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
  _links: {
    self: {
      href: string;
    };
    place: {
      href: string;
    };
  };
};

export type DialogFormProps = {
  place: Place;
  handleChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
};

export type PlaceEntry = {
  place: Place;
  url: string;
};
