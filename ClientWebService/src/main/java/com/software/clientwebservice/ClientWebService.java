/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.software.clientwebservice;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Scanner;

/**
 *
 * @author camm0
 */
public class ClientWebService {

    public static void main(String[] args) {
        Scanner scanner;
        try {
            scanner = new Scanner(System.in);
        } catch (Exception e) {
            System.out.println("Fallo al cargar el scanner");
            return;
        }
        
        try { // Call Web Service Operation
            org.tempuri.DocumentService service = new org.tempuri.DocumentService();
            org.tempuri.DocumentServiceSoap port = service.getDocumentServiceSoap();
            while(true){
                System.out.println("Nombre del documento: ");
                String documentName = scanner.nextLine();
                documentName = documentName.replace("\n","").trim();
                byte[] bufferDocument = port.downLoadDocument(documentName);

                if(bufferDocument == null){
                    System.out.println("No existe el archivo");
                    System.out.println("Enter para continuar..");
                    scanner.nextLine();
                    continue;
                }

                File archivo = new File(documentName);

                // Guarda los bytes del archivo en disco
                try (FileOutputStream fos = new FileOutputStream(archivo)) {
                    fos.write(bufferDocument);
                }
                Desktop.getDesktop().open(archivo);
                System.out.println("Enter para continuar..");
                scanner.nextLine();
            }
        } catch (Exception ex) {
            System.out.println("Fallo al descargar y abrir el archivo");
        }
    }
}

