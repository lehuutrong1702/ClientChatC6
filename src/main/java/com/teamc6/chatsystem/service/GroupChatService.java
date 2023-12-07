package com.teamc6.chatsystem.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.teamc6.chatsystem.model.Connection;
import com.teamc6.chatsystem.model.GroupChat;
import com.teamc6.chatsystem.model.Page;
import com.teamc6.chatsystem.model.User;
import com.teamc6.chatsystem.properties.Account;
import com.teamc6.chatsystem.request.Request;

import java.awt.event.MouseWheelEvent;
import java.util.Set;

public class GroupChatService {
    public GroupChat searchGroupChatById(long Id) throws JsonProcessingException {
        String url = String.format("http://localhost:8081/api/v1/groups/%d", Id);
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());
        request.GET();
        request.build();
        request.send();
        GroupChat group = (GroupChat) request.getResBody(new TypeReference<GroupChat>() {});
        return group;
    }

    public Set<User> listMember(long Id) throws JsonProcessingException{
        String url = String.format("http://localhost:8081/api/v1/groups/%d/members", Id);
        Request request = new Request(url);

        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();

        Set<User> members = (Set<User>) request.getResBody(new TypeReference<Set<User>>() {});
        return members;
    }

    public Set<User> listAdmin(long Id) throws JsonProcessingException{
        String url = String.format("http://localhost:8081/api/v1/groups/%d/admins", Id);
        Request request = new Request(url);

        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();

        Set<User> admins = (Set<User>) request.getResBody(new TypeReference<Set<User>>() {});
        return admins;
    }

    public GroupChat addMemberGroupChat(long idGroup, long idMember) throws JsonProcessingException{
        String url = String.format("http://localhost:8081/api/v1/groups/%d/members/%d", idGroup, idMember);
        Request request = new Request(url);
        System.out.println(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.POST(null);
        request.build();
        request.send();

        //GroupChat groupChat = (GroupChat) request.getResBody(new TypeReference<GroupChat>() {});
        return searchGroupChatById(idGroup);
    }

    public GroupChat addAdminGroupChat(long idGroup, long idAdmin) throws JsonProcessingException{
        String url = String.format("http://localhost:8081/api/v1/groups/%d/admins/%d", idGroup, idAdmin);
        Request request = new Request(url);

        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.PUT(null);
        request.build();
        request.send();

        //GroupChat groupChat = (GroupChat) request.getResBody(new TypeReference<GroupChat>() {});
        return searchGroupChatById(idGroup);
    }

    public Connection getConnect(long idGroup) throws  JsonProcessingException{
        String url = String.format("http://localhost:8081/api/v1/groups/%d/connection", idGroup);
        Request request = new Request(url);

        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();

        Connection connection = (Connection) request.getResBody(new TypeReference<Connection>() {});
        return connection;
    }

    public GroupChat createGroupChat() throws JsonProcessingException{
        String url = "http://localhost:8081/api/v1/groups";
        Request request = new Request(url);

        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.PUT(GroupChat.class);
        request.build();
        request.send();

        GroupChat groupChat = (GroupChat) request.getResBody(new TypeReference<GroupChat>() {});
        return groupChat;
    }

    private static final GroupChatService INSTANCE = new GroupChatService();

    private GroupChatService() {

    }

    public static GroupChatService getInstance() {
        return INSTANCE;
    }
}
