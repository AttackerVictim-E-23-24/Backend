package com.ucab.cmcapp.implementation;

import com.ucab.cmcapp.logic.dtos.PersonaDto;
import com.ucab.cmcapp.logic.dtos.UserDto;
import com.ucab.cmcapp.logic.dtos.UserTypeDto;
import org.junit.jupiter.api.Test;

import javax.ws.rs.WebApplicationException;

import static com.ucab.cmcapp.implementation.PersonaService.addPersona;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddPersonaTest {

    @Test
    public void testAddPersonaWithValidData() {
        UserDto userDto = new UserDto();

        // Set the fields with specific values (replace with your actual data)
        userDto.setId(123L);                   // Example ID
        userDto.setUserName("johndoe");         // Example username
        userDto.setImei("354231098547549");    // Example IMEI
        userDto.setPassword("password123");    // Example password (consider secure storage)
        userDto.setEmail("johndoe@example.com"); // Example email

// Create a new PersonaDto object
        PersonaDto personaDto = new PersonaDto();

// Set the PersonaDto fields with specific values
        personaDto.set_id(123L);                 // Example ID
        personaDto.setNombre("Juan");             // Example name
        personaDto.setSeg_nombre("Antonio");       // Example second name
        personaDto.setApellido("Pérez");          // Example surname
        personaDto.setSeg_apellido("López");       // Example second surname
        personaDto.setCedula(123456789);         // Example ID card number
        personaDto.setFch_nac("2023-01-01");      // Example date of birth
        personaDto.setDireccion("Av. Bolívar, Caracas"); // Example address

        userDto.setDatosPersona(personaDto);

// Create a new UserTypeDto object
        UserTypeDto userTypeDto = new UserTypeDto();
        userTypeDto.setId(1L);
        userTypeDto.setName("ADMIN");

// Set the UserTypeDto object in the UserDto object
        userDto.setUserTypeDto(userTypeDto);

        // Set up userDto with valid data

        long response = addPersona(userDto);

        // Assert the response is not negative or zero
        assertTrue(response > 0);
    }

    @Test
    public void testAddPersonaWithNullData() {
        UserDto userDto = new UserDto();
        // Set up userDto with null data

        // Assert that an exception is thrown
        assertThrows(RuntimeException.class, () -> addPersona(userDto));
    }

    @Test
    public void testAddPersonaWithInvalidData() {
        UserDto userDto = new UserDto();
        // Set up userDto with invalid data

        // Assert that an exception is thrown
        assertThrows(RuntimeException.class, () -> addPersona(userDto));
    }


}