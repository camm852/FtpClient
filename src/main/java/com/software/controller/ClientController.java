/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.software.controller;

import com.google.gson.Gson;
import com.software.entities.Response;
import com.software.entities.Request;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author camm0
 */
public class ClientController {

    //metodo para solicitar conexion con el servidor
    public Socket connectToServer(String port, String ip) {
        Request request = new Request();
        int serverPort = Integer.parseInt(port);
        Gson gson = new Gson();

        int socketTimeOutDefault = 0;
        int socketTimeOutReadDocument = 500;

        try {
            Socket socket = new Socket(ip, serverPort);
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            byte[] responseDataBytes = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(responseDataBytes)) != -1) {
                String responseJsonString = new String(responseDataBytes, 0, bytesRead, StandardCharsets.UTF_8);
                Response responseJson = gson.fromJson(responseJsonString, Response.class);

                System.out.println("Respuesta recibida del servidor:");
                System.out.println("Status: " + responseJson.getStatus());
                System.out.println("Data: " + responseJson.getData());

                if (responseJson.getStatus().equals("503")) {
                    System.out.println(responseJson.getData());
                    throw new Error();
                } else {
                    break;
                }
            }

            return socket;
        } catch (IOException e) {
            return null;
        }
    }

}
