package com.teamc6.chatSystem.model;

import java.util.Date;

public class Message {
    private long id;
    private Date creationDateTime;
    private String userName;
    private String message;
    private GroupChat groupChat;

    public long getId() {
        return id;
    }

    public Date getCreationDateTime() {
        return creationDateTime;
    }

    public String getUserName() {
        return userName;
    }

    public String getMessage() {
        return message;
    }

    public GroupChat getGroupChat() {
        return groupChat;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", creationDateTime=" + creationDateTime +
                ", userName='" + userName + '\'' +
                ", message='" + message + '\'' +
                ", groupChat=" + groupChat +
                '}';
    }
}
