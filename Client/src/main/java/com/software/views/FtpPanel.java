/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.software.views;

import com.software.entities.Request;
import com.software.entities.Response;
import java.awt.FileDialog;
import java.net.Socket;

import java.io.OutputStream;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.software.controller.ClientController;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author monicag
 */
public class FtpPanel extends javax.swing.JFrame {

    Gson gson = new Gson();
    private Request request;
    private Response response;
    private Socket socketConnection; 
    private ClientController clientController;
    private String pathDocuments;
    private String localDocumentSelected;
    private String serverDocumentSelected;
    private int xMouse, yMouse; // coords screen
    private InputDialogUtils showInputDialog;
    
    // Constructor que acepta un Socket
    public FtpPanel(Socket socket, ClientController clientController) {
        initComponents();

        socketConnection = socket;
        pathDocuments = "./Documents/";
        localDocumentSelected = "";
        serverDocumentSelected = "";
        this.clientController = clientController;
        
        showInputDialog = new InputDialogUtils();
        
        clientController.setPathDocuments(pathDocuments);
        clientController.documentsPrevLoad(ClientDocumentList);
        
        request = new Request();
        response = new Response();
        
        
        EmptyBorder emptyBorder = new EmptyBorder(0, 0, 0, 0);
        this.ServerDocumentsList.setBorder(emptyBorder);
        this.ClientDocumentList.setBorder(emptyBorder);
        this.ScrollServerDocumentsList.setBorder(null);
        this.ScrollClientDocumentsList.setBorder(null);
        
    }

    // Constructor sin parámetros
    public FtpPanel() {
        initComponents();
        
        pathDocuments = "./Documents/";
        localDocumentSelected = "";
        serverDocumentSelected = "";
        
        request = new Request();
        response = new Response();
        
        EmptyBorder emptyBorder = new EmptyBorder(0, 0, 0, 0);
        this.ServerDocumentsList.setBorder(emptyBorder);
        this.ClientDocumentList.setBorder(emptyBorder);
        this.ScrollServerDocumentsList.setBorder(null);
        this.ScrollClientDocumentsList.setBorder(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SeverPanel = new javax.swing.JPanel();
        ScrollServerDocumentsList = new javax.swing.JScrollPane();
        ServerDocumentsList = new javax.swing.JList<>();
        FtpTitle = new javax.swing.JLabel();
        ButtonRefresh = new javax.swing.JLabel();
        LabelServerDocumentList = new javax.swing.JLabel();
        LabelDownloadDocument = new javax.swing.JLabel();
        LabelListUsers = new javax.swing.JLabel();
        ClientPanel = new javax.swing.JPanel();
        ServerTitle = new javax.swing.JLabel();
        ScrollClientDocumentsList = new javax.swing.JScrollPane();
        ClientDocumentList = new javax.swing.JList<>();
        LabelServerDocumentList1 = new javax.swing.JLabel();
        LabelLoadDocument = new javax.swing.JLabel();
        LabelSendDocument = new javax.swing.JLabel();
        MessagesPanel = new javax.swing.JPanel();
        TopBarPanel = new javax.swing.JPanel();
        CloseWindow = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cliente");
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SeverPanel.setBackground(new java.awt.Color(30, 174, 255));
        SeverPanel.setFocusTraversalPolicyProvider(true);
        SeverPanel.setPreferredSize(new java.awt.Dimension(372, 509));

        ServerDocumentsList.setSelectionBackground(new java.awt.Color(30, 174, 255));
        ServerDocumentsList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                ServerDocumentsListValueChanged(evt);
            }
        });
        ScrollServerDocumentsList.setViewportView(ServerDocumentsList);

        FtpTitle.setFont(new java.awt.Font("Calisto MT", 1, 48)); // NOI18N
        FtpTitle.setForeground(new java.awt.Color(255, 255, 255));
        FtpTitle.setText("FTP");

        ButtonRefresh.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        ButtonRefresh.setForeground(new java.awt.Color(255, 255, 255));
        ButtonRefresh.setIcon(new javax.swing.ImageIcon("C:\\Users\\camm0\\Documents\\NetBeansProjects\\FtpClient\\Icons\\refresh-button.png")); // NOI18N
        ButtonRefresh.setText("Recargar");
        ButtonRefresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ButtonRefresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonRefreshMouseClicked(evt);
            }
        });

        LabelServerDocumentList.setFont(new java.awt.Font("Cambria", 3, 18)); // NOI18N
        LabelServerDocumentList.setForeground(new java.awt.Color(255, 255, 255));
        LabelServerDocumentList.setText("Documentos en el servidor");

        LabelDownloadDocument.setFont(new java.awt.Font("Cambria", 1, 16)); // NOI18N
        LabelDownloadDocument.setForeground(new java.awt.Color(255, 255, 255));
        LabelDownloadDocument.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelDownloadDocument.setIcon(new javax.swing.ImageIcon("C:\\Users\\camm0\\Documents\\NetBeansProjects\\FtpClient\\Icons\\download.png")); // NOI18N
        LabelDownloadDocument.setText("Descargar Documento");
        LabelDownloadDocument.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LabelDownloadDocument.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LabelDownloadDocumentMouseClicked(evt);
            }
        });

        LabelListUsers.setFont(new java.awt.Font("Cambria", 1, 16)); // NOI18N
        LabelListUsers.setForeground(new java.awt.Color(255, 255, 255));
        LabelListUsers.setIcon(new javax.swing.ImageIcon("C:\\Users\\camm0\\Documents\\NetBeansProjects\\FtpClient\\Icons\\listar.png")); // NOI18N
        LabelListUsers.setText("Listar Usuarios");
        LabelListUsers.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LabelListUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LabelListUsersMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout SeverPanelLayout = new javax.swing.GroupLayout(SeverPanel);
        SeverPanel.setLayout(SeverPanelLayout);
        SeverPanelLayout.setHorizontalGroup(
            SeverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SeverPanelLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(SeverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FtpTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SeverPanelLayout.createSequentialGroup()
                        .addGroup(SeverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(SeverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(SeverPanelLayout.createSequentialGroup()
                                    .addComponent(LabelDownloadDocument)
                                    .addGap(12, 12, 12)
                                    .addComponent(LabelListUsers))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SeverPanelLayout.createSequentialGroup()
                                    .addComponent(LabelServerDocumentList)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ButtonRefresh)))
                            .addComponent(ScrollServerDocumentsList, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24))))
        );
        SeverPanelLayout.setVerticalGroup(
            SeverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SeverPanelLayout.createSequentialGroup()
                .addComponent(FtpTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(7, 7, 7)
                .addGroup(SeverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonRefresh)
                    .addComponent(LabelServerDocumentList))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ScrollServerDocumentsList, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SeverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelDownloadDocument, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelListUsers))
                .addGap(237, 237, 237))
        );

        getContentPane().add(SeverPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 390, 490));

        ClientPanel.setBackground(new java.awt.Color(255, 255, 255));
        ClientPanel.setPreferredSize(new java.awt.Dimension(372, 509));

        ServerTitle.setFont(new java.awt.Font("Calisto MT", 1, 48)); // NOI18N
        ServerTitle.setForeground(new java.awt.Color(30, 174, 255));
        ServerTitle.setText("SERVER");

        ClientDocumentList.setBackground(new java.awt.Color(250, 250, 250));
        ClientDocumentList.setSelectionBackground(new java.awt.Color(30, 174, 255));
        ClientDocumentList.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ClientDocumentListPropertyChange(evt);
            }
        });
        ClientDocumentList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                ClientDocumentListValueChanged(evt);
            }
        });
        ScrollClientDocumentsList.setViewportView(ClientDocumentList);

        LabelServerDocumentList1.setFont(new java.awt.Font("Cambria", 3, 18)); // NOI18N
        LabelServerDocumentList1.setText("Documentos locales");

        LabelLoadDocument.setFont(new java.awt.Font("Cambria", 1, 16)); // NOI18N
        LabelLoadDocument.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelLoadDocument.setIcon(new javax.swing.ImageIcon("C:\\Users\\camm0\\Documents\\NetBeansProjects\\FtpClient\\Icons\\subir.png")); // NOI18N
        LabelLoadDocument.setText("Cargar Documento");
        LabelLoadDocument.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LabelLoadDocument.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LabelLoadDocumentMouseClicked(evt);
            }
        });

        LabelSendDocument.setFont(new java.awt.Font("Cambria", 1, 16)); // NOI18N
        LabelSendDocument.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelSendDocument.setIcon(new javax.swing.ImageIcon("C:\\Users\\camm0\\Documents\\NetBeansProjects\\FtpClient\\Icons\\send.png")); // NOI18N
        LabelSendDocument.setText("Enviar Documento");
        LabelSendDocument.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LabelSendDocument.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LabelSendDocumentMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout ClientPanelLayout = new javax.swing.GroupLayout(ClientPanel);
        ClientPanel.setLayout(ClientPanelLayout);
        ClientPanelLayout.setHorizontalGroup(
            ClientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ClientPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ClientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ClientPanelLayout.createSequentialGroup()
                        .addComponent(ServerTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(ClientPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(ClientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ClientPanelLayout.createSequentialGroup()
                                .addComponent(LabelLoadDocument)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                                .addComponent(LabelSendDocument)
                                .addGap(22, 22, 22))
                            .addGroup(ClientPanelLayout.createSequentialGroup()
                                .addGroup(ClientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ScrollClientDocumentsList, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(LabelServerDocumentList1))
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );
        ClientPanelLayout.setVerticalGroup(
            ClientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ClientPanelLayout.createSequentialGroup()
                .addComponent(ServerTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LabelServerDocumentList1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ScrollClientDocumentsList, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ClientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelSendDocument, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelLoadDocument, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        getContentPane().add(ClientPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 400, 490));

        MessagesPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout MessagesPanelLayout = new javax.swing.GroupLayout(MessagesPanel);
        MessagesPanel.setLayout(MessagesPanelLayout);
        MessagesPanelLayout.setHorizontalGroup(
            MessagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 790, Short.MAX_VALUE)
        );
        MessagesPanelLayout.setVerticalGroup(
            MessagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(MessagesPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 790, 0));

        TopBarPanel.setBackground(new java.awt.Color(255, 255, 255));
        TopBarPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                TopBarPanelMouseDragged(evt);
            }
        });
        TopBarPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TopBarPanelMousePressed(evt);
            }
        });

        CloseWindow.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        CloseWindow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CloseWindow.setText("X");
        CloseWindow.setToolTipText("");
        CloseWindow.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CloseWindow.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CloseWindow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CloseWindowMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout TopBarPanelLayout = new javax.swing.GroupLayout(TopBarPanel);
        TopBarPanel.setLayout(TopBarPanelLayout);
        TopBarPanelLayout.setHorizontalGroup(
            TopBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TopBarPanelLayout.createSequentialGroup()
                .addContainerGap(757, Short.MAX_VALUE)
                .addComponent(CloseWindow, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        TopBarPanelLayout.setVerticalGroup(
            TopBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TopBarPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(CloseWindow))
        );

        getContentPane().add(TopBarPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 20));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void LabelLoadDocumentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LabelLoadDocumentMouseClicked
        FileDialog fileDialog = new FileDialog(this, "Seleccionar archivo", FileDialog.LOAD);
        fileDialog.setVisible(true);

        String selectedFile = fileDialog.getFile();
        if (selectedFile != null) {
            String filePath = fileDialog.getDirectory() + selectedFile;

            File file = new File(filePath);
            clientController.loadDocument(file);
        }
    }//GEN-LAST:event_LabelLoadDocumentMouseClicked

    private void LabelSendDocumentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LabelSendDocumentMouseClicked
        try {                    
            
            File file = clientController.getLocalDocument(localDocumentSelected);
            
            if(file == null) return;
            
            InputStream fileInputStream = new FileInputStream(file);
            request.setType("post");
            request.setService("send-document");
            request.setBody(localDocumentSelected);

            String jsonString = gson.toJson(request);

            byte[] requestJson = jsonString.getBytes("UTF-8");

            OutputStream outputStream = this.socketConnection.getOutputStream();
            InputStream inputStream = this.socketConnection.getInputStream();

            outputStream.write(requestJson);
            System.out.println("JSON enviado correctamente al servidor.");


            byte[] responseGetDocument = new byte[1024];

            int bytesRead = 0;


            while ((bytesRead = inputStream.read(responseGetDocument)) != -1) {
                String responseJsonString = new String(responseGetDocument, 0, bytesRead, StandardCharsets.UTF_8);
                response = gson.fromJson(responseJsonString, Response.class);
                break;
            }

            if(!response.getStatus().equals("200")){
                System.out.println(response.getData());
                return;
            }

            byte[] buffer = new byte[1024];
            bytesRead = 0;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            fileInputStream.close();
            
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                String responseJsonString = new String(buffer, 0, bytesRead, StandardCharsets.UTF_8);
                Response response = gson.fromJson(responseJsonString, Response.class);
                System.out.println("Respuesta recibida del servidor:");
                System.out.println("status: " + response.getStatus());
                System.out.println("data: " + response.getData());
                break;
            }
            System.out.println("Archivo enviado correctamente");
           
        } catch (JsonSyntaxException | IOException e) {
            showInputDialog.showCloseConnectionServer();
            this.setVisible(false);
            new Connection().setVisible(true);
            return;
        }
    }//GEN-LAST:event_LabelSendDocumentMouseClicked

    private void ClientDocumentListPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ClientDocumentListPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_ClientDocumentListPropertyChange

    private void ClientDocumentListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_ClientDocumentListValueChanged
        if (!evt.getValueIsAdjusting()) {
           JList<String> localDocumentList = (JList<String>) evt.getSource();
           String documentSelected = localDocumentList.getSelectedValue();
           this.localDocumentSelected = documentSelected;
        }
    }//GEN-LAST:event_ClientDocumentListValueChanged

    private void ButtonRefreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonRefreshMouseClicked
       
        try {
            InputStream inputStream = this.socketConnection.getInputStream();
            OutputStream outputStream = this.socketConnection.getOutputStream();
            
            request.setType("get");
            request.setService("list-documents");
            String jsonString = gson.toJson(request);
            int bytesRead = 0;
            byte[] dataBytes = new byte[1024];
            try {
                dataBytes = jsonString.getBytes("UTF-8");
            } catch (UnsupportedEncodingException ex) {
                System.out.println("Fallo al serializar");
            }
            try {
                outputStream.write(dataBytes);
            } catch (IOException ex) {
                showInputDialog.showCloseConnectionServer();
                this.setVisible(false);
                new Connection().setVisible(true);
                return;
            }
            System.out.println("JSON enviado correctamente al servidor.");
            byte[] responseDataBytes = new byte[1024];
            try {
                while ((bytesRead = inputStream.read(responseDataBytes)) != -1) {
                    String responseJsonString = new String(responseDataBytes, 0, bytesRead, StandardCharsets.UTF_8);
                    Response response = gson.fromJson(responseJsonString, Response.class);
                    System.out.println("Respuesta recibida del servidor:");
                    System.out.println("status: " + response.getStatus());
                    System.out.println("data: " + response.getData());
                    clientController.refreshServerDocuments(ServerDocumentsList, response.getData());
                    break;
                }
            } catch (IOException ex) {
               showInputDialog.showCloseConnectionServer();
                this.setVisible(false);
                new Connection().setVisible(true);
                return;
            }
        } catch (IOException ex) {
            showInputDialog.showCloseConnectionServer();
            this.setVisible(false);
            new Connection().setVisible(true);
            return;
        }
        
        
    }//GEN-LAST:event_ButtonRefreshMouseClicked

    private void ServerDocumentsListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_ServerDocumentsListValueChanged
        if (!evt.getValueIsAdjusting()) {
           JList<String> serverDocumentList = (JList<String>) evt.getSource();
           String documentSelected = serverDocumentList.getSelectedValue();
           this.serverDocumentSelected = documentSelected;
        }
    }//GEN-LAST:event_ServerDocumentsListValueChanged

    private void LabelDownloadDocumentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LabelDownloadDocumentMouseClicked
        try {                    
            InputStream inputStream = this.socketConnection.getInputStream();
            OutputStream outputStream = this.socketConnection.getOutputStream();
            
            if(serverDocumentSelected.length() <= 0) return;
            
            String nameDocument = clientController.getNameServerDocument(serverDocumentSelected);
            
            System.out.println(nameDocument);
            
            
            request.setType("get");
            request.setService("get-document");
            request.setBody(nameDocument);
            String jsonString = gson.toJson(request);

            int bytesRead = 0;
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

            if(!response.getStatus().equals("200")) {
                System.out.println("No se puede descargar el arhivo");
                return;
            }
            clientController.SaveDocument(socketConnection, nameDocument);
            byte buffer[] = new byte[1024];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                String responseJsonString = new String(buffer, 0, bytesRead, StandardCharsets.UTF_8);
                response = gson.fromJson(responseJsonString, Response.class);
                System.out.println("Respuesta recibida del servidor:");
                System.out.println("status: " + response.getStatus());
                System.out.println("data: " + response.getData());
                break;
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FtpPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            showInputDialog.showCloseConnectionServer();
            this.setVisible(false);
            new Connection().setVisible(true);
            return;
        }
            
                
    }//GEN-LAST:event_LabelDownloadDocumentMouseClicked

    private void TopBarPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TopBarPanelMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_TopBarPanelMousePressed

    private void TopBarPanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TopBarPanelMouseDragged
        int xScreen = evt.getXOnScreen();
        int yScreen = evt.getYOnScreen();

        this.setLocation(xScreen - xMouse, yScreen - yMouse);
    }//GEN-LAST:event_TopBarPanelMouseDragged

    private void CloseWindowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CloseWindowMouseClicked
        try {
            this.socketConnection.close();
            this.setVisible(false);
            Connection connection = new Connection();
            connection.setVisible(true);
        } catch (IOException ex) {
            System.exit(0);
            Logger.getLogger(FtpPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_CloseWindowMouseClicked

    private void LabelListUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LabelListUsersMouseClicked
        
        String data = "";
        Response response = new Response();
        try {                                            
            request.setType("get");
            request.setService("list-users");
            String jsonString = gson.toJson(request);
            
            OutputStream outputStream;
            InputStream inputStream;
            try {
                outputStream = socketConnection.getOutputStream();
                inputStream = socketConnection.getInputStream();
            } catch (Exception e) {
                System.out.println("catch 1");
                showInputDialog.showCloseConnectionServer();
                this.setVisible(false);
                new Connection().setVisible(true);
                return;
            }
            
            byte[] dataBytes = new byte[1024];
            try {
                dataBytes = jsonString.getBytes("UTF-8");
                outputStream.write(dataBytes);
            } catch (UnsupportedEncodingException ex) {
                System.out.println("Fallo al obtener los bytes del mensaje");
            } catch (IOException ex) {
                System.out.println("catch 2");
                showInputDialog.showCloseConnectionServer();
                this.setVisible(false);
                new Connection().setVisible(true);
                return;
            }
            int bytesRead = 0;
            byte[] responseDataBytes = new byte[1024];
            System.out.println("JSON enviado correctamente al servidor.");
            while ((bytesRead = inputStream.read(responseDataBytes)) != -1) {
                String responseJsonString = new String(responseDataBytes, 0, bytesRead, StandardCharsets.UTF_8);
                response = gson.fromJson(responseJsonString, Response.class);
                System.out.println("Respuesta recibida del servidor:");
                System.out.println("status: " + response.getStatus());
                System.out.println("data: " + response.getData());
                data = response.getData();
                break;
            }
        } catch (IOException ex) {
            System.out.println("catch 3");
            showInputDialog.showCloseConnectionServer();
            this.setVisible(false);
            new Connection().setVisible(true);
            return;
        }
        UsersConnected usersFrame;
        if(response.getStatus().equals("503")) {
            usersFrame = new UsersConnected();
        }else{
            usersFrame = new UsersConnected(clientController.getListUsers(data));
        }
        usersFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        usersFrame.setVisible(true);
    }//GEN-LAST:event_LabelListUsersMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ButtonRefresh;
    private javax.swing.JList<String> ClientDocumentList;
    private javax.swing.JPanel ClientPanel;
    private javax.swing.JLabel CloseWindow;
    private javax.swing.JLabel FtpTitle;
    private javax.swing.JLabel LabelDownloadDocument;
    private javax.swing.JLabel LabelListUsers;
    private javax.swing.JLabel LabelLoadDocument;
    private javax.swing.JLabel LabelSendDocument;
    private javax.swing.JLabel LabelServerDocumentList;
    private javax.swing.JLabel LabelServerDocumentList1;
    private javax.swing.JPanel MessagesPanel;
    private javax.swing.JScrollPane ScrollClientDocumentsList;
    private javax.swing.JScrollPane ScrollServerDocumentsList;
    private javax.swing.JList<String> ServerDocumentsList;
    private javax.swing.JLabel ServerTitle;
    private javax.swing.JPanel SeverPanel;
    private javax.swing.JPanel TopBarPanel;
    // End of variables declaration//GEN-END:variables
}
