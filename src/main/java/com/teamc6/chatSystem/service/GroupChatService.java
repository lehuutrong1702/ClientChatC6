package com.teamc6.chatSystem.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.teamc6.chatSystem.model.*;
import com.teamc6.chatSystem.properties.Account;
import com.teamc6.chatSystem.request.Request;

import java.util.ArrayList;
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

    public void clearMessages(long id){
        String url = String.format("http://localhost:8080/api/v1/groups/%d/messages", id);
        Request request = new Request(url);

        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.DELETE();
        request.build();
        request.send();
    }

    public List<Message> searchInChat(long idGroup, String search) throws JsonProcessingException {
        String url = String.format("http://localhost:8080/api/v1/groups/%d/search", idGroup);
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());
        request.POST(search);
        request.build();
        request.send();

        List<Message> messages = (List<Message>) request.getResBody(new TypeReference<List<Message>>() {});
        return messages;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getPage() {
        return page;
    }

    private long page = 0;
    private long size = 20;
    public List<Message> getOldMessages(long Id) throws JsonProcessingException {
        System.out.println(Id);
        String url = String.format("http://localhost:8080/api/v1/groups/%d/messages?page=%d&size=%d", Id, page, size);
        Request request = new Request(url);

        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();

        Page<Message> pageMessage = (Page<Message>) request.getResBody(new TypeReference<Page<Message>>() {});
        if(pageMessage == null){
            return new ArrayList<Message>();
        }

        page++;
        return  pageMessage.getContent();
    }
    private static final GroupChatService INSTANCE = new GroupChatService();
    private GroupChatService() {

    }
    public static GroupChatService getInstance() {
        return INSTANCE;
    }
}
