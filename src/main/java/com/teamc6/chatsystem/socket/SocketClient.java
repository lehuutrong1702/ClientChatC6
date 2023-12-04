package com.teamc6.chatsystem.socket;


import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private String username;

    public SocketClient(Socket socket, String username) {
        try {
            this.socket = socket;
            this.reader = new BufferedReader((new InputStreamReader(socket.getInputStream())));
            this.writer = new BufferedWriter((new OutputStreamWriter(socket.getOutputStream())));
            this.username = username;
        } catch (IOException e) {
            closeEverything(socket, reader, writer);
        }
    }

    public void sendMessage(){
        try {
            writer.write((username));
            writer.newLine();
            writer.flush();

            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()){
                String messageToSend = scanner.nextLine();
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

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the chat room(PORT): ");
        int port = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter your username for the group chat: ");
        String username = scanner.nextLine();
        Socket socket = new Socket("192.168.245.1", port);
        SocketClient client = new SocketClient(socket, username);
        client.listenForMessage();
        client.sendMessage();
    }
}