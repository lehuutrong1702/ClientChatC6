package com.teamc6.chatsystem.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.teamc6.chatsystem.model.*;
import com.teamc6.chatsystem.properties.Account;
import com.teamc6.chatsystem.request.Request;

import java.util.Set;

public class UserService {
    public Page<User> filterUser(String username, long page, long size) throws JsonProcessingException {
        String url = String.format("http://localhost:8081/api/v1/users/filter/username?username=%s&page=%d&size=%d", username, page, size);
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
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());
        request.GET();
        request.build();
        request.send();
        User user = (User) request.getResBody(new TypeReference<User>() {});
        return user;
    }

    public User findByUserName(String username) throws JsonProcessingException{
        String url = String.format("http://localhost:8081/api/v1/users/search?username=%s", username);
        Request request = new Request(url);
        request.GET();
        request.build();
        request.send();
        User user = (User) request.getResBody(new TypeReference<User>() {});
        return user;
    }

    public Relationship addFriend(long id) throws JsonProcessingException {
        String url = String.format("http://localhost:8081/api/v1/users/%d/friends/%d", Account.getInstance().getId(), id);
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.POST(null);
        request.build();
        request.send();

        Relationship relationship = (Relationship) request.getResBody(new TypeReference<Relationship>() {});
        return relationship;
    }

    public Set<User> getListFriend() throws JsonProcessingException {
        String url = String.format("http://localhost:8081/api/v1/users/%d/friends",  Account.getInstance().getId());
        Request request = new Request(url);

        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());
        request.GET();
        request.build();
        request.send();

        Set<User> listUser = (Set<User>) request.getResBody(new TypeReference<Set<User>>() {});
        return listUser;
    }

    public Set<GroupChat> getListGroup() throws JsonProcessingException {
        String url = String.format("http://localhost:8081/api/v1/users/%d/groups",  Account.getInstance().getId());
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();

        Set<GroupChat> listGroup = (Set<GroupChat>) request.getResBody(new TypeReference<Set<GroupChat>>() {});
        return listGroup;
    }

    public User updateUser(User user) throws JsonProcessingException{
        String url = String.format("http://localhost:8081/api/v1/users/%d",  Account.getInstance().getId());
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());
        request.PUT(user);
        request.build();
        request.send();

        //User user = (User) request.getResBody(new TypeReference<User>() {});

        return user;
    }

    public Page<User> filterFriendByName(Long id, String username, long page, long size) throws JsonProcessingException {
        String url = String.format("http://localhost:8081/api/v1/users/%d/friends/%s?page=%d&size=%d", id, username, page, size);
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();

        Page<User> pageUser = (Page<User>) request.getResBody(new TypeReference<Page<User>>() {});
        return  pageUser;
    }


    public Page<GroupChat> filterGroupsByName(Long id, String groupname, long page, long size) throws JsonProcessingException {
        String url = String.format("http://localhost:8081/api/v1/users/%d/groups/%s?page=%d&size=%d", id, groupname, page, size);
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();

        Page<GroupChat> pageGroup = (Page<GroupChat>) request.getResBody(new TypeReference<Page<GroupChat>>() {});
        return  pageGroup;
    }

    public User createUser(User user) throws JsonProcessingException{
        String url = String.format("http://localhost:8081/api/v1/users");
        Request request = new Request(url);
        //request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.POST(user);
        request.build();
        request.send();

        //User user = (User) request.getResBody(new TypeReference<User>() {});

        return user;
    }

    private static final UserService INSTANCE = new UserService();

    private UserService() {

    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
