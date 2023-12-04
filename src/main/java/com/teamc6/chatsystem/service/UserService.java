package com.teamc6.chatsystem.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamc6.chatsystem.model.GroupChat;
import com.teamc6.chatsystem.model.Page;
import com.teamc6.chatsystem.model.Relationship;
import com.teamc6.chatsystem.model.User;
import com.teamc6.chatsystem.properties.Account;
import com.teamc6.chatsystem.request.Request;

import java.util.Set;

public class UserService {
    public Page<User> filterUser(String username) throws JsonProcessingException {
        String url = String.format("http://localhost:8081/api/v1/users/filter/%s", username);
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();

        Page<User> pageUser = (Page<User>) request.getResBody(Page.class);
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

        User user = (User) request.getResBody(User.class);
        return user;
    }

    public User findByUserName(String username) throws JsonProcessingException{
        String url = String.format("http://localhost:8081/api/v1/users/search?username=%s", username);
        System.out.println(url);
        Request request = new Request(url);

        request.GET();
        request.build();
        request.send();

        User user = (User) request.getResBody(User.class);
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

        Relationship relationship = (Relationship) request.getResBody(Relationship.class);
        return relationship;
    }

    public Set<User> getListFriend() throws JsonProcessingException {
        String url = String.format("http://localhost:8081/api/v1/users/%id/friends",  Account.getInstance().getId());
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();

        Set<User> listUser = (Set<User>) request.getResBody(GroupChat.class);
        return listUser;
    }

    public Set<GroupChat> getListGroup() throws JsonProcessingException {
        String url = String.format("http://localhost:8081/api/v1/users/%id/groups",  Account.getInstance().getId());
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();

        Set<GroupChat> listGroup = (Set<GroupChat>) request.getResBody(GroupChat.class);
        return listGroup;
    }

    public User updateUser() throws JsonProcessingException{
        String url = String.format("http://localhost:8081/api/v1/users/%d",  Account.getInstance().getId());
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());
        request.PUT(User.class);
        request.build();
        request.send();

        User user = (User) request.getResBody(User.class);

        return user;
    }

    private static final UserService INSTANCE = new UserService();

    private UserService() {

    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
