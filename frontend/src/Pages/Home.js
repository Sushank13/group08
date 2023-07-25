import { Box } from '@chakra-ui/react';
import '../DalClubCommons';
import useAuth from "../hooks/useAuth";

function Home() {
  const { auth } = useAuth();
  console.log("home auth" + auth.user);
  return (
    <>
    <Box position="relative">
        <img src="dalBackground.png" alt="" style={{ width: '100%', height: '400px', objectFit: 'cover' }} />
        
        
      </Box>
    </>
  );
}

export default Home;
