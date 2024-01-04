package com.teamc6.chatSystem.service;

import SwingUI.Utils.DateAndString;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.teamc6.chatSystem.model.*;
import com.teamc6.chatSystem.properties.Account;
import com.teamc6.chatSystem.request.Request;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class UserService {
    public Page<User> filterUser(String username, long page, long size) throws JsonProcessingException {
        String url = String.format("http://localhost:8080/api/v1/users/filter/%s?&page=%d&size=%d", username, page, size);
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();

        Page<User> pageUser = (Page<User>) request.getResBody(new TypeReference<Page<User>>() {});
        return  pageUser;
    }

    public List<User> getByTime(Date start, Date end) throws JsonProcessingException {
        String url = String.format("http://localhost:8080/api/v1/users/time-register?start=%sT00:00:00&end=%sT00:00:00",
                DateAndString.DatetoString(start, "yyyy-MM-dd"),
                DateAndString.DatetoString(end, "yyyy-MM-dd")
        );
        System.out.println(url);
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();

        List<User> userList = (List<User>) request.getResBody(new TypeReference<List<User>>() {});
        return  userList;
    }

    public List<User> getByYear(int year) throws JsonProcessingException {
        String url = String.format("http://localhost:8080/api/v1/users/time-register?start=%d-01-01T00:00:00&end=%d-01-01T00:00:00",
                year, year + 1
        );
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();

        List<User> userList = (List<User>) request.getResBody(new TypeReference<List<User>>() {});
        return userList;
    }

    public User adduser(User u) throws JsonProcessingException {
        String url = "http://localhost:8080/api/v1/users";
        Request request = new Request(url);
        //request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.POST(u);
        request.build();
        request.send();

        User user = (User) request.getResBody(new TypeReference<User>() {});
        return user;
    }

    public User findById(long Id) throws JsonProcessingException{
        String url = String.format("http://localhost:8080/api/v1/users/%d", Id);
        System.out.println(url);
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();

        User user = (User) request.getResBody(new TypeReference<User>() {});
        return user;
    }

    public User findByUserName(String username) throws JsonProcessingException{
        String url = String.format("http://localhost:8080/api/v1/users/search?username=%s", username);
        Request request = new Request(url);

        request.GET();
        request.build();
        request.send();

        User user = (User) request.getResBody(new TypeReference<User>() {});
        return user;
    }

    public Relationship addFriend(long id) throws JsonProcessingException {
        String url = String.format("http://localhost:8080/api/v1/users/%d/friends/%d", Account.getInstance().getId(), id);
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.POST(null);
        request.build();
        request.send();

        Relationship relationship = (Relationship) request.getResBody(new TypeReference<Relationship>() {});
        return relationship;
    }

    public Page<User> getAll() throws JsonProcessingException {
        String url = String.format("http://localhost:8080/api/v1/users?page=0&size=100");
        Request request = new Request(url);

        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());
        request.GET();
        request.build();
        request.send();

        Page<User> listUser = (Page<User>) request.getResBody(new TypeReference<Page<User>>() {});
        return listUser;
    }

    public Set<User> getListFriend(Long id) throws JsonProcessingException {
        String url = String.format("http://localhost:8080/api/v1/users/%d/friends", id);
        Request request = new Request(url);

        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());
        request.GET();
        request.build();
        request.send();

        Set<User> listUser = (Set<User>) request.getResBody(new TypeReference<Set<User>>() {});
        return listUser;
    }

    public Set<GroupChat> getListGroup() throws JsonProcessingException {
        String url = String.format("http://localhost:8080/api/v1/users/%d/groups",  Account.getInstance().getId());
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();

        Set<GroupChat> listGroup = (Set<GroupChat>) request.getResBody(new TypeReference<Set<GroupChat>>() {});
        return listGroup;
    }

    public Set<UserActiveSession> getActiveSession(Long id) throws JsonProcessingException {
        String url = String.format("http://localhost:8080/api/v1/users/%d/user-active-sessions", id);
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();

        Set<UserActiveSession> activeSessions = (Set<UserActiveSession>) request.getResBody(new TypeReference<Set<UserActiveSession>>() {});
        return activeSessions;
    }

    public User updateUser(User u, Long id) throws JsonProcessingException{
        String url = String.format("http://localhost:8080/api/v1/users/%d",  id);
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());
        request.PUT(u);
        request.build();
        request.send();

        User user = (User) request.getResBody(new TypeReference<User>() {});

        return user;
    }

    public Page<User> filterFriendByName(Long id, String username, long page, long size) throws JsonProcessingException {
        String url = String.format("http://localhost:8080/api/v1/users/%d/friends/%s?page=%d&size=%d", id, username, page, size);
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();

        Page<User> pageUser = (Page<User>) request.getResBody(new TypeReference<Page<User>>() {});
        return  pageUser;
    }
    public void setActive(Long id, boolean isActive) throws JsonProcessingException {
        String url = String.format("http://localhost:8080/api/v1/users/%d/active/%b", id, !isActive);
        Request request = new Request(url);
        System.out.println(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.POST(null);
        request.build();
        request.send();
    }

    public Page<GroupChat> filterGroupsByName(Long id, String groupname, long page, long size) throws JsonProcessingException {
        String url = String.format("http://localhost:8080/api/v1/users/%d/groups/%s?page=%d&size=%d", id, groupname, page, size);
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();

        Page<GroupChat> pageGroup = (Page<GroupChat>) request.getResBody(new TypeReference<Page<GroupChat>>() {});
        return  pageGroup;
    }
    public void unFriend(Long id) {
        String url = String.format("http://localhost:8080/api/v1/users/%d/friends/%d", Account.getInstance().getId(), id);
        Request request = new Request(url);

        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.DELETE();
        request.build();
        request.send();
    }

    public Boolean deleteUser(Long id) throws JsonProcessingException {

        String url = String.format("http://localhost:8080/api/v1/users/%d", id);
        System.out.println(id);
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.DELETE();
        request.build();
        request.send();

        return (Boolean) request.getResBody(new TypeReference<Boolean>() {});
    }

    public GroupChat getPrivateGroupChat(Long id) throws JsonProcessingException {

        String url = String.format("http://localhost:8080/api/v1/users/%d/friends/%d/group-chat", Account.getInstance().getId(), id);
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();

        return (GroupChat) request.getResBody(new TypeReference<GroupChat>() {});
    }
    public Relationship getRelationShip(Long id) throws JsonProcessingException {
        String url = String.format("http://localhost:8080/api/v1/users/%d/friends/%d", Account.getInstance().getId(), id);
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();
        System.out.println(id);
        return (Relationship) request.getResBody(new TypeReference<Relationship>() {});
    }

    public void block(Long id) {
        String url = String.format("http://localhost:8080/api/v1/users/%d/block/%d", Account.getInstance().getId(), id);
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.POST(null);
        request.build();
        request.send();
    }

    public Page<User> blocking() throws JsonProcessingException {
        String url = String.format("http://localhost:8080/api/v1/users/%d/blocking", Account.getInstance().getId());
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();

        return (Page<User>) request.getResBody(new TypeReference<Page<User>>() {});
    }

    public Page<User> blockers() throws JsonProcessingException {
        String url = String.format("http://localhost:8080/api/v1/users/%d/blockers", Account.getInstance().getId());
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();

        return (Page<User>) request.getResBody(new TypeReference<Page<User>>() {});
    }

    public void reportSpam(long id){
        String url = String.format("http://localhost:8080/api/v1/users/%d/spams", id);
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.POST(null);
        request.build();
        request.send();
    }

    public boolean isOnline(long id) throws JsonProcessingException {
        String url = String.format("http://localhost:8080/api/v1/users/%d/online", id);
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();

        return (boolean) request.getResBody(new TypeReference<Boolean>() {});
    }


    private static final UserService INSTANCE = new UserService();

    private UserService() {

    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
