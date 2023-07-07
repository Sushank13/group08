import { Accordion, AccordionButton, AccordionIcon, AccordionItem, AccordionPanel, Box, Flex, Text } from '@chakra-ui/react';
import React from 'react';



function NewClubRequest() {
  return (
    <Flex align="center" justify="start" direction='column' fontSize='2rem' h="90vh">
      <Flex alignItems="end" justifyContent="center" h="20vh" bgColor='black' width='100%' >
        <Flex h="18vh" bgColor='white' width='60%' alignItems='center' justifyContent='center'>
          <Flex h='10vh' bgColor='#ECC94B' width='40%' justifyContent='center' alignItems='center'>
            <Text fontSize='2xl' fontWeight='extrabold'> New Club Requests</Text>
          </Flex>
        </Flex>
      </Flex>
      <Flex direction='column' alignItems='center' mt='80px' width='60%'>
        <Accordion allowToggle width='90%'>
          <AccordionItem>
            <h2>
              <AccordionButton>
                <Box as="span" flex='1' textAlign='left'>
                  Request #1: Dalhousie Greens
                </Box>
                <AccordionIcon />
              </AccordionButton>
            </h2>
            <AccordionPanel pb={4} >
              <Flex direction='column'>
                <Flex direction='row' justifyContent='space-between'>
                  <Flex width='20%'>
                    <Text fontSize='md'>Club Name:</Text>
                  </Flex>
                  <Flex width='80%'>
                    <Text fontSize='md'>Dalhousie Greens</Text>
                  </Flex>
                </Flex>
                <Flex direction='row' justifyContent='space-between'>
                  <Flex width='20%'>
                    <Text fontSize='md'>Club Description:</Text>
                  </Flex>
                  <Flex width='80%'>
                    <Text fontSize='md'>A Green values common interest society, pertaining to sustainability, social justice and grassroots democracy. 
                    Our society brings together people with common interest in green values for discussions and events pertaining to these values</Text>
                  </Flex>
                </Flex>
                <Flex direction='row' justifyContent='space-between'>
                  <Flex width='20%'>
                    <Text fontSize='md'>Requestor Name:</Text>
                  </Flex>
                  <Flex width='80%'>
                    <Text fontSize='md'>Preeti Sharma</Text>
                  </Flex>
                </Flex>
                <Flex direction='row' justifyContent='space-between'>
                  <Flex width='20%'>
                    <Text fontSize='md'>Requestor Email ID:</Text>
                  </Flex>
                  <Flex width='80%'>
                    <Text fontSize='md'>pr233584@dal.ca</Text>
                  </Flex>
                </Flex>
              </Flex>
            </AccordionPanel>
          </AccordionItem>

          <AccordionItem>
            <h2>
              <AccordionButton>
                <Box as="span" flex='1' textAlign='left'>
                  Section 2 title
                </Box>
                <AccordionIcon />
              </AccordionButton>
            </h2>
            <AccordionPanel pb={4}>
              Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
              tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
              veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
              commodo consequat.
            </AccordionPanel>
          </AccordionItem>
        </Accordion>
      </Flex>
    </Flex>
  );
}

export default NewClubRequest;