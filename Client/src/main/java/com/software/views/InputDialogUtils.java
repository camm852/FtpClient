/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.software.views;

import javax.swing.JOptionPane;

/**
 *
 * @author monicag
 */
public class InputDialogUtils {
    
     public static void showConnectionSuccessDialog() {
        String message = "Conexión exitosa";
        String title = "Conexión exitosa";

        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showConnectionErrorDialog() {
        String message = "Error de conexión. No se pudo establecer la conexión.";
        String title = "Error de conexión";

        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }
    
    public void showCloseConnectionServer(){
        String message = "Error de conexión. Se ha perdido al conexion con el servidor";
        String title = "Error de conexión";
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }
    
}
