package com.martkans.solvrojakdojade.authentication.services;

import com.martkans.solvrojakdojade.authentication.domain.User;
import com.martkans.solvrojakdojade.authentication.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserDetailsServiceImplTest {

    public static final String USERNAME = "ala";
    public static final int WANTED_NUMBER_OF_INVOCATIONS = 1;
    @Mock
    private UserRepository userRepository;
    private UserDetailsServiceImpl userService;

    private User user;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        userService = new UserDetailsServiceImpl(userRepository);

        user = new User();
        user.setUsername(USERNAME);
        user.setPassword("passw0rd");
    }

    @Test
    public void loadUserByUsername() {
        when(userRepository.findByUsername(any()))
                .thenReturn(Optional.of(user));

        UserDetails userDetails = userService.loadUserByUsername(USERNAME);

        assertEquals(user.getUsername(), userDetails.getUsername());

        verify(userRepository, times(WANTED_NUMBER_OF_INVOCATIONS)).findByUsername(any());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsernameFailed() {
        when(userRepository.findByUsername(any()))
                .thenReturn(Optional.empty());

        userService.loadUserByUsername(USERNAME);
        verify(userRepository, times(WANTED_NUMBER_OF_INVOCATIONS)).findByUsername(any());
    }

    @Test
    public void save() {
        userService.save(user);
        verify(userRepository, times(WANTED_NUMBER_OF_INVOCATIONS)).save(any());
    }
}