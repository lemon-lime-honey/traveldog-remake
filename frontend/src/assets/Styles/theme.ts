import { createTheme } from '@mui/material/styles';
import { indigo } from '@mui/material/colors';

export const theme = createTheme({
  palette: {
    primary: {
      light: indigo[100],
      main: indigo[300],
      dark: indigo[500],
      darker: indigo[700],
    },
  },
  typography: {
    fontFamily: [
      'IM_Hyemin-Bold',
      '-apple-system',
      'BlinkMacSystemFont',
      '"Segoe UI"',
      'Roboto',
      '"Helvetica Neue"',
      'Arial',
      'sans-serif',
      '"Apple Color Emoji"',
      '"Segoe UI Emoji"',
      '"Segoe UI Symbol"',
    ].join(','),
  },
});
