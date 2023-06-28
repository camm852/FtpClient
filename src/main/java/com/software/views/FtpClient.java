/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.software.views;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 *
 * @author camm0
 */

public class FtpClient {
    
    
    public static String menu(){
        Scanner scanner = new Scanner(System.in);
        
        int option = 0;
        while(true){
            System.out.println("1.Listar usuarios conectado");
            System.out.println("2.Listar documentos en el servidor");
            System.out.println("3.Descargar documento");
            System.out.println("4.Enviar documento");

            option = scanner.nextInt();

        
            switch (option) {
                case 1:
                    return "list-users";
                case 2:
                    return "list-documents";
                case 3:
                    return "get-document";
                case 4:
                    return "send-document";
                default:{
                    System.out.println("Elige una opcion correcta");
                }
            }
            scanner.nextLine();
        }
        
    }
    
    
    public static void main(String[] args) throws InterruptedException {
        String host = "192.168.1.109"; // Cambia esto por la dirección IP o el nombre de host del servidor
        //String host = "127.0.0.1";
        int port = 5000; // Cambia esto por el número de puerto del servidor
        
        Request request = new Request();
        Gson gson = new Gson();
        
        int socketTimeOutDefault = 0;
        int socketTimeOutReadDocument = 500;
        

        try (Socket socket = new Socket(host, port)) {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            
            byte[] responseDataBytes = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(responseDataBytes)) != -1) {
                String responseJsonString = new String(responseDataBytes, 0, bytesRead, StandardCharsets.UTF_8);
                Response responseJson = gson.fromJson(responseJsonString, Response.class);
                if(responseJson.getStatus().equals("503")){
                    System.out.println(responseJson.getData());
                    System.exit(1);
                }else{
                    break;
                }
            }
            
            while(true){
            
                String option = menu();
                
                if(option.contains("list")) {
                    
                    request.setType("get");
                    request.setService(option);
                    String jsonString = gson.toJson(request);
                    
                    byte[] dataBytes = jsonString.getBytes("UTF-8");

                    outputStream.write(dataBytes);
                    System.out.println("JSON enviado correctamente al servidor.");
                    
                }else if(option.equals(("get-document"))){
                    System.out.println("Descargar documento");
                    String nameDocument = "Apache-Kafka-192.168.137.53.pptx"; //Nombre del documento en el servidor
                    String filePath = "./Documents/" + nameDocument;

                    request.setType("get");
                    request.setService("get-document");
                    request.setBody(nameDocument);
                    String jsonString = gson.toJson(request);

                    byte[] dataBytes = jsonString.getBytes("UTF-8");
                    outputStream.write(dataBytes);
                    System.out.println("JSON enviado correctamente al servidor.");
                    
                    Response response = new Response();
                    
                    byte[] bufferDocument = new byte[1024];
                    
                    while ((bytesRead = inputStream.read(bufferDocument)) != -1) {
                        String responseJsonString = new String(bufferDocument, 0, bytesRead, StandardCharsets.UTF_8);
                        response = gson.fromJson(responseJsonString, Response.class);
                        break;
                    }

                    if(!response.getStatus().equals("200")) System.exit(1);
                    
                    FileOutputStream fileOutputStream = new FileOutputStream(filePath);

                    try {
                        socket.setSoTimeout(socketTimeOutReadDocument); // Establece el tiempo de espera inicial


                        byte[] buffer = new byte[1024];
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            System.out.println(bytesRead);
                            fileOutputStream.write(buffer, 0, bytesRead);
                        }
                    } catch (SocketTimeoutException e) {
                        System.out.println("Archivo guardado correctamente");
                        socket.setSoTimeout(socketTimeOutDefault);
                    } catch (IOException e) {
                        System.out.println("Fallo al guardar el archivo");
                    }finally{
                        fileOutputStream.close();
                    }
                }else{
                    
                    String filePath = "./Documents/Apache-Kafka.pptx"; //Documento guardado en la carpeta del cliente
                    //String filePath = "./Documents/cv.pdf";
                    
                    File file = new File(filePath);
                
                    InputStream fileInputStream = new FileInputStream(file);


                    request.setType("post");
                    request.setService("send-document");
                    request.setBody(file.getName());

                    String jsonString = gson.toJson(request);

                    byte[] requestJson = jsonString.getBytes("UTF-8");

                    outputStream.write(requestJson);
                    System.out.println("JSON enviado correctamente al servidor.");

                    
                    byte[] responseGetDocument = new byte[1024];
                    Response response = new Response();
                    
                    while ((bytesRead = inputStream.read(responseGetDocument)) != -1) {
                        String responseJsonString = new String(responseGetDocument, 0, bytesRead, StandardCharsets.UTF_8);
                        response = gson.fromJson(responseJsonString, Response.class);
                        break;
                    }

                    if(!response.status.equals("200")){
                        System.out.println(response.data);
                        continue;
                    }
                    System.out.println(response.data);
                    
                    byte[] buffer = new byte[1024];
                    bytesRead = 0;
                    while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                        System.out.println(bytesRead);
                        outputStream.write(buffer, 0, bytesRead);
                    }

                    System.out.println("Archivo enviado correctamente");
                }


                while ((bytesRead = inputStream.read(responseDataBytes)) != -1) {
                    String responseJsonString = new String(responseDataBytes, 0, bytesRead, StandardCharsets.UTF_8);
                    Response response = gson.fromJson(responseJsonString, Response.class);
                    System.out.println("Respuesta recibida del servidor:");
                    System.out.println("status: " + response.getStatus());
                    System.out.println("data: " + response.getData());
                    break;
                }
                System.out.println("Final");
            }
        } catch (JsonSyntaxException | IOException e) {
            System.out.println(e);
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
        private String status;
        private String data;

        public String getStatus() {
            return status;
        }

        public void setType(String status) {
            this.status = status;
        }

        public String getData() {
            return data;
        }

        public void setService(String data) {
            this.data = data;
        }
    }
    
    
}
