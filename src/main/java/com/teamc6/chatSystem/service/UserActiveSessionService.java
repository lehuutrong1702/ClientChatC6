package com.teamc6.chatSystem.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.teamc6.chatSystem.model.UserActiveSession;
import com.teamc6.chatSystem.properties.Account;
import com.teamc6.chatSystem.request.Request;

import java.util.List;

public class UserActiveSessionService {
    private static final UserActiveSessionService INSTANCE = new UserActiveSessionService();


    private UserActiveSessionService() {

    }

    public static UserActiveSessionService getInstance() {
        return INSTANCE;
    }

    public UserActiveSession createSession(long Id) throws JsonProcessingException {
        String url = String.format("http://localhost:8081/api/v1/user-active-sessions/%d", Id);
        Request request = new Request(url);

        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.POST(null);
        request.build();
        request.send();

        UserActiveSession activeSession = (UserActiveSession) request.getResBody(new TypeReference<UserActiveSession>() {
        });
        return activeSession;
    }

    public UserActiveSession endSession(long Id) throws JsonProcessingException {
        String url = String.format("http://localhost:8081/api/v1/user-active-sessions/%d", Id);
        Request request = new Request(url);

        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.PATCH(null);
        request.build();
        request.send();

        UserActiveSession activeSession = (UserActiveSession) request.getResBody(new TypeReference<UserActiveSession>() {
        });
        return activeSession;
    }

    public List<UserActiveSession> getAll() throws JsonProcessingException {
        String url = "http://localhost:8081/api/v1/user-active-sessions";
        Request request = new Request(url);

        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();

        List<UserActiveSession> activeSession = (List<UserActiveSession>) request.getResBody(new TypeReference<List<UserActiveSession>>() {
        });
        return activeSession;
    }
}
