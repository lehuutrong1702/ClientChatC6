package com.teamc6.chatSystem.model;

import java.util.Date;

public class GroupChat {
    private long id;

    @Override
    public String toString() {
        return "GroupChat{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", timeCreate=" + timeCreate +
                '}';
    }

    private String groupName;
    private Date timeCreate;

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
