/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.software.views;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author camm0
 */

public class FtpClient {
    public static void main(String[] args) {
        String host = "127.0.0.1"; // Cambia esto por la dirección IP o el nombre de host del servidor
        int port = 5000; // Cambia esto por el número de puerto del servidor
        
        Request data = new Request();
        
        
        //Este ejemplo sirve para los tipos get y servicios:
        // list-users, list-documents
        
        /*data.setType("get");
        data.setService("list-users");

        Gson gson = new Gson();
        String jsonString = gson.toJson(data);

        try (Socket socket = new Socket(host, port)) {
            InputStream inputStream = socket.getInputStream();
            byte[] responseDataBytes = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(responseDataBytes)) != -1) {
                String responseJsonString = new String(responseDataBytes, 0, bytesRead, StandardCharsets.UTF_8);
                Response response = gson.fromJson(responseJsonString, Response.class);
                if(response.getStatus().equals("503")){
                    System.out.println(response.getData());
                    System.exit(1);
                }else{
                    break;
                }
            }
            
            for (int i = 0; i < responseDataBytes.length; i++) {
                responseDataBytes[i] = 0;
            }
            
            
            OutputStream outputStream = socket.getOutputStream();
            byte[] dataBytes = jsonString.getBytes("UTF-8");

            outputStream.write(dataBytes);
            System.out.println("JSON enviado correctamente al servidor.");
            
            
            while ((bytesRead = inputStream.read(responseDataBytes)) != -1) {
                String responseJsonString = new String(responseDataBytes, 0, bytesRead, StandardCharsets.UTF_8);
                Response response = gson.fromJson(responseJsonString, Response.class);
                System.out.println("Respuesta recibida del servidor:");
                System.out.println("Status: " + response.getStatus());
                System.out.println("Data: " + response.getData());
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        
        
        try (Socket socket = new Socket(host, port)) {
            
            InputStream inputStream = socket.getInputStream();
            byte[] responseDataBytes = new byte[1024];
            int bytesRead;
            Gson gson = new Gson();

            while ((bytesRead = inputStream.read(responseDataBytes)) != -1) {
                String responseJsonString = new String(responseDataBytes, 0, bytesRead, StandardCharsets.UTF_8);
                Response response = gson.fromJson(responseJsonString, Response.class);
                if(response.getStatus().equals("503")){
                    System.out.println(response.getData());
                    System.exit(1);
                }else{
                    break;
                }
            }
            
            
            String filePath = "./Documents/Apache-Kafka.pptx";
            try {
                File file = new File(filePath);
                
                InputStream fileInputStream = new FileInputStream(file);
        

                data.setType("post");
                data.setService("send-document");
                data.setBody(file.getName());

                String jsonString = gson.toJson(data);

                OutputStream outputStream = socket.getOutputStream();
                byte[] dataBytes = jsonString.getBytes("UTF-8");

                outputStream.write(dataBytes);
                System.out.println("JSON enviado correctamente al servidor.");

                while ((bytesRead = inputStream.read(responseDataBytes)) != -1) {
                    String responseJsonString = new String(responseDataBytes, 0, bytesRead, StandardCharsets.UTF_8);
                    Response response = gson.fromJson(responseJsonString, Response.class);

                    if(response.Status.equals("200")){
                        System.out.println(response.Data);
                        break;
                    }
                }


                byte[] buffer = new byte[1024];
                bytesRead = 0;
                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                System.out.println("Archivo enviado correctamente");

            } catch (Exception e) {
                System.out.println("archivo no encontrado");
                socket.close();
                System.exit(1);
            }
        } catch (IOException ex) {
            Logger.getLogger(FtpClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static class Request {
        private String type;
        private String service;
        private String body;

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
        
        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
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

