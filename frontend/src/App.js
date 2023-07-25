import React from 'react';
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import { ChakraProvider, theme } from '@chakra-ui/react';
import LayoutNavbar from './Pages/LayoutNavbar';
import Home from './Pages/Home';
import FindAllClubs from './Pages/FindAllClubs';
import ClubPage from './Pages/ClubPage';
import FindAllEvents from './Pages/FindAllEvents';
import EventPage from './Pages/EventPage';

const router = createBrowserRouter([
  {
    element: <LayoutNavbar />,
    children: [
      { 
        path: "/",
        element: <Home />
      },
      {
        path: "/FindClubs",
        element: <FindAllClubs />
      },
      {
        path: "/club/:clubName",
        element: <ClubPage />
      },
      {
        path: "/FindEvents",
        element: <FindAllEvents />
      },
      {
        path: "/event/:eventName",
        element: <EventPage />
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