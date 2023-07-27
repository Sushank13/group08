import React, { useState } from 'react';
import { Box, Flex, Text, Input, Button, useToast } from '@chakra-ui/react';
import { useNavigate } from 'react-router-dom'; // Updated import here

const SignupPage = () => {
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [program, setProgram] = useState('');
  const [term, setTerm] = useState('');
  const [mobileNumber, setMobileNumber] = useState('');
  const [DOB, setDOB] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const toast = useToast();
  const navigate = useNavigate(); // Updated this line

  const handleSignup = () => {
    // Add your signup logic here, e.g., sending the signup data to the server.
    // For simplicity, this example only shows a toast message when the form is submitted.
    if (email) {
      toast({
        title: 'Successful',
        description: `You have signed up successfully with email: ${email}!`,
        status: 'success',
        duration: 5000,
        isClosable: true,
      });
      // Redirect to the homepage or any other page after successful signup.
      // navigate('/'); // Updated this line to use navigate instead of history.push
      console.log([email,password,firstName,lastName,program,term,mobileNumber,DOB]);
    } else {
      toast({
        title: 'Oops!',
        description: 'Please provide a valid email address.',
        status: 'error',
        duration: 5000,
        isClosable: true,
      });
    }
  };

  
  return (
    <>
      <Box position="relative" mt="50px">
      <img src="dalBackground.png" alt="" style={{ width: '100%', height: '400px', objectFit: 'cover' }} />

        <Flex position="absolute" direction="column" align="center" justify="center" height="35px">
          <Box width="60%" bg="white" p="20px" rounded="md">
            <Text fontSize="2xl" fontWeight="bold" mb="20px" textAlign="center">
              Please Enter the following details to become a member of Dal-Clubs 
            </Text>
            <Input
              placeholder="Enter your first name"
              value={firstName}
              onChange={(e) => setFirstName(e.target.value)}
              mb="20px"
            />
            <Input
              placeholder="Enter your last name"
              value={lastName}
              onChange={(e) => setLastName(e.target.value)}
              mb="20px"
            />
            <Input
              placeholder="Enter your program"
              value={program}
              onChange={(e) => setProgram(e.target.value)}
              mb="20px"
            />
            <Input
              placeholder="Enter your term"
              value={term}
              onChange={(e) => setTerm(e.target.value)}
              mb="20px"
            />
            <Input
              placeholder="Enter your Mobile Number"
              value={mobileNumber}
              onChange={(e) => setMobileNumber(e.target.value)}
              mb="20px"
            />
            <Input
              placeholder="Enter your Date of Birth"
              value={DOB}
              onChange={(e) => setDOB(e.target.value)}
              mb="20px"
            />
            <Input
              placeholder="Enter your email address"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              mb="20px"
            />
            <Input
              placeholder="Enter your password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              mb="20px"
            />
            <Button
              onClick={handleSignup}
              color="white"
              bg="black"
              width="100%"
              size="lg"
              disabled={!email}
            >
              Sign Up
            </Button>
          </Box>
        </Flex>
      </Box>
    </>
  );
};

export default SignupPage;
