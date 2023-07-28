import { Accordion, AccordionButton, AccordionIcon, AccordionItem, AccordionPanel, Box, Flex, Text, Toast } from '@chakra-ui/react';
import axios from 'axios';
import React, { useEffect, useState } from 'react';



function ManageClub() {

    const [newClubRequest, setNewClubRequest] = useState({
        name: "",
        firstName: "",
        lastName: "",
        emailID: "",
    });

    const [pendingClubRequest, setPendingClubRequest] = useState([{
        name: "Request #1"
    }, {
        name: "Request #2"
    },
    {
        name: "Request #3"
    }]);

    useEffect(() => {
        const fetchpendingclubs = async () => {
            try {
                const response = await axios.get(`/getpending`);
                const data = await response.data
                setPendingClubRequest(data)
            }
            catch (e) {
                console.error(e.message)
            }
        }
        fetchpendingclubs()

    }, [])


    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            const response = await axios.post(`/registerClub`, JSON.stringify(newClubRequest));
            if (response.status === 200) {
                Toast({
                    title: 'Successful',
                    description: 'Your submission was successful.',
                    status: 'success',
                    duration: 5000,
                    isClosable: true,
                });
            }
            return response.data;

        } catch (error) {
            console.log(error);
            return null;
        }
    }

    return (
        <Flex align="center" justify="start" direction='column' fontSize='2rem' h="90vh">
            <Flex alignItems="end" justifyContent="center" h="20vh" bgColor='black' width='100%' >
                <Flex h="18vh" bgColor='white' width='60%' alignItems='center' justifyContent='center'>
                    <Flex h='10vh' bgColor='#ECC94B' width='40%' justifyContent='center' alignItems='center'>
                        <Text fontSize='2xl' fontWeight='extrabold'> Manage Club </Text>
                    </Flex>
                </Flex>
            </Flex>
            <Flex direction='column' alignItems='center' mt='80px' width='60%'>
                {
                    pendingClubRequest.map((pendingClub) => {
                        return (<Accordion allowToggle width='90%'>
                            <AccordionItem>
                                <h2>
                                    <AccordionButton>
                                        <Box as="span" flex='1' textAlign='left'>
                                            {pendingClub.name}
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
                                        <Flex direction='row' justifyContent='space-between'>
                                            <Flex width='20%'>
                                                <Text fontSize='md'>Old Values</Text>
                                            </Flex>
                                            <Flex width='80%'>
                                                <Text fontSize='md'>Name: Dalhousie Greens</Text>
                                            </Flex>
                                        </Flex>
                                        <Flex direction='row' justifyContent='space-between'>
                                            <Flex width='20%'>
                                                <Text fontSize='md'>New Values:</Text>
                                            </Flex>
                                            <Flex width='80%'>
                                                <Text fontSize='md'>Dalhousie Environment</Text>
                                            </Flex>
                                        </Flex>
                                    </Flex>
                                </AccordionPanel>
                            </AccordionItem>


                        </Accordion>)

                    })
                }

            </Flex>
        </Flex>
    );
}

export default ManageClub;