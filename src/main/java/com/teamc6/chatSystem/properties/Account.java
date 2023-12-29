package com.teamc6.chatSystem.properties;

import com.teamc6.chatSystem.model.User;

public class Account {
    private  long Id;
    private  String userName;
    private  String passWord;

    public User getSelf() {
        return self;
    }

    public void setSelf(User self) {
        this.self = self;
    }

    private User self;

    public Account(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    private static final Account INSTANCE = new Account();

    // Private constructor to avoid client applications to use constructor
    private Account() {

    }

    public static Account getInstance() {
        return INSTANCE;
    }
}
