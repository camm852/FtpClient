package com.software.controller;

import com.google.gson.Gson;
import com.software.entities.Documents;
import com.software.entities.Response;
import com.software.entities.Request;
import com.software.entities.User;
import com.software.features.ClientFeatures;
import com.software.persistence.GetDocuments;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author camm0
 */
public class ClientController {

    private final ClientFeatures clientFeatures;
    private Documents localDocuments;
    private List<String> serverDocuments;
    private JList documentsList;
    String pathDocuments;
    
    public ClientController(){
        clientFeatures = new ClientFeatures();
        localDocuments = new Documents();
        serverDocuments = new ArrayList<>();
        pathDocuments = "";
    }
    
    public void setPathDocuments(String pathDocuments){
        this.pathDocuments = pathDocuments;
        Path directory = Paths.get(pathDocuments);
        if(!Files.exists(directory)){
            try {
                Files.createDirectories(directory);
            } catch (IOException ex) {
                System.out.println("Fallo al crear el directorio");
                System.exit(1);
            }
        }
    }
    
    private void setLocalDocuments(Documents documents){
        this.localDocuments = documents;
        
    }
    
    private void setJListLocalDocuments(JList documentList){
        this.documentsList = documentList;
    }
    
    
     //metodo para solicitar conexion con el servidor
    public Socket connectToServer(String port, String ip) {
        Request request = new Request();
        int serverPort = Integer.parseInt(port);
        Gson gson = new Gson();

        try {
            Socket socket = clientFeatures.connecToServer(ip, serverPort);
            if(socket == null) return null;
            InputStream inputStream = socket.getInputStream();

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
    
    private void refreshJListLocalDocuments(){
        DefaultListModel<String> model = new DefaultListModel<>();

        for (Map.Entry<String, File> entry : this.localDocuments.getDocuments().entrySet()) {
            model.addElement(entry.getKey());
        }
        this.documentsList.setModel(model);
    }
    
    public boolean loadDocument(File document){
        setLocalDocuments(clientFeatures.loadDocument(localDocuments, document));
        Path sourcePath = document.toPath();
        Path destinationPath = Paths.get(pathDocuments, document.getName());
        try {
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            refreshJListLocalDocuments();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
    
    public boolean SaveDocument(Socket socketConnection, String nameDocument) throws SocketException, IOException{
        FileOutputStream fileOutputStream = null;
        boolean isSaved = false;
        try {
            fileOutputStream = new FileOutputStream(pathDocuments+nameDocument);
            InputStream inputStream = socketConnection.getInputStream();

            socketConnection.setSoTimeout(500); // Establece el tiempo de espera inicial

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                System.out.println(bytesRead);
                fileOutputStream.write(buffer, 0, bytesRead);
            }
        } catch (SocketTimeoutException e) {
            System.out.println("Archivo guardado correctamente");
            socketConnection.setSoTimeout(0);
            isSaved = true;
            loadDocument(clientFeatures.findDocument(pathDocuments+nameDocument));
        } catch (IOException e) {
            System.out.println("Fallo al guardar el archivo");
            isSaved = false;
        }finally{
            fileOutputStream.close();
            return isSaved;
        }
    }
    
    public List<User> getListUsers(String data){
        return clientFeatures.listUsers(data);
    }
    
    public String getNameServerDocument(String itemListName){
        return clientFeatures.getNameItemListServer(itemListName);
    }
    
    public void refreshServerDocuments(JList serverDocumentsJList, String documents){
        this.serverDocuments = clientFeatures.stringDocumentsToList(documents);
        DefaultListModel<String> model = new DefaultListModel<>();

        for (String document : this.serverDocuments) {
            model.addElement(document);
        }
        serverDocumentsJList.setModel(model);
    }
    
    public File getLocalDocument(String documentName){
        return clientFeatures.findDocumentName(documentName, localDocuments);
    }
    
    public void documentsPrevLoad(JList documentList){
        Documents documents = new GetDocuments().getLoadedDocuments(pathDocuments);
        setLocalDocuments(documents);
        setJListLocalDocuments(documentList);
        DefaultListModel<String> model = this.clientFeatures.loadDocumentsPrevLoaded(documents.getDocuments());
        documentList.setModel(model);
    }
    

}
