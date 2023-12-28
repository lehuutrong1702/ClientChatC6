package com.teamc6.chatSystem.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.teamc6.chatSystem.model.GroupChat;
import com.teamc6.chatSystem.model.Page;
import com.teamc6.chatSystem.model.Relationship;
import com.teamc6.chatSystem.model.User;
import com.teamc6.chatSystem.properties.Account;
import com.teamc6.chatSystem.request.Request;

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

    public User adduser(User u) throws JsonProcessingException {
        String url = "http://localhost:8080/api/v1/users";
        Request request = new Request(url);
        //request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.POST(u);
        request.build();
        request.send();

        User user = (User) request.getResBody(new TypeReference<User>() {});
        return  user;
    }

    public User findById(long Id) throws JsonProcessingException{
        String url = String.format("http://localhost:8080/api/v1/users/search/id=%d", Id);
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

    public User updateUser(User u) throws JsonProcessingException{
        String url = String.format("http://localhost:8080/api/v1/users/id=%d",  Account.getInstance().getId());
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());
        request.PUT(u);
        request.build();
        request.send();

        User user = (User) request.getResBody(new TypeReference<User>() {});

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
    public void setActive(boolean isActive) throws JsonProcessingException {
        String url = String.format("http://localhost:8080/api/v1/users/%d/active/%b", Account.getInstance().getId(), isActive);
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();
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
    public void unFriend(Long id) {
        String url = String.format("http://localhost:8080/api/v1/users/%d/friends/%d", Account.getInstance().getId(), id);
        Request request = new Request(url);

        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.DELETE();
        request.build();
        request.send();
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


    private static final UserService INSTANCE = new UserService();

    private UserService() {

    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
