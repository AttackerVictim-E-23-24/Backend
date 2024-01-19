package com.ucab.cmcapp.implementation;

import com.ucab.cmcapp.logic.dtos.UserDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GetUserByEmailTest {

    @Test
    void getUser_ReturnsUserDto_WhenEmailIsValid() {
        // Arrange
        UserService userService = new UserService();
        String email = "test@example.com";

        // Act
        UserDto result = userService.getUser(email);

        // Assert
        assertNotNull(result);
        assertEquals(UserDto.class, result.getClass());
    }

    @Test
    void getUser_ThrowsException_WhenEmailIsInvalid() {
        // Arrange
        UserService userService = new UserService();
        String email = "invalid_email";

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            userService.getUser(email);
        });
    }


}
