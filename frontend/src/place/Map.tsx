import React, { useEffect, useState } from 'react';

const { kakao } = window;

export function StaticMap() {
  useEffect(() => {
    const markerPosition = new kakao.maps.LatLng(37.5759, 126.9768);
    const marker = { position: markerPosition };
    const container = document.getElementById('staticmap');
    const mapOption = {
      center: markerPosition,
      level: 3,
      marker: marker,
    };
    const staticMap = new kakao.maps.StaticMap(container, mapOption);
  }, []);

  return <div id="staticmap" style={{ width: 'auto', height: '500px' }}></div>;
}

export function GetMap({ address }) {
  useEffect(() => {
    const container = document.getElementById('map');
    const lat = document.getElementById('latitude');
    const lng = document.getElementById('longitude');
    let option = {
      center: new kakao.maps.LatLng(37.5759, 126.9768),
      draggable: false,
      level: 3,
    };
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
      }
    });
  }, [address]);

  return <div id="map" style={{ width: 'auto', height: '500px' }}></div>;
}

export function ListMap({ Lat, Lng, pk }: { Lat: number; Lng: number; pk: string }) {
  useEffect(() => {
    const markerPosition = new kakao.maps.LatLng(Lat, Lng);
    const marker = { position: markerPosition };
    const container = document.getElementById(pk);
    const mapOption = {
      center: markerPosition,
      level: 4,
      marker: marker,
    };
    const listMap = new kakao.maps.StaticMap(container, mapOption);
  }, []);

  return <div id={pk} style={{ width: '227px', height: '227px', margin: 'auto' }}></div>;
}
