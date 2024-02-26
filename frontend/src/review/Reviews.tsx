import React from 'react';
import { ReviewResponse } from '../types';
import { Box } from '@mui/material';

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
        <Box>
          <p key={review.pk}>{review.content}</p>
        </Box>
      ))}
    </>
  );
}

export default Reviews;
