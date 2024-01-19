package com.ucab.cmcapp.common.util;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;

import java.io.IOException;
import java.io.InputStream;

public class FirebaseSender{

    public static void sendMessage(String Title, String Body, String token) throws IOException, FirebaseMessagingException {

        InputStream serviceAccount = FirebaseSender.class.getResourceAsStream("/firebase-adminsdk.json");

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        // Check if FirebaseApp is already initialized
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }

        // Setear el cuerpo de la notificación
        Message message = Message.builder()
                .setToken(token)
                .putData("key", "value") // Datos adicionales si los necesitas
                .setNotification(Notification.builder()
                        .setTitle(Title)
                        .setBody(Body)
                        .build())
                .build();

        // Enviar la notificación
        String response = FirebaseMessaging.getInstance().send(message);
        System.out.println("Mensaje enviado correctamente: " + response);
    }

}
