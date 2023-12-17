package com.teamc6.chatsystem.socket;


import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {
    private JTextArea textArea;
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private String username;

    public SocketClient(Socket socket, String username, JTextArea textArea) {
        try {
            this.socket = socket;
            this.reader = new BufferedReader((new InputStreamReader(socket.getInputStream())));
            this.writer = new BufferedWriter((new OutputStreamWriter(socket.getOutputStream())));
            this.username = username;
            this.textArea = textArea;
        } catch (IOException e) {
            closeEverything(socket, reader, writer);
        }
    }

    public void sendMessage(String messageToSend){
        try {
            if (socket.isConnected()){
                writer.write(username + ": " + messageToSend);
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            closeEverything(socket, reader, writer);
        }
    }

    public void listenForMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromGroupChat;

                while(socket.isConnected()){
                    try{
                        msgFromGroupChat = reader.readLine();
                        System.out.println(msgFromGroupChat);

                        textArea.append('\n'+ msgFromGroupChat);
                    } catch (IOException e) {
                        closeEverything(socket, reader, writer);
                    }
                }
            }
        }).start();
    }

    public void closeEverything(Socket socket, BufferedReader reader, BufferedWriter writer){
        try{
            if(reader != null){
                reader.close();
            }
            if(writer != null){
                writer.close();
            }
            if(socket != null){
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}