import React, { useState, useEffect } from 'react';
import axios from '../axiosConfiguration';
import { useParams } from 'react-router-dom';
import { Box, Flex, Text } from '@chakra-ui/react';

const fetchEventDetails = async (eventName) => {
  try {
    const response = await axios.get(`/unauthenticated/getEventDetails/${eventName}`);
    return response.data[0]; 
  } catch (error) {
    console.log(error);
    return null;
  }
};

function EventPage() {
  const { eventName } = useParams(); 
  const [eventDetails, setEventDetails] = useState([]);

  useEffect(() => {
    const getEventDetails = async () => {
      const details = await fetchEventDetails(eventName);
      setEventDetails(details);
    };
    getEventDetails();
  }, [eventName]); 

  return (
    <>
      <Box position="relative" height="20vh">
        <img src="/dalBackground.png" alt="" style={{ width: '100%', height: '100%', objectFit: 'cover' }} />

        <Flex position="absolute" top="50%" left="50%" transform="translate(-50%, -50%)">
          <Box bg="white" p="5px" textAlign="center" >
            <Text fontSize="xl" fontWeight="bold">
              {eventName.toUpperCase()}
            </Text>
          </Box>
        </Flex>
      <Box position="relative" top="50px" left="50%" transform="translateX(-50%)" width="60%" bg="white" p="20px"  rounded="md" h="100%" >
          <Box>
            <Text fontSize="xl">Event Name: {eventDetails.eventName}</Text>
            <Text fontSize="md">Event ID: {eventDetails.eventID}</Text>
            <Text fontSize="md">Club ID: {eventDetails.clubID}</Text>
            <Text fontSize="md">Description: {eventDetails.description}</Text>
            <Text fontSize="md">Start Date: {eventDetails.startDate}</Text>
            <Text fontSize="md">End Date: {eventDetails.endDate}</Text>
          </Box>
        </Box>
      </Box>
    </>
  );
}

export default EventPage;