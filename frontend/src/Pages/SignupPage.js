import React, { useState } from 'react';
import { Box, Flex, Text, Input, Button, useToast } from '@chakra-ui/react';
import { useNavigate } from 'react-router-dom';

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
  const navigate = useNavigate();

  const handleSignup = () => {
    // Perform input validation
    const emptyFields = [];
    if (!firstName) emptyFields.push('First Name');
    if (!lastName) emptyFields.push('Last Name');
    if (!program) emptyFields.push('Program');
    if (!term) emptyFields.push('Term');
    if (!mobileNumber) emptyFields.push('Mobile Number');
    if (!DOB) emptyFields.push('Date of Birth');
    if (!email) emptyFields.push('Email');
    if (!password) emptyFields.push('Password');

    if (emptyFields.length > 0) {
      toast({
        title: 'Error',
        description: `Please fill in the following fields: ${emptyFields.join(', ')}.`,
        status: 'error',
        duration: 5000,
        isClosable: true,
      });
      return;
    }

    // Email validation
    if (!validateEmail(email)) {
      toast({
        title: 'Invalid Email',
        description: 'Please provide a valid email address.',
        status: 'error',
        duration: 5000,
        isClosable: true,
      });
      return;
    }

    // Check password validation
    if (password.length < 8 || !/\d/.test(password)) {
      toast({
        title: 'Password Error',
        description: 'Password must be at least 8 characters long and contain at least one numeric value.',
        status: 'error',
        duration: 5000,
        isClosable: true,
      });
      return;
    }

    // Add your signup logic here, e.g., sending the signup data to the server.
    // For simplicity, this example only shows a toast message when the form is submitted.
    toast({
      title: 'Successful',
      description: `You have signed up successfully with email: ${email}!`,
      status: 'success',
      duration: 5000,
      isClosable: true,
    });
    // Redirect to the homepage or any other page after successful signup.
    // navigate('/'); // Updated this line to use navigate instead of history.push
    console.log([email, password, firstName, lastName, program, term, mobileNumber, DOB]);
  };

  // Email validation function
  const validateEmail = (email) => {
    const re = /\S+@\S+\.\S+/;
    return re.test(email);
  };

  return (
    <>
      <Box position="relative" mt="50px">
        <img src="dalBackground.png" alt="" style={{ width: '100%', height: '400px', objectFit: 'cover' }} />

        <Flex position="absolute" direction="column" align="center" justify="center" height="35px">
          <Box width="60%" bg="white" p="20px" rounded="md">
            <Text fontSize="2xl" fontWeight="bold" mb="20px" textAlign="center">
              Please Enter the following details to become a member.
            </Text>
            <Input
              placeholder="Enter your first name"
              value={firstName}
              onChange={(e) => setFirstName(e.target.value)}
              mb="20px"
              isInvalid={!firstName && firstName !== ''}
            />
            <Input
              placeholder="Enter your last name"
              value={lastName}
              onChange={(e) => setLastName(e.target.value)}
              mb="20px"
              isInvalid={!lastName && lastName !== ''}
            />
            <Input
              placeholder="Enter your program"
              value={program}
              onChange={(e) => setProgram(e.target.value)}
              mb="20px"
              isInvalid={!program && program !== ''}
            />
            <Input
              placeholder="Enter your term"
              value={term}
              onChange={(e) => setTerm(e.target.value)}
              mb="20px"
              isInvalid={!term && term !== ''}
            />
            <Input
              placeholder="Enter your Mobile Number"
              value={mobileNumber}
              onChange={(e) => setMobileNumber(e.target.value)}
              mb="20px"
              isInvalid={!mobileNumber && mobileNumber !== ''}
            />
            <Input
              placeholder="Enter your Date of Birth"
              value={DOB}
              onChange={(e) => setDOB(e.target.value)}
              mb="20px"
              isInvalid={!DOB && DOB !== ''}
            />
            <Input
              placeholder="Enter your email address"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              mb="20px"
              isInvalid={!email && email !== ''}
            />
            <Input
              type="password"
              placeholder="Enter your password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              mb="20px"
              isInvalid={password.length > 0 && (password.length < 8 || !/\d/.test(password))}
            />
            <Button
              onClick={handleSignup}
              color="white"
              bg="black"
              width="100%"
              size="lg"
              disabled={!email || !password || !firstName || !lastName || !program || !term || !mobileNumber || !DOB}
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
