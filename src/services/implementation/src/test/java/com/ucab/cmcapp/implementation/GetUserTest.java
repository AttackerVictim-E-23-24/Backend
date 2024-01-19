package com.ucab.cmcapp.implementation;

import com.ucab.cmcapp.common.entities.User;
import com.ucab.cmcapp.logic.commands.user.composite.GetUserCommand;
import com.ucab.cmcapp.logic.dtos.UserDto;
import com.ucab.cmcapp.logic.mappers.AlertaMapper;
import org.junit.jupiter.api.Test;

import javax.ws.rs.WebApplicationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class GetUserTest {

    @Test
    public void testGetUser() throws Exception {
        // Test case 1: Valid user ID
        long userId1 = 123;
        User expectedUser1 = createUser(userId1);
        GetUserCommand expectedCommand1 = createGetUserCommand(expectedUser1);
        GetUserTest commandFactory;
        commandFactory = null;
        when(commandFactory.createGetUserCommand(expectedUser1)).thenReturn(expectedCommand1);
        when(expectedCommand1.getReturnParam()).thenReturn(expectedUser1);

        UserDto expectedResponse1 = createUserDto(expectedUser1);
        AlertaMapper userMapper = null;

        UserService userService = null;
        UserDto actualResponse1 = userService.getUser(userId1);

        assertEquals(expectedResponse1, actualResponse1);

        // Test case 2: Invalid user ID
        long userId2 = -1;

        assertThrows(WebApplicationException.class, () -> userService.getUser(userId2));

        // Test case 3: Exception during command execution
        long userId3 = 456;
        User expectedUser3 = createUser(userId3);
        GetUserCommand expectedCommand3 = createGetUserCommand(expectedUser3);
        when(commandFactory.createGetUserCommand(expectedUser3)).thenReturn(expectedCommand3);
        when(expectedCommand3.getReturnParam()).thenThrow(new Exception("Command execution exception"));

        assertThrows(WebApplicationException.class, () -> userService.getUser(userId3));
    }

    private User createUser(long userId) {
        User user = new User();
        user.setId(userId);
        // Set other properties
        return user;
    }

    private GetUserCommand createGetUserCommand(User user) {
        GetUserCommand command = new GetUserCommand(user);
        // Set other properties
        return command;
    }

    private UserDto createUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        // Set other properties
        return userDto;
    }
}
