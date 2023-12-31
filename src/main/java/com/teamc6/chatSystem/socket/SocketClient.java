package com.teamc6.chatSystem.socket;


import com.teamc6.chatSystem.model.CommandObj;
import com.teamc6.chatSystem.model.InitObj;
import com.teamc6.chatSystem.model.MessageObj;
import com.teamc6.chatSystem.model.User;
import com.teamc6.chatSystem.properties.Account;
import com.teamc6.chatSystem.service.UserService;

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

            try {
                if (socket.isConnected()){
                    writer.writeObject(new InitObj(LocalDateTime.now(), Account.getInstance().getId(), username));
                    writer.reset();
                    writer.flush();
                }
            } catch (IOException e) {
                closeEverything(socket, reader, writer);
            }
        } catch (IOException e) {
            closeEverything(socket, reader, writer);
        }
    }

    public void sendMessage(String messageToSend){
        try {
            if (socket.isConnected()){
                System.out.println(messageToSend);
                writer.writeObject(new MessageObj(LocalDateTime.now(), username, messageToSend));
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
                Object msgFromGroupChat;
                while(socket.isConnected()){
                    try{
                        msgFromGroupChat = reader.readObject();
                        if(msgFromGroupChat instanceof MessageObj){
                            MessageObj msg = (MessageObj) msgFromGroupChat;

                            textArea.append('\n'+ msg.userName()+ ": "+ msg.message());
                        }
                        if(msgFromGroupChat instanceof CommandObj){
                            CommandObj cmd = (CommandObj) msgFromGroupChat;
                            if(cmd.command().contains("online")){
                                // set card to online;
                            }else {
                                // set card to offline;
                            }
                        }
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
        System.out.println("Closing");
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