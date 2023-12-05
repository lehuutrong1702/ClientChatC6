package com.teamc6.chatsystem.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamc6.chatsystem.model.GroupChat;
import com.teamc6.chatsystem.model.Page;
import com.teamc6.chatsystem.model.Relationship;
import com.teamc6.chatsystem.model.User;
import com.teamc6.chatsystem.properties.Account;
import com.teamc6.chatsystem.request.Request;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class UserService {
    public Page<User> filterUser(String username, long page, long size) throws JsonProcessingException {
        String url = String.format("http://localhost:8081/api/v1/users/filter/%s?page=%d&size=%d", username, page, size);
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();
        Page<User> pageUser = (Page<User>) request.getResBody(new TypeReference<Page<User>>() {});
        return  pageUser;
    }

    public User findById(long Id) throws JsonProcessingException{
        String url = String.format("http://localhost:8081/api/v1/users/%d", Id);
        System.out.println(url);
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();

        User user = (User) request.getResBody(new TypeReference<User>(){});
        return user;
    }

    public User findByUserName(String username) throws JsonProcessingException{
        String url = String.format("http://localhost:8081/api/v1/users/search?username=%s", username);
        System.out.println(url);
        Request request = new Request(url);

        request.GET();
        request.build();
        request.send();

        User user = (User) request.getResBody(new TypeReference<User>(){});
        System.out.println(user);
        return user;
    }

    public Relationship addFriend(long id) throws JsonProcessingException {
        String url = String.format("http://localhost:8081/api/v1/users/%d/friends/%d", Account.getInstance().getId(), id);
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.POST(null);
        request.build();
        request.send();

        Relationship relationship = (Relationship) request.getResBody(new TypeReference<Relationship>(){});
        return relationship;
    }

    public List<User> getListFriend() throws JsonProcessingException {
        String url = String.format("http://localhost:8081/api/v1/users/%d/friends",  Account.getInstance().getId());
        System.out.println(url);
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();

        List<User> listUser = (List<User>) request.getResBody(new TypeReference<List<User>>() {});

        return listUser;
    }

    public List<GroupChat> getListGroup() throws JsonProcessingException {
        String url = String.format("http://localhost:8081/api/v1/users/%d/groups",  Account.getInstance().getId());
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();

        List<GroupChat> listGroup = (List<GroupChat>) request.getResBody(new TypeReference<List<GroupChat>>() {});
        return listGroup;
    }

    public User updateUser(User user) throws JsonProcessingException{
        String url = String.format("http://localhost:8081/api/v1/users/%d",  Account.getInstance().getId());
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());
        request.PUT(user);
        request.build();
        request.send();

        return user;
    }

    private static final UserService INSTANCE = new UserService();

    private UserService() {

    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
