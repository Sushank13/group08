import { React } from 'react';
import { ChakraProvider, theme } from '@chakra-ui/react';
import LayoutNavbar from './Pages/LayoutNavbar';
import Home from './Pages/Home';
import FindAllClubs from './Pages/FindAllClubs';
import ClubPage from './Pages/ClubPage';
import Login from './Pages/LoginPage';
import RequireAuth from './Components/RequireAuth';
import { Routes, Route } from 'react-router-dom';
import Unauthorized from './Pages/Unauthorized';
import Missing from './Pages/Missing';
import AdminHomePage from './Pages/AdminHomePage';

const ROLES = {
  'member': 'member',
  'president': 'president',
  'admin': 'admin'
};

function App() {
  return (
    <ChakraProvider theme={theme}>
      <Routes>
        <Route path="/" element={<LayoutNavbar />}>
          {/* public routes */}
          <Route path="/" element={<Home />} />
          <Route path="login" element={<Login />} />
          <Route path="unauthorized" element={<Unauthorized />} />
          
          <Route path="FindClubs" element={<FindAllClubs />} />
          <Route path="club/:clubName" element={<ClubPage />} />

          {/* we want to protect these routes */}
          <Route element={<RequireAuth allowedRoles={[ROLES.admin]} />}>
            <Route path="admin" element={<AdminHomePage />} />
          </Route>

          {/* catch all */}
          <Route path="*" element={<Missing />} />
        </Route>
      </Routes>
    </ChakraProvider>
  );
}

export default App;