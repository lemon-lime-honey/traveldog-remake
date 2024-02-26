import { Review, ReviewResponse, ReviewEntry } from '../types';
import axios from 'axios';

export const getReviews = async (placePk: number | undefined): Promise<ReviewResponse[] | undefined> => {
  try {
    const response = await axios.get(`${import.meta.env.VITE_API_URL}/place/${placePk}/review`, {
      headers: {
        'Content-Type': 'application/json',
      },
    });
    return response.data._embedded.reviewResponseDtoList;
  } catch (error) {
    const response: ReviewResponse[] = [];
    return response;
  }
};

export const addReview = async (review: Review): Promise<ReviewResponse> => {
  const response = await axios.post(`${import.meta.env.VITE_API_URL}/place/${review.placePk}/review`, review, {
    headers: {
      'Content-Type': 'application/json',
    },
  });
  return response.data;
};

export const updateReview = async (reviewEntry: ReviewEntry): Promise<ReviewResponse> => {
  const response = await axios.put(reviewEntry.url, reviewEntry.review, {
    headers: {
      'Content-Type': 'application/json',
    },
  });
  return response.data;
};

export const deleteReview = async (url: string): Promise<ReviewResponse> => {
  const response = await axios.delete(url, {
    headers: {
      'Content-Type': 'application/json',
    },
  });
  return response.data;
};
