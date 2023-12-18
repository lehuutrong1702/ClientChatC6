package com.teamc6.chatSystem.service;

import SwingUI.Home.HomePanel.MessageUI;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.teamc6.chatSystem.model.*;
import com.teamc6.chatSystem.properties.Account;
import com.teamc6.chatSystem.request.Request;

import java.util.List;
import java.util.Set;

public class GroupChatService {
    public Connection getConnectionByID(long Id) throws JsonProcessingException {
        String url = String.format("http://localhost:8080/api/v1/groups/%d/connection", Id);
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();

        Connection connection = (Connection) request.getResBody(new TypeReference<Connection>() {});
        return connection;
    }
    public GroupChat searchGroupChatById(long Id) throws JsonProcessingException {
        String url = String.format("http://localhost:8080/api/v1/groups/%d", Id);
        Request request = new Request(url);

        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();

        GroupChat group = (GroupChat) request.getResBody(new TypeReference<GroupChat>() {});
        return group;
    }

    public Set<User> listMember(long Id) throws JsonProcessingException{
        String url = String.format("http://localhost:8080/api/v1/groups/%d/members", Id);
        Request request = new Request(url);

        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();

        Set<User> members = (Set<User>) request.getResBody(new TypeReference<Set<User>>() {});
        return members;
    }

    public Set<User> listAdmin(long Id) throws JsonProcessingException{
        String url = String.format("http://localhost:8080/api/v1/groups/%d/admins", Id);
        Request request = new Request(url);

        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();

        Set<User> admins = (Set<User>) request.getResBody(new TypeReference<Set<User>>() {});
        return admins;
    }

    public GroupChat addMemberGroupChat(long idGroup, long idMember) throws JsonProcessingException{
        String url = String.format("http://localhost:8080/api/v1/groups/%d/members/%d", idGroup, idMember);
        Request request = new Request(url);

        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.PUT(GroupChat.class);
        request.build();
        request.send();

        GroupChat groupChat = (GroupChat) request.getResBody(new TypeReference<GroupChat>() {});
        return groupChat;
    }

    public GroupChat addAdminGroupChat(long idGroup, long idAdmin) throws JsonProcessingException{
        String url = String.format("http://localhost:8080/api/v1/groups/%d/admins/%d", idGroup, idAdmin);
        Request request = new Request(url);

        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.PUT(GroupChat.class);
        request.build();
        request.send();

        GroupChat groupChat = (GroupChat) request.getResBody(new TypeReference<GroupChat>() {});
        return groupChat;
    }

    public GroupChat createGroupChat() throws JsonProcessingException{
        String url = "http://localhost:8080/api/v1/groups";
        Request request = new Request(url);

        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.PUT(GroupChat.class);
        request.build();
        request.send();

        GroupChat groupChat = (GroupChat) request.getResBody(new TypeReference<GroupChat>() {});
        return groupChat;
    }
    public Page<Message> getOldMessages(long Id) throws JsonProcessingException {
        String url = String.format("http://localhost:8080/api/v1/groups/%d/messages", Id);
        Request request = new Request(url);

        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.PUT(GroupChat.class);
        request.build();
        request.send();

        Page<Message> pageMessage = (Page<Message>) request.getResBody(new TypeReference<Page<Message>>() {});
        return  pageMessage;
    }
    private static final GroupChatService INSTANCE = new GroupChatService();
    private GroupChatService() {

    }
    public static GroupChatService getInstance() {
        return INSTANCE;
    }
}
