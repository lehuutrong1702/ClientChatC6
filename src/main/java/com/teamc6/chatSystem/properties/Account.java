package com.teamc6.chatSystem.properties;

public class Account {
    private static final Account INSTANCE = new Account();
    private long Id;
    private String userName;
    private String passWord;
    private long sessionID;

    public Account(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    // Private constructor to avoid client applications to use constructor
    private Account() {

    }

    public static Account getInstance() {
        return INSTANCE;
    }

    public long getSessionID() {
        return sessionID;
    }

    public void setSessionID(long sessionID) {
        this.sessionID = sessionID;
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
}
