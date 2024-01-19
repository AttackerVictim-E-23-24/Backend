package com.ucab.cmcapp.implementation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GetPersonaTest {

    @Test
    void testGetPersona() {
        // Test case 1: cedula is a valid value
        long cedula1 = 28443153;
        long expectedResponse1 = 1; // Assuming the expected response is 1
        long actualResponse1 = PersonaService.getPersona(cedula1);
        assertEquals(expectedResponse1, actualResponse1);

        // Test case 2: cedula is a negative value
        long cedula2 = -123456789;
        long expectedResponse2 = -1; // Assuming the expected response is -1
        long actualResponse2 = PersonaService.getPersona(cedula2);
        assertEquals(expectedResponse2, actualResponse2);

        // Test case 3: cedula is zero
        long cedula3 = 0;
        long expectedResponse3 = 0; // Assuming the expected response is 0
        long actualResponse3 = PersonaService.getPersona(cedula3);
        assertEquals(expectedResponse3, actualResponse3);

        // Test case 4: cedula is a large value
        long cedula4 = Long.MAX_VALUE;
        long expectedResponse4 = Long.MAX_VALUE; // Assuming the expected response is Long.MAX_VALUE
        long actualResponse4 = PersonaService.getPersona(cedula4);
        assertEquals(expectedResponse4, actualResponse4);
    }
}
