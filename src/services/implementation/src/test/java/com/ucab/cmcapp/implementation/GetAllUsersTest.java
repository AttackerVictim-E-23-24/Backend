package com.ucab.cmcapp.implementation;

import com.ucab.cmcapp.common.util.ServiceResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GetAllUsersTest {

    @Test
    public void testGetAllUsers_Success() {
        // Arrange
        UserService userService = new UserService();

        // Act
        ServiceResponse result = userService.getAllUsers();

        // Assert
        assertTrue(result.isStatus());
        assertNotNull(result.getRespuesta());
        assertEquals("Se obtuvo la lista de usuarios", result.getMensaje());
    }

    @Test
    public void testGetAllUsers_ErrorResponse() {
        // Arrange
        UserService userService = new UserService();
        // Simulate an error response from the command
        Object CommandFactory = null;
        // Act
        ServiceResponse result = userService.getAllUsers();

        // Assert
        assertFalse(result.isStatus());
        assertNull(result.getRespuesta());
        assertEquals("No se podido obtener la lista de usuarios", result.getMensaje());
    }
}
