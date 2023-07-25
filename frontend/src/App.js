import React from 'react';
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import { ChakraProvider, theme } from '@chakra-ui/react';
import LayoutNavbar from './Pages/LayoutNavbar';
import Home from './Pages/Home';
import FindAllClubs from './Pages/FindAllClubs';
import ClubPage from './Pages/ClubPage';
import FindAllEvents from './Pages/FindAllEvents';
import EventPage from './Pages/EventPage';
import Admin from './Pages/Admin';
import NewClubRequest from './Pages/NewClubRequest';
import NewClubRequestForm from './Pages/NewClubRequestForm';
import ClubMembershipForm from './Pages/ClubMembershipForm';

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
      },
      {
        path: "/Admin",
        element: <Admin />
      },
      {
        path: "/newclubrequest",
        element: <NewClubRequest />
      },
      {
        path: "/newclubrequestform",
        element: <NewClubRequestForm />
      },
      {
        path: "/clubmembershipform",
        element: <ClubMembershipForm />
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
