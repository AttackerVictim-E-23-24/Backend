package com.ucab.cmcapp.implementation;

import com.google.firebase.messaging.FirebaseMessagingException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class HiloInamovilidadTest {

    private Object userService;
    private Object firebaseSender;

    @Test
    public void testConstructor() {
        int seconds = 10;
        String username = "testUser";
        HiloInamovilidad hilo = new HiloInamovilidad(seconds, username);
        assertEquals(seconds, hilo.seconds);
        assertEquals(username, hilo.username);
    }

    @Test
    public void testRun_ConditionTrue() throws InterruptedException {
        int seconds = 2;
        String username = "testUser";
        HiloInamovilidad hilo = new HiloInamovilidad(seconds, username) {

            boolean validarMovimiento(String username, int seconds) {
                return true; // Simulate condition being true
            }
        };

        hilo.start();
        hilo.join(); // Wait for the thread to finish

        // Verify that the thread runs for the specified seconds and then stops
        // (without sending a message)
        verifyNoInteractions(firebaseSender);
    }

}