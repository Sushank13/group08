import React from 'react';
import { NavLink, Outlet } from 'react-router-dom';
import { Flex, Box, Text } from '@chakra-ui/react';
import Navbar from '../Components/Navbar';

function LayoutNavbar() {
    return (
        <>
            <header> <Navbar /> </header>

            <Flex as="nav" align="center" justify="space-between" padding="1rem" bg="white" h="1vh" top="25%" left="0" right="0" color='#605c51'>
                <Box>
                    <NavLink to='/'>
                    <Text>About</Text>
                    </NavLink>
                </Box>
                <Box>
                    <NavLink to='/'>
                    <Text>Home</Text>
                    </NavLink>
                </Box>
                <Box>
                    <NavLink to='/FindClubs'>
                    <Text>Find Clubs</Text>
                    </NavLink>
                </Box>
                <Box>
                    <NavLink to='/FindEvents'>
                    <Text>Find Events</Text>
                    </NavLink>
                </Box>        
                <Box>
                    <NavLink to='/admin'>
                    <Text>Admin</Text>
                    </NavLink>
                </Box>            
            </Flex>

            <Outlet />

        </>
    );
}

export default LayoutNavbar;