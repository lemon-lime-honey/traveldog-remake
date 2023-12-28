import AppBar from '@mui/material/AppBar';
import Container from '@mui/material/Container';
import CssBaseline from '@mui/material/CssBaseline';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import Placelist from './place/Placelist';

const queryClient = new QueryClient();
function App() {
  return (
    <>
      <Container maxWidth="xl">
        <CssBaseline />
        <AppBar sx={{ mb: 2 }} position="static">
          <Toolbar>
            <Typography variant="h6">TravelDog</Typography>
          </Toolbar>
        </AppBar>
        <QueryClientProvider client={queryClient}>
          <Placelist />
        </QueryClientProvider>
      </Container>
    </>
  );
}

export default App;
