import React from 'react';
import { ReviewResponse } from '../types';

// interface ReviewProps {
//   reviews: ReviewResponse[];
// }

function Reviews({ reviews }: { reviews: ReviewResponse[] }) {
  if (reviews === undefined || reviews === null || reviews.length === 0) {
    return;
  }
  return (
    <>
      {reviews.map((review) => (
        <span key={review.pk}>{review.content}</span>
      ))}
    </>
  );
}

export default Reviews;
