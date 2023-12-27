import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App.tsx';
import { theme } from './assets/Styles/theme.ts';
import { ThemeProvider } from '@emotion/react';

ReactDOM.createRoot(document.getElementById('root')!).render(
  <ThemeProvider theme={theme}>
    <React.StrictMode>
      <App />
    </React.StrictMode>
  </ThemeProvider>
);
