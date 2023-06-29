/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.software.interfaces;

import com.software.entities.Documents;

/**
 *
 * @author camm0
 */
public interface IDocumentRepository {
    Documents getLoadedDocuments(String pathPlugins);
}
