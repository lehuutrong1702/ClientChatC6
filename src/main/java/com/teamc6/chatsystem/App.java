package com.teamc6.chatsystem;



import com.teamc6.chatsystem.request.Request;
import com.teamc6.chatsystem.model.*;
import java.io.IOException;

    public class App {



        public static void main (String args[]) throws IOException, InterruptedException {
            Request request = new Request("http://localhost:8080/api/v1/users/");
            request.authorization("lht123","123");

            request.GET();
            request.build();
            request.send();

            Page<User> page = (Page<User>) request.getResBody(Page.class);
            System.out.println(page.toString());
        }

    }
