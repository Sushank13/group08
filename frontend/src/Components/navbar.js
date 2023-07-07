import { Box, Flex, Text, Icon, Img } from '@chakra-ui/react';
import { FaUser} from 'react-icons/fa';
import { BsQuestionCircle } from 'react-icons/bs';
import React from 'react';
import { NavLink } from 'react-router-dom';
import tunehub from '../Assets/TuneHub.png'


function Navbar() {
  return (

    <Flex
      as="nav"
      align="center"
      justify="space-between"
      padding="1rem"
      bg="#ECC94B"
      color="white"
      h="10vh"
    >
      <Box>
        <NavLink to='/'>
        <Text color='black' fontSize='2xl' fontWeight='extrabold'>DAL CLUB</Text>
        </NavLink>
      </Box>
      <Flex>
        <Box marginRight="1rem">
        <Icon as={BsQuestionCircle} color='blackAlpha.900' boxSize={6} />
        </Box>
        <Box>
        <Icon as={FaUser} color='blackAlpha.900' boxSize={6} />
        
        </Box>
      </Flex>
    </Flex>

  );
}

export default Navbar;
