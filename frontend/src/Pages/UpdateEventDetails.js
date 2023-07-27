import React, { useState } from 'react';
import { Box, Flex, Text, Input, Button, Checkbox, useToast } from '@chakra-ui/react';

function UpdateEventDetails() {
  const [organizerEmailID, setOrganizerEmailID] = useState('organizer@example.com');
  const [eventName, setEventName] = useState('Sample Event');
  const [description, setDescription] = useState('A sample event description');
  const [venue, setVenue] = useState('Sample Venue');
  const [image, setImage] = useState(''); // Assuming it's a file upload field

  const [updateEventID, setUpdateEventID] = useState(false);
  const [updateClubID, setUpdateClubID] = useState(false);
  const [updateOrganizerEmailID, setUpdateOrganizerEmailID] = useState(false);
  const [updateEventName, setUpdateEventName] = useState(false);
  const [updateDescription, setUpdateDescription] = useState(false);
  const [updateVenue, setUpdateVenue] = useState(false);
  const [updateImage, setUpdateImage] = useState(false);

  const [toastError, setToastError] = useState('');
  const toast = useToast();

  const handleUpdate = () => {
    let updated = false; // Flag to track if any field has been updated

    if (updateOrganizerEmailID && organizerEmailID === 'organizer@example.com') {
      setToastError('Please update Organizer Email ID');
    } else if (updateEventName && eventName === 'Sample Event') {
      setToastError('Please update Event Name');
    } else if (updateDescription && description === 'A sample event description') {
      setToastError('Please update Description');
    } else if (updateVenue && venue === 'Sample Venue') {
      setToastError('Please update Venue');
    } else if (updateImage && image === '') {
      setToastError('Please update Image');
    } else {
      // At least one field has been updated successfully
      updated = true;
      setToastError(''); // Clear the error message
    }

    if (updated) {
      toast({
        title: 'Update Successful',
        description: 'Event details updated successfully!',
        status: 'success',
        duration: 5000,
        isClosable: true,
      });

      // Implement the rest of the update logic here

      console.log('Organizer Email ID:', updateOrganizerEmailID ? organizerEmailID : 'Not Updated');
      console.log('Event Name:', updateEventName ? eventName : 'Not Updated');
      console.log('Description:', updateDescription ? description : 'Not Updated');
      console.log('Venue:', updateVenue ? venue : 'Not Updated');
      console.log('Image:', updateImage ? image : 'Not Updated');
    }
  };

  return (
    <Box position="relative">
      <img src="/formBackground.jpg" alt="" style={{ width: '100%', height: '400px', objectFit: 'cover' }} />
      <Box position="absolute" top="50px" left="50%" transform="translateX(-50%)" width="60%" bg="white" p="20px" rounded="md" h="100%">
        <Flex align="center" justify="center" h="100vh">
          <Box p="20px" bg="white" rounded="md">
            <Text fontSize="4xl" fontWeight="bold" textAlign="center" mb="10px" color="yellow.500">
              UPDATE EVENT DETAILS
            </Text>
            <Text fontSize="md" textAlign="center" mb="20px">
              Please select and update the values as required!
            </Text>

            
            <Box mt="10px">
              <Text fontSize="sm" mb="5px" color="yellow.500">
                Organizer Email ID:
              </Text>
              <Flex align="center">
                <Checkbox isChecked={updateOrganizerEmailID} onChange={(e) => setUpdateOrganizerEmailID(e.target.checked)}>
                  Update
                </Checkbox>
                <Input value={organizerEmailID} onChange={(e) => setOrganizerEmailID(e.target.value)} ml="10px" disabled={!updateOrganizerEmailID} />
              </Flex>
            </Box>

            <Box mt="10px">
              <Text fontSize="sm" mb="5px" color="yellow.500">
                Event Name:
              </Text>
              <Flex align="center">
                <Checkbox isChecked={updateEventName} onChange={(e) => setUpdateEventName(e.target.checked)}>
                  Update
                </Checkbox>
                <Input value={eventName} onChange={(e) => setEventName(e.target.value)} ml="10px" disabled={!updateEventName} />
              </Flex>
            </Box>

            <Box mt="10px">
              <Text fontSize="sm" mb="5px" color="yellow.500">
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
              <Text fontSize="sm" mb="5px" color="yellow.500">
                Venue:
              </Text>
              <Flex align="center">
                <Checkbox isChecked={updateVenue} onChange={(e) => setUpdateVenue(e.target.checked)}>
                  Update
                </Checkbox>
                <Input value={venue} onChange={(e) => setVenue(e.target.value)} ml="10px" disabled={!updateVenue} />
              </Flex>
            </Box>

            <Box mt="10px">
              <Text fontSize="sm" mb="5px" color="yellow.500">
                Image:
              </Text>
              <Flex align="center">
                <Checkbox isChecked={updateImage} onChange={(e) => setUpdateImage(e.target.checked)}>
                  Update
                </Checkbox>
                {/* Assuming it's a file input */}
                <Input type="file" onChange={(e) => setImage(e.target.files[0])} ml="10px" disabled={!updateImage} />
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
                Update Event Details
              </Button>
            </Box>
          </Box>
        </Flex>
      </Box>
    </Box>
  );
}

export default UpdateEventDetails;
