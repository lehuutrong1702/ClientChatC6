package com.teamc6.chatsystem.model;

import java.util.Date;

public class GroupChat {
    private long groupId;
    private String groupName;
    private Date timeCreate;

    public GroupChat(long groupId, String groupName, Date timeCreate) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.timeCreate = timeCreate;
    }

    public GroupChat() {

    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
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
