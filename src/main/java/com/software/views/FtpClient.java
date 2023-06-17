/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.software.views;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import com.google.gson.Gson;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author camm0
 */
public class FtpClient {
    public static void main(String[] args) {
        String host = "127.0.0.1"; // Cambia esto por la dirección IP o el nombre de host del servidor
        int port = 5000; // Cambia esto por el número de puerto del servidor
        
        Request data = new Request();
        data.setType("get");
        data.setService("list-users");

        Gson gson = new Gson();
        String jsonString = gson.toJson(data);

        try (Socket socket = new Socket(host, port)) {
            OutputStream outputStream = socket.getOutputStream();
            byte[] dataBytes = jsonString.getBytes("UTF-8");

            outputStream.write(dataBytes);
            System.out.println("JSON enviado correctamente al servidor.");
            
            
            InputStream inputStream = socket.getInputStream();
            byte[] responseDataBytes = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(responseDataBytes)) != -1) {
                String responseJsonString = new String(responseDataBytes, 0, bytesRead, StandardCharsets.UTF_8);
                Response response = gson.fromJson(responseJsonString, Response.class);
                System.out.println("Respuesta recibida del servidor:");
                System.out.println("Status: " + response.getStatus());
                System.out.println("Data: " + response.getData());
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Request {
        private String type;
        private String service;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }
    }
    
    private static class Response {
        private String Status;
        private String Data;

        public String getStatus() {
            return Status;
        }

        public void setType(String status) {
            this.Status = status;
        }

        public String getData() {
            return Data;
        }

        public void setService(String data) {
            this.Data = data;
        }
    }
}

