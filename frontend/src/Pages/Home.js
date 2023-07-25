import { Box } from '@chakra-ui/react';
import '../DalClubCommons';
import useAuth from "../hooks/useAuth";
import useLogout from '../hooks/useLogout';

import { useNavigate } from 'react-router-dom';
function Home() {
  const navigate = useNavigate();
  const { auth } = useAuth();
  console.log("home auth" + auth.user);
  const logout = useLogout();

    const signOut = async () => {
        await logout();
        navigate('/');
    }
  return (
    <>
      <Box position="relative">
        <img src="dalBackground.png" alt="" style={{ width: '100%', height: '400px', objectFit: 'cover' }} />
      </Box>
      <div className="flexGrow">
        <button onClick={signOut}>Sign Out</button>
      </div>
    </>
  );
}

export default Home;
