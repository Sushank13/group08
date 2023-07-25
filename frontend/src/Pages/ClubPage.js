import React, { useState, useEffect } from 'react';
import axios from '../axiosConfiguration';
import { useParams } from 'react-router-dom';
import { Box, Flex, Text } from '@chakra-ui/react';

const fetchClubDetails = async (clubName) => {
  try {
    const response = await axios.get(`/unauthenticated/findClubByName/${clubName}`);
    return response.data[0];
  } catch (error) {
    console.log(error);
    return null;
  }
};

function ClubPage() {
  const { clubName } = useParams(); 
  const [clubDetails, setClubDetails] = useState([]);

  useEffect(() => {
    const getClubDetails = async () => {
      const details = await fetchClubDetails(clubName);
      setClubDetails(details);
    };
    getClubDetails();
  }, [clubName]); 

  return (
    <>
      <Box position="relative" height="20vh">
        <img src="/dalBackground.png" alt="" style={{ width: '100%', height: '100%', objectFit: 'cover' }} />

        <Flex position="absolute" top="50%" left="50%" transform="translate(-50%, -50%)">
          <Box bg="white" p="5px" textAlign="center">
            <Text fontSize="xl" fontWeight="bold">
              {clubDetails.clubName.toUpperCase()}
            </Text>
            
          </Box>
        </Flex>
        
        

      </Box>
    </>
  );
}

export default ClubPage;
