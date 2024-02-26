import React, { useEffect, useState } from 'react';

type GetMapProps = {
  address: string;
  handleCoord: (event: React.ChangeEvent<HTMLInputElement>) => void;
};

const { kakao } = window;

export function StaticMap({ Lat, Lng }: { Lat: number; Lng: number }) {
  useEffect(() => {
    const markerPosition = new kakao.maps.LatLng(Lat, Lng);
    const marker = { position: markerPosition };
    const container = document.getElementById('staticmap');
    const mapOption = {
      center: markerPosition,
      level: 5,
      marker: marker,
    };
    const staticMap = new kakao.maps.StaticMap(container, mapOption);
  }, []);

  return <div id="staticmap" style={{ width: '100%', aspectRatio: 1 }}></div>;
}

export function GetMap({ address, handleCoord }: GetMapProps) {
  useEffect(() => {
    const container = document.getElementById('map');
    const lat = document.getElementById('latitude');
    const lng = document.getElementById('longitude');
    let option;

    if (lat.value && lng.value) {
      option = {
        center: new kakao.maps.LatLng(lng.value, lat.value),
        draggable: false,
        level: 3,
      };
    } else {
      option = {
        center: new kakao.maps.LatLng(37.5759, 126.9768),
        draggable: false,
        level: 3,
      };
    }

    let map = new kakao.maps.Map(container, option);
    let marker = new kakao.maps.Marker({
      map: map,
      position: option.center,
    });
    let geocoder = new kakao.maps.services.Geocoder();
    let coords;
    geocoder.addressSearch(address, function (result, status) {
      if (status === kakao.maps.services.Status.OK) {
        coords = new kakao.maps.LatLng(result[0].y, result[0].x);
        marker = new kakao.maps.Marker({
          map: map,
          position: coords,
        });
        map.setCenter(coords);
        lat.value = result[0].y;
        lng.value = result[0].x;
        handleCoord;
      }
    });
  }, [address]);

  return <div id="map" style={{ width: 'auto', height: '500px' }}></div>;
}

export function ListMap({ Lat, Lng, pk }: { Lat: number; Lng: number; pk: number }) {
  useEffect(() => {
    const markerPosition = new kakao.maps.LatLng(Lat, Lng);
    const marker = { position: markerPosition };
    const container = document.getElementById(pk.toString());
    const mapOption = {
      center: markerPosition,
      level: 4,
      marker: marker,
    };
    const listMap = new kakao.maps.StaticMap(container, mapOption);
  }, []);

  return <div id={pk.toString()} style={{ width: '227px', height: '227px', margin: 'auto' }}></div>;
}
