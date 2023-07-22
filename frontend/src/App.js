import React from 'react';
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import { ChakraProvider, theme } from '@chakra-ui/react';
import LayoutNavbar from './Pages/LayoutNavbar';
import Home from './Pages/Home';

const router = createBrowserRouter([
  {
    element: <LayoutNavbar />,
    children: [
      { 
        path: "/",
        element: <Home />
      }
    ]
  }
]);

function App() {
  return (
    <ChakraProvider theme={theme}>
        <RouterProvider router={router} />
    </ChakraProvider>
  );
}

export default App;