package com.software.features;

import com.software.entities.Documents;
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
        String[] lineas = documents.split("\n");

        // Crear una nueva lista y agregar los elementos del arreglo
        List<String> lineList = new ArrayList<>();
        lineList.addAll(Arrays.asList(lineas));

        return lineList;
    }

    
    
    public DefaultListModel<String> loadDocumentsPrevLoaded(Map<String,File> documentList){
        DefaultListModel<String> model = new DefaultListModel<>();

        for (Map.Entry<String, File> entry : documentList.entrySet()) {
            model.addElement(entry.getKey());
        }
        return model; 
    }
    
    public File finDocumentName(String document, Documents documents){
        for (Map.Entry<String, File> entry : documents.getDocuments().entrySet()) {
            if(entry.getKey().equals(document)) return entry.getValue();
        }
        return null;
    }
    
}
