package com.ucab.cmcapp.implementation;

import com.ucab.cmcapp.logic.dtos.UserDto;
import org.junit.jupiter.api.Test;

import javax.ws.rs.WebApplicationException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GetUserByUsernameTest {

    @Test
    public void testGetUserByUsername_validUsername() {
        UserService userService = new UserService();
        UserDto result = userService.getUserByUsername("john");
        assertNotNull(result);
        // Add assertions to validate the result
    }

}
