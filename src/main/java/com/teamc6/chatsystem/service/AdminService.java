package com.teamc6.chatsystem.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.teamc6.chatsystem.model.GroupChat;
import com.teamc6.chatsystem.model.Page;
import com.teamc6.chatsystem.model.User;
import com.teamc6.chatsystem.properties.Account;
import com.teamc6.chatsystem.request.Request;

import java.util.List;
import java.util.Set;

public class AdminService {
    public GroupChat searchGroupChatById(long Id) throws JsonProcessingException {
        String url = String.format("http://localhost:8081/api/v1/groups/%d", Id);
        Request request = new Request(url);

        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();

        GroupChat group = (GroupChat) request.getResBody(new TypeReference<GroupChat>(){});
        return group;
    }

    public List<User> listMember(long Id) throws JsonProcessingException{
        String url = String.format("http://localhost:8081/api/v1/groups/%d/members", Id);
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();

        List<User> members = (List<User>) request.getResBody(new TypeReference<List<User>>() {});
        return members;
    }

    public List<User> listAdmin(long Id) throws JsonProcessingException{
        String url = String.format("http://localhost:8081/api/v1/groups/%d/admins", Id);
        Request request = new Request(url);

        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();

        List<User> admins = (List<User>) request.getResBody(new TypeReference<List<User>>() {});
        return admins;
    }

    public GroupChat addMemberGroupChat(long idGroup, long idMember) throws JsonProcessingException{
        String url = String.format("http://localhost:8081/api/v1/groups/%d/members/%d", idGroup, idMember);
        Request request = new Request(url);

        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.PUT(GroupChat.class);
        request.build();
        request.send();

        GroupChat groupChat = (GroupChat) request.getResBody(new TypeReference<GroupChat>(){});
        return groupChat;
    }

    public GroupChat addAdminGroupChat(long idGroup, long idAdmin) throws JsonProcessingException{
        String url = String.format("http://localhost:8081/api/v1/groups/%d/admins/%d", idGroup, idAdmin);
        Request request = new Request(url);

        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.PUT(GroupChat.class);
        request.build();
        request.send();

        GroupChat groupChat = (GroupChat) request.getResBody(new TypeReference<GroupChat>(){});
        return groupChat;
    }

    public Page<User> allUser(long page, long size) throws JsonProcessingException {
        String url = String.format("http://localhost:8081/api/v1/users?page=%d&size=%d", page, size);
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();

        Page<User> pageUser = (Page<User>) request.getResBody(new TypeReference<Page<User>>() {});

        return  pageUser;
    }

    public Page<GroupChat> allGroupChat(long page, long size) throws JsonProcessingException {
        String url = String.format("http://localhost:8081/api/v1/groups?page=%d&size=%d", page, size);
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();

        Page<GroupChat> pageGroup = (Page<GroupChat>) request.getResBody(new TypeReference<Page<GroupChat>>() {});
        return  pageGroup;
    }

    public Page<GroupChat> filterByNameGroupChat(String name, long page, long size) throws JsonProcessingException {
        String url = String.format("http://localhost:8081/api/v1/groups/search/%s?page=%d&size=%d", name, page, size);
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();

        Page<GroupChat> pageGroup = (Page<GroupChat>) request.getResBody(new TypeReference<Page<GroupChat>>() {});
        return  pageGroup;
    }

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

    private static final AdminService INSTANCE = new AdminService();

    private AdminService() {

    }

    public static AdminService getInstance() {
        return INSTANCE;
    }
}
