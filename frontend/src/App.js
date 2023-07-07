import { ChakraProvider, theme } from '@chakra-ui/react';
import React from 'react';
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Admin from './Pages/Admin';
import ClubMembershipForm from './Pages/ClubMembershipForm';
import CreateEventForm from './Pages/CreateEventForm';
import Layout from './Pages/layout';
import NewClubRequest from './Pages/NewClubRequest';
import Subscribe from './Pages/Subscribe';

const router = createBrowserRouter(
  [
    {
      element: <Layout/>,
      children:[
        { 
        path: "/",
        element: <Admin />
        },
        {
          path: "/NewClubRequest",
          element: <NewClubRequest />
        },
        {
          path:"/subscribe",
          element: <Subscribe />
        },
        {
          path:"/clubmembershipform",
          element: <ClubMembershipForm />
        },
        {
          path:"/createeventform",
          element: <CreateEventForm />
        }
      ]
    }
  ]
);

function App() {
  return (
    <ChakraProvider theme={theme}>
        <RouterProvider router={router} />
    </ChakraProvider>
  );
}



export default App;
