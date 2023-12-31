package com.teamc6.chatSystem.model;

import java.util.Date;

public class UserActiveSession {
    private long id ;

    private Date timeActive;

    private Date timeLogout;

    private User sessionUser ;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getTimeActive() {
        return timeActive;
    }

    public void setTimeActive(Date timeActive) {
        this.timeActive = timeActive;
    }

    public Date getTimeLogout() {
        return timeLogout;
    }

    public void setTimeLogout(Date timeLogout) {
        this.timeLogout = timeLogout;
    }

    public User getSessionUser() {
        return sessionUser;
    }

    public void setSessionUser(User sessionUser) {
        this.sessionUser = sessionUser;
    }
}
