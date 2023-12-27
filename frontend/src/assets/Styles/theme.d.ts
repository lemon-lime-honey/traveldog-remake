import { Theme, ThemeOptions } from '@mui/material/styles/createTheme';

declare module '@mui/material/styles' {
  interface Palette {
    custom: Palette['primary'];
  }
  interface PaletteColor {
    darker?: string;
  }
  interface SimplePaletteColorOptions {
    darker?: string;
  }
  interface PaletteOptions {
    custom?: PaletteOptions['primary'];
  }
  export function createTheme(options: ThemeOptions): Theme;
}

declare module '@mui/material/AppBar' {
  interface AppBarPropsColorOverrides {
    custom: true;
  }
}
