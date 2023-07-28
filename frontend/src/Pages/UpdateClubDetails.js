import React, { useState } from 'react';
import { Box, Flex, Text, Input, Button, Checkbox, useToast } from '@chakra-ui/react';

function UpdateClubDetails() {
  const [clubName, setClubName] = useState('Dal Club');
  const [clubPicture, setClubPicture] = useState(''); // Assuming it's a file upload field
  const [description, setDescription] = useState('A club for students');
  const [location, setLocation] = useState('Dalhousie University');
  const [meetingTimes, setMeetingTimes] = useState('Mondays at 5 PM');
  const [facebookLink, setFacebookLink] = useState('https://www.facebook.com/dalclub');
  const [instagramLink, setInstagramLink] = useState('https://www.instagram.com/dalclub');
  const [updateClubName, setUpdateClubName] = useState(false);
  const [updateClubPicture, setUpdateClubPicture] = useState(false);
  const [updateDescription, setUpdateDescription] = useState(false);
  const [updateLocation, setUpdateLocation] = useState(false);
  const [updateMeetingTimes, setUpdateMeetingTimes] = useState(false);
  const [updateFacebookLink, setUpdateFacebookLink] = useState(false);
  const [updateInstagramLink, setUpdateInstagramLink] = useState(false);

  const [toastError, setToastError] = useState('');
  const toast = useToast();

  const handleUpdate = () => {
    let updated = false; // Flag to track if any field has been updated

    if (updateClubName && clubName === 'Dal Club') {
      setToastError('Please update Club Name');
    } else if (updateClubPicture && clubPicture === '') {
      setToastError('Please update Club Picture');
    } else if (updateDescription && description === 'A club for students') {
      setToastError('Please update Description');
    } else if (updateLocation && location === 'Dalhousie University') {
      setToastError('Please update Location');
    } else if (updateMeetingTimes && meetingTimes === 'Mondays at 5 PM') {
      setToastError('Please update Meeting Times');
    } else if (updateFacebookLink && facebookLink === 'https://www.facebook.com/dalclub') {
      setToastError('Please update Facebook Link');
    } else if (updateInstagramLink && instagramLink === 'https://www.instagram.com/dalclub') {
      setToastError('Please update Instagram Link');
    } else {
      // At least one field has been updated successfully
      updated = true;
      setToastError(''); // Clear the error message
    }

    if (updated) {
      toast({
        title: 'Update Successful',
        description: 'Club details updated successfully!',
        status: 'success',
        duration: 5000,
        isClosable: true,
      });

      // Implement the rest of the update logic here

      console.log('Club Name:', updateClubName ? clubName : 'Not Updated');
      console.log('Club Picture:', updateClubPicture ? clubPicture : 'Not Updated');
      console.log('Description:', updateDescription ? description : 'Not Updated');
      console.log('Location:', updateLocation ? location : 'Not Updated');
      console.log('Meeting Times:', updateMeetingTimes ? meetingTimes : 'Not Updated');
      console.log('Facebook Link:', updateFacebookLink ? facebookLink : 'Not Updated');
      console.log('Instagram Link:', updateInstagramLink ? instagramLink : 'Not Updated');
    }
  };

  return (
    <Box position="relative">

        <img src="/formBackground.jpg" alt="" style={{ width: '100%', height: '400px', objectFit: 'cover' }} />
        <Box position="absolute" top="50px"  left="50%" transform="translateX(-50%)" width="60%" bg="white" p="20px" rounded="md" h="100%">
    <Flex align="center" justify="center" h="100vh">

      <Box p="20px" bg="white" rounded="md">
        <Text fontSize="4xl" fontWeight="bold" textAlign="center" mb="10px" color="yellow.500">
          UPDATE CLUB DETAILS
        </Text>
        <Text fontSize="md" textAlign="center" mb="20px">
          Please select and update the values as required!
        </Text>
  
        <Box>
          <Text fontSize="sm" mb="5px" color="yellow.500" >
            Club Name:
          </Text>
          <Flex align="center">
            <Checkbox isChecked={updateClubName} onChange={(e) => setUpdateClubName(e.target.checked)}>
              Update
            </Checkbox>
            <Input value={clubName} onChange={(e) => setClubName(e.target.value)} ml="10px" disabled={!updateClubName} />
          </Flex>
        </Box>
  
        <Box mt="10px">
          <Text fontSize="sm" mb="5px" color="yellow.500" >
            Club Picture:
          </Text>
          <Flex align="center">
            <Checkbox isChecked={updateClubPicture} onChange={(e) => setUpdateClubPicture(e.target.checked)}>
              Update
            </Checkbox>
            {/* Assuming it's a file input */}
            <Input type="file" onChange={(e) => setClubPicture(e.target.files[0])} ml="10px" disabled={!updateClubPicture} />
          </Flex>
        </Box>
  
        <Box mt="10px">
          <Text fontSize="sm" mb="5px" color="yellow.500" >
            Description:
          </Text>
          <Flex align="center">
            <Checkbox isChecked={updateDescription} onChange={(e) => setUpdateDescription(e.target.checked)}>
              Update
            </Checkbox>
            <Input value={description} onChange={(e) => setDescription(e.target.value)} ml="10px" disabled={!updateDescription} />
          </Flex>
        </Box>
  
        <Box mt="10px">
          <Text fontSize="sm" mb="5px" color="yellow.500" >
            Location:
          </Text>
          <Flex align="center">
            <Checkbox isChecked={updateLocation} onChange={(e) => setUpdateLocation(e.target.checked)}>
              Update
            </Checkbox>
            <Input value={location} onChange={(e) => setLocation(e.target.value)} ml="10px" disabled={!updateLocation} />
          </Flex>
        </Box>
  
        <Box mt="10px">
          <Text fontSize="sm" mb="5px" color="yellow.500" >
            Meeting Times:
          </Text>
          <Flex align="center">
            <Checkbox isChecked={updateMeetingTimes} onChange={(e) => setUpdateMeetingTimes(e.target.checked)}>
              Update
            </Checkbox>
            <Input value={meetingTimes} onChange={(e) => setMeetingTimes(e.target.value)} ml="10px" disabled={!updateMeetingTimes} />
          </Flex>
        </Box>
  
        <Box mt="10px">
          <Text fontSize="sm" mb="5px" color="yellow.500" >
            Facebook Link:
          </Text>
          <Flex align="center">
            <Checkbox isChecked={updateFacebookLink} onChange={(e) => setUpdateFacebookLink(e.target.checked)}>
              Update
            </Checkbox>
            <Input value={facebookLink} onChange={(e) => setFacebookLink(e.target.value)} ml="10px" disabled={!updateFacebookLink} />
          </Flex>
        </Box>
  
        <Box mt="10px">
          <Text fontSize="sm" mb="5px" color="yellow.500" >
            Instagram Link:
          </Text>
          <Flex align="center">
            <Checkbox isChecked={updateInstagramLink} onChange={(e) => setUpdateInstagramLink(e.target.checked)}>
              Update
            </Checkbox>
            <Input value={instagramLink} onChange={(e) => setInstagramLink(e.target.value)} ml="10px" disabled={!updateInstagramLink} />
          </Flex>
        </Box>
  
        {toastError && (
          <Box mt="10px" textAlign="center">
            <Text color="red.500" fontSize="sm">
              {toastError}
            </Text>
          </Box>
        )}
  
        <Box mt="20px">
          <Button colorScheme="yellow" onClick={handleUpdate} width="100%">
            Update Club Details
          </Button>
        </Box>
      </Box>
    </Flex>
  </Box>
  </Box>
  );
  
        }
export default UpdateClubDetails;
