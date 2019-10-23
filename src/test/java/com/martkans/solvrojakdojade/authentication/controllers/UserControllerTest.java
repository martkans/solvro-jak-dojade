package com.martkans.solvrojakdojade.authentication.controllers;

import com.martkans.solvrojakdojade.authentication.mappers.UserMapper;
import com.martkans.solvrojakdojade.authentication.services.UserService;
import com.martkans.solvrojakdojade.exceptions.RestResponseEntityExceptionHandler;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {

    private static final int WANTED_NUMBER_OF_INVOCATIONS_CORRECT_DATA = 1;
    private static final int WANTED_NUMBER_OF_INVOCATIONS_UNCORRECT_DATA = 0;

    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        UserController userController = new UserController(userService, userMapper);

        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void registrationCorrect() throws Exception {
        String userJSON = "{\"username\": \"testUser\",\"password\": \"passw0rd\",\"email\": \"ala@makota.com\" }";

        mockMvc.perform(MockMvcRequestBuilders.post("/registration")
                .content(userJSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());


        verify(userService, times(WANTED_NUMBER_OF_INVOCATIONS_CORRECT_DATA)).save(any());
    }

    @Test
    public void registrationFailedTooShortUsername() throws Exception {
        String userJSON = "{\"username\": \"test\",\"password\": \"passw0rd\",\"email\": \"ala@makota.com\" }";

        mockMvc.perform(MockMvcRequestBuilders.post("/registration")
                .content(userJSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(userService, times(WANTED_NUMBER_OF_INVOCATIONS_UNCORRECT_DATA)).save(any());
    }

    @Test
    public void registrationFailedPasswordTooLong() throws Exception {
        String userJSON = "{\"username\": \"testUser\",\"password\": \"passw0rdpppppppppppppppppppppppppppppppppppppp\",\"email\": \"ala@makota.com\" }";

        mockMvc.perform(MockMvcRequestBuilders.post("/registration")
                .content(userJSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(userService, times(WANTED_NUMBER_OF_INVOCATIONS_UNCORRECT_DATA)).save(any());

    }
}