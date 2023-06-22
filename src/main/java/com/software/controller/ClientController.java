/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.software.controller;

import com.google.gson.Gson;
import com.software.views.FtpClient;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author camm0
 */
public class ClientController {
    
    public Socket requestConnection(String ipAddress, int port) {
        FtpClient.Request request = new FtpClient.Request();
        Gson gson = new Gson();
        
        int socketTimeOutDefault = 0;
        int socketTimeOutReadDocument = 500;

        try (Socket socket = new Socket(ipAddress, port)) {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            
            
            byte[] responseDataBytes = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(responseDataBytes)) != -1) {
                String responseJsonString = new String(responseDataBytes, 0, bytesRead, StandardCharsets.UTF_8);
                FtpClient.Response responseJson = gson.fromJson(responseJsonString, FtpClient.Response.class);
                if(responseJson.getStatus().equals("503")){
                    System.out.println(responseJson.getData());
                    throw new Error();
                }else{
                    break;
                }
            }
            return socket;
        }catch(Exception ex){
            return null;
        }
    }
    
    public String requestGetUsers(Socket socketConnection){
        OutputStream outPutStream = socketConnection.getOutputStream();
        
        
        
        
    }
    
    
}
