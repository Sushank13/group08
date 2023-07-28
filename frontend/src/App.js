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
import PersistLogin from './Components/PersistLogin';
import FindAllEvents from './Pages/FindAllEvents';
import EventPage from './Pages/EventPage';
import Admin from './Pages/Admin';
import NewClubRequestForm from './Pages/NewClubRequestForm';
import ClubMembershipForm from './Pages/ClubMembershipForm';
import SignupPage from './Pages/SignupPage';
import CreateEventForm from './Pages/CreateEventForm';
import ReviewNewClubRequest from './Pages/ReviewNewClubRequest';
import ReviewClubUpdateRequest from './Pages/ReviewClubUpdateRequest';
import ManageClub from './Pages/ManageClub';
import RecommenderPage from './Pages/RecommenderPage';
import UpdateEventDetails from './Pages/UpdateEventDetails';
import UpdateClubDetails from './Pages/UpdateClubDetails';


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
          <Route path="admin" element={<Admin />} />

          <Route path="FindEvents" element={<FindAllEvents />} />
          <Route path="event/:eventName" element={<EventPage />} />

          <Route path="FindClubs" element={<FindAllClubs />} />
          <Route path="/admin/manageclub" element={<ManageClub />} />
          <Route path="club/:clubName" element={<ClubPage />} />

          <Route path="/admin/reviewnewclubrequest" element={<ReviewNewClubRequest />} />
          <Route path="/admin/reviewclubupdaterequest" element={<ReviewClubUpdateRequest />} />
          <Route path="newclubrequestform" element={<NewClubRequestForm />} />
          <Route path="clubmembershipform" element={<ClubMembershipForm />} />
          <Route path="signuppage" element={<SignupPage />} />
          <Route path="createeventform" element={<CreateEventForm />} />

          {/* <Route path="createeventform" element={<ClubMembershipForm />} /> */}

          <Route path="recommend" element={<RecommenderPage />} />
          <Route path="updateEvent/:eventName" element={<UpdateEventDetails />} />

          <Route path="club/:clubName" element={<ClubPage />} />
          <Route path="updateClub/:clubName" element={<UpdateClubDetails />} />

          {/* we want to protect these routes */}
          <Route element={<PersistLogin />}>
            <Route element={<RequireAuth allowedRoles={[ROLES.admin]} />}>
              <Route path="admin" element={<AdminHomePage />} />
            </Route>
          </Route>
          {/* catch all */}
          <Route path="*" element={<Missing />} />
        </Route>
      </Routes>
    </ChakraProvider>
  );
}

export default App;
