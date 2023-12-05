package com.teamc6.chatsystem;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatsystem.properties.Account;
import com.teamc6.chatsystem.request.Request;
import com.teamc6.chatsystem.model.*;
import com.teamc6.chatsystem.service.AdminService;
import com.teamc6.chatsystem.service.UserService;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class App {


        public static void main (String args[]) throws JsonProcessingException {
            Account.getInstance().setId(18);
            Account.getInstance().setUserName("BH123");
            Account.getInstance().setPassWord("123");

            //User user = new User(Account.getInstance().getId(), "Nguyen", null, true, "nguyen@gmail.com", null, Account.getInstance().getUserName(), Account.getInstance().getPassWord(), "USER", true);
            System.out.println(UserService.getInstance().getListFriend());


        }

    }
