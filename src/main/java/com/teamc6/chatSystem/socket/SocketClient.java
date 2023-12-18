package com.teamc6.chatSystem.socket;


import com.teamc6.chatSystem.model.MessageObj;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;

public class SocketClient {
    private JTextArea textArea;
    private Socket socket;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;
    private String username;

    public SocketClient(Socket socket, String username, JTextArea textArea) {
        try {
            this.socket = socket;
            this.reader = new ObjectInputStream(socket.getInputStream());
            this.writer = new ObjectOutputStream(socket.getOutputStream());
            this.username = username;
            this.textArea = textArea;
        } catch (IOException e) {
            closeEverything(socket, reader, writer);
        }
    }

    public void sendMessage(String messageToSend){
        try {
            if (socket.isConnected()){
                System.out.println(messageToSend);
                writer.writeObject(new MessageObj(LocalDateTime.now(), username,messageToSend));
                writer.reset();
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
                MessageObj msgFromGroupChat;
                while(socket.isConnected()){
                    try{
                        msgFromGroupChat = (MessageObj) reader.readObject();
                        System.out.println(msgFromGroupChat);
                        textArea.append('\n'+ msgFromGroupChat.userName()+ ": "+ msgFromGroupChat.message());
                    } catch (IOException e) {
                        closeEverything(socket, reader, writer);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }

    public void closeEverything(Socket socket, ObjectInputStream reader, ObjectOutputStream writer){
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