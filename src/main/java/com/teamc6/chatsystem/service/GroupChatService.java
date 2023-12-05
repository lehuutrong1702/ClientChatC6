package com.teamc6.chatsystem.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.teamc6.chatsystem.model.GroupChat;
import com.teamc6.chatsystem.model.User;
import com.teamc6.chatsystem.properties.Account;
import com.teamc6.chatsystem.request.Request;

import java.awt.event.MouseWheelEvent;
import java.util.List;
import java.util.Set;

public class GroupChatService {
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

    public GroupChat createGroupChat() throws JsonProcessingException{
        String url = "http://localhost:8081/api/v1/groups";
        Request request = new Request(url);

        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.PUT(GroupChat.class);
        request.build();
        request.send();

        GroupChat groupChat = (GroupChat) request.getResBody(new TypeReference<GroupChat>(){});
        return groupChat;
    }
}
