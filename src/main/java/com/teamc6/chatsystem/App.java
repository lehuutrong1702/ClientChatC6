package com.teamc6.chatsystem;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatsystem.properties.Account;
import com.teamc6.chatsystem.request.Request;
import com.teamc6.chatsystem.model.*;
import com.teamc6.chatsystem.service.UserService;

import java.io.IOException;

    public class App {


        public static void main (String args[]) throws JsonProcessingException {
            Account.getInstance().setId(17);
            Account.getInstance().setUserName("GH123");
            Account.getInstance().setPassWord("123");

            System.out.println(UserService.getInstance().findById(18));
        }

    }
