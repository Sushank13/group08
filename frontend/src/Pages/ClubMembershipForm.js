import { Button, Container, Flex, FormControl, FormLabel, Grid, HStack, Input, Text, useToast } from '@chakra-ui/react';
import React from 'react';
import { NavLink } from 'react-router-dom';



function ClubMembershipForm() {
    const toast = useToast();
    const handleSubmit = (event) => {
        event.preventDefault();
        toast({
            title: 'Successful',
            description: 'Your submission was successful.',
            status: 'success',
            duration: 5000,
            isClosable: true,
        });
    }
    return (
        <Flex align="center" justify="start" direction='column' fontSize='2rem' h="90vh">
            <Flex alignItems="end" justifyContent="center" h="20vh" bgColor='black' width='100%' >
                <Flex h="18vh" bgColor='white' width='60%' alignItems='center' justifyContent='center'>
                    <Flex h='10vh' bgColor='#ECC94B' width='40%' justifyContent='center' alignItems='center'>
                        <Text fontSize='2xl' fontWeight='extrabold'>CLUB MEMBERSHIP FORM</Text>
                    </Flex>
                </Flex>
            </Flex>
            <Flex direction='column' mt='20px'>
            <Text fontSize='md' fontWeight='bold'>Please enter the below details to become a club member</Text>
            <Flex align="center" justify="start" mt='40px' direction='row' fontSize='2rem'>
                <form onSubmit={handleSubmit}>
                    <Flex justify='center' alignItems="center">
                        <FormControl isRequired mt="8px">
                            <FormLabel>Club Name</FormLabel>
                        </FormControl>
                        <FormControl isRequired>
                            <Input width='320' type="email" placeholder="Enter the club name" />
                        </FormControl>
                    </Flex>
                    <Flex justify='center' alignItems="center">
                        <FormControl isRequired mt="8px">
                            <FormLabel>First Name</FormLabel>
                        </FormControl>
                        <FormControl isRequired>
                            <Input width='320' type="email" placeholder="Enter First Name" />
                        </FormControl>
                    </Flex>
                    <Flex justify='center' alignItems="center">
                        <FormControl isRequired mt="8px">
                            <FormLabel>Last Name</FormLabel>
                        </FormControl>
                        <FormControl isRequired>
                            <Input width='320' type="email" placeholder="Enter Last Name" />
                        </FormControl>
                    </Flex>
                    <Flex justify='center' alignItems="center">
                        <FormControl isRequired mt="8px">
                            <FormLabel>Email ID</FormLabel>
                        </FormControl>
                        <FormControl isRequired>
                            <Input width='320' type="email" placeholder="Enter your email" />
                        </FormControl>
                    </Flex>
                    <Button mt='40px' type="submit" onSubmit={handleSubmit}>Submit</Button>
                </form>
            </Flex>

            </Flex>
        </Flex>
    );
}

export default ClubMembershipForm;