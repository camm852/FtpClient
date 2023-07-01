package com.software.features;

import com.software.entities.Documents;
import com.software.entities.User;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;

/**
 *
 * @author camm0
 */
public class ClientFeatures {
    
    public Socket connecToServer(String ip, int port){
        try {
            Socket socket = new Socket(ip, port);
            return socket;
        } catch (IOException e) {
            return null;
        }
    }
    
    public Documents loadDocument(Documents documents, File document){
        documents.getDocuments().put(document.getName(), document);
        return documents;
    }
    
    public List<String> stringDocumentsToList(String documents) {
        String[] lines = documents.split("\n");

        // Crear una nueva lista y agregar los elementos del arreglo
        List<String> lineList = new ArrayList<>();
        lineList.addAll(Arrays.asList(lines));

        return lineList;
    }
    
    public File findDocument(String pathDocument){
        File file = new File(pathDocument);
        System.out.println("archivo encontrado");
        System.out.println(file.getName());
        return file;
    }
    
    public String getNameItemListServer(String itemListName) {
        String[] lines = itemListName.split(" ");
        return lines[0];
    }
    
    public List<User> listUsers(String data){
        List<User> users = new ArrayList<>();
        String[] lines = data.split("\n");
        
        for(String line : lines){
            String[] info = line.split("-");
            users.add(new User(info[0], info[1], info[2]));
        }
        return users;
    }
    
    public DefaultListModel<String> loadDocumentsPrevLoaded(Map<String,File> documentList){
        DefaultListModel<String> model = new DefaultListModel<>();

        for (Map.Entry<String, File> entry : documentList.entrySet()) {
            model.addElement(entry.getKey());
        }
        return model; 
    }
    
    public File findDocumentName(String document, Documents documents){
        for (Map.Entry<String, File> entry : documents.getDocuments().entrySet()) {
            if(entry.getKey().equals(document)) return entry.getValue();
        }
        return null;
    }
    
}
