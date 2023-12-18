package com.teamc6.chatSystem.model;

import java.util.Date;

public class GroupChat {
    private long id;
    private String groupName;
    private Date timeCreate;

    public GroupChat(long id, String groupName, Date timeCreate) {
        this.id = id;
        this.groupName = groupName;
        this.timeCreate = timeCreate;
    }

    public GroupChat() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Date getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Date timeCreate) {
        this.timeCreate = timeCreate;
    }
}
