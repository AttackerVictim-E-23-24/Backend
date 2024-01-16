package com.ucab.cmcapp.common.util;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseSender {

    public static void sendMessage(String titulo, String body, String token) {
        try {
            // Implementar el logger

            // Ruta al archivo de configuración descargado desde Firebase Console
            FileInputStream serviceAccount = new FileInputStream("ProyectoApiAAI\\src\\persistence\\dao\\src\\main\\resources\\testcmapp-b557b-firebase-adminsdk-ldte3-1eeac016dd");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);

            // Construir el mensaje de la notificación
            Message message = Message.builder()
                    .setToken(token)    // Token del dispositivo
                    .putData("key", "value") // Datos adicionales si los necesitas
                    .setNotification(Notification.builder()
                            .setTitle(titulo)
                            .setBody(body)
                            .build())
                    .build();

            // Enviar la notificación
            String response = FirebaseMessaging.getInstance().send(message);
            // Implementar el logger
        } catch (IOException e) {

            // Maneja el error de lectura del archivo aquí

        } catch (FirebaseMessagingException e) {

            // Maneja el error de envío de la notificación aquí

        } catch (Exception e) {

            // Maneja cualquier otro error inesperado aquí

        }
    }



}
