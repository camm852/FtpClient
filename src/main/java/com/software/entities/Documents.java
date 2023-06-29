/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.software.entities;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author camm0
 */
public class Documents {
    private Map<String, File> documents;
    
    public Documents(){
        this.documents = new HashMap<>();
    }
    
    public Documents(Map<String, File> listPlugins){
        this.documents = listPlugins;
    }
    
    public Map<String, File> getDocuments(){
        return this.documents;
    }
    
    public void setDocuments(Map<String, File> documents){
        this.documents = documents;
    }
    
}
