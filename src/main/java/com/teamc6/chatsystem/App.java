package com.teamc6.chatsystem;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatsystem.properties.Account;
import com.teamc6.chatsystem.request.Request;
import com.teamc6.chatsystem.model.*;
import com.teamc6.chatsystem.service.AdminService;
import com.teamc6.chatsystem.service.UserService;

import java.io.IOException;

    public class App {


        public static void main (String[] args) throws JsonProcessingException {
            Account.getInstance().setId(19);
            Account.getInstance().setUserName("LTAT123");
            Account.getInstance().setPassWord("123");

            System.out.println(AdminService.getInstance().allUser(1, 2));
        }

    }
