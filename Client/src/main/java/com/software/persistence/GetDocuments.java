/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.software.persistence;

import com.software.entities.Documents;
import com.software.interfaces.IDocumentRepository;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author camm0
 */
public class GetDocuments implements IDocumentRepository {
    @Override
    public Documents getLoadedDocuments(String pathDocuments) {
        Map<String, File> documents = new HashMap<>();
        
        File folder = new File(pathDocuments);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    documents.put(file.getName(), file);
                }
            }
        }
        
        return new Documents(documents);
    }
}
