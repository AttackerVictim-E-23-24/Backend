package com.ucab.cmcapp.implementation;

import com.ucab.cmcapp.common.util.FirebaseSender;

import java.util.concurrent.TimeUnit;

public class HiloInamovilidad extends Thread {

    private int seconds;
    private boolean condition;

    private String username;

    private UserService userService = new UserService();

    private static String tokenAdmin = "Prueba";

    public HiloInamovilidad(int seconds, String username) {
        this.seconds = seconds;
        this.username = username;
    }

    @Override
    public void run() {

        for (int i = seconds; i >= 0; i--) {

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }

            if (i == 0) {

                // La cuenta ha llegado a 0, se deberia validar si el usuario se ha movido en los segundos que respecta la validacion
                // Llamo a la funcion en UserService
                condition = userService.validarMovimiento(username, seconds);

                if( !condition) {
                    FirebaseSender.sendMessage("Alerta - Este usuario ha superado el tiempo que puede estar sin moverse", "El siguiente usario ha superado el tiempo que puede estar sin moverse: " + username, tokenAdmin);
                }

                //Vuelvo a correr el hilo
                run();

            }
        }
    }
}