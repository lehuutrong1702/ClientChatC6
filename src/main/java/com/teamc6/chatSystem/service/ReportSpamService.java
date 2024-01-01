package com.teamc6.chatSystem.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.teamc6.chatSystem.model.Page;
import com.teamc6.chatSystem.model.ReportSpam;
import com.teamc6.chatSystem.properties.Account;
import com.teamc6.chatSystem.request.Request;

public class ReportSpamService {
    private static final ReportSpamService INSTANCE = new ReportSpamService();

    private ReportSpamService() {

    }

    public static ReportSpamService getInstance() {
        return INSTANCE;
    }

    public Page<ReportSpam> getAll() throws JsonProcessingException {
        String url = "http://localhost:8080/api/v1/spams?page=0&size=100";
        Request request = new Request(url);
        request.authorization(Account.getInstance().getUserName(), Account.getInstance().getPassWord());

        request.GET();
        request.build();
        request.send();

        return (Page<ReportSpam>) request.getResBody(new TypeReference<Page<ReportSpam>>() {
        });
    }
}
