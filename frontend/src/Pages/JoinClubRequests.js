import React, { useState, useEffect } from 'react';
import { Box, Flex, Button, Input, Text } from '@chakra-ui/react';
import axios from '../axiosConfiguration';

function JoinClubRequests()
{
    const [joinClubrequestsData, setJoinClubRequestsData] = useState([]); 
    
    const  getAllJoinClubRequests = async () =>
    {
        try
        {
            const response = await axios.get('/unauthenticated/getAllJoinClubRequests/CLB_1/bikectr@dal.ca');
            setJoinClubRequestsData(response.data);
            console.log(response.data);

        }
        catch(error)
        {
            console.log(error);
        }
    };

    useEffect(() => 
    {
        getAllJoinClubRequests();
    }, []);

    return(
        <>
        <Box position="relative">
          <img src="dalBackground.png" alt="" style={{ width: '100%', height: '400px', objectFit: 'cover' }} />
          
          <Box position="absolute" top="50px"  left="50%" transform="translateX(-50%)" width="60%" bg="white" p="20px" rounded="md" h="100%">
            
            <Flex position='relative' bgColor='white' alignItems='center'  justifyContent='center'>
              <Flex bgColor={global.DalClubCommons.yellow} pl="1" pr="1">
                <Text fontSize='2xl' fontWeight='bold'>JOIN CLUB REQUESTS</Text>
              </Flex>
            </Flex>

            {joinClubrequestsData.map((joinClubRequest, index) => (
            <Box key={index} mt="20px" bg="white" p="20px" rounded="md">
              <Text fontSize="14px" color="rgba(26,26,26,.4)"> 
                {joinClubRequest.requestID}
              </Text>

             <Text fontSize="md" lineHeight="1.6em" margin="12px 0" color={global.DalClubCommons.textColor} >
                {joinClubRequest.requestorEmailID}
              </Text>
              <Text fontSize="md" lineHeight="1.6em" margin="12px 0" color={global.DalClubCommons.textColor} >
                {joinClubRequest.clubID}
              </Text>
              <Text fontSize="md" lineHeight="1.6em" margin="12px 0" color={global.DalClubCommons.textColor} >
                {joinClubRequest.joiningReason}
              </Text>
              <Text fontSize="md" lineHeight="1.6em" margin="12px 0" color={global.DalClubCommons.textColor} >
                {joinClubRequest.requestStatus}
              </Text>
             </Box>
          ))}
            </Box>
        </Box> 
     </>
    );
}
export default JoinClubRequests;