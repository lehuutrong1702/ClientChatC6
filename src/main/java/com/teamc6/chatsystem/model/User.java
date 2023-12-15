package com.teamc6.chatsystem.model;

import java.util.Date;

public class User {
    private long userId;
    private String fullName;
    private Date birthDay;
    private boolean gender;
    private String email;
    private Date timeRegister;
    private String userName;
    private String password;
    private String role;
    private boolean Active;


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getTimeRegister() {
        return timeRegister;
    }

    public void setTimeRegister(Date timeRegister) {
        this.timeRegister = timeRegister;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return Active;
    }

    public void setActive(boolean active) {
        Active = active;
    }


    public User(String fullName, Date birthDay, boolean gender, String email, String userName, String password) {
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.gender = gender;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    public User(String fullName, Date birthDay, boolean gender, String email, Date timeRegister,
                String userName, String password, String role, boolean active) {
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.gender = gender;
        this.email = email;
        this.timeRegister = timeRegister;
        this.userName = userName;
        this.password = password;
        this.role = role;
        Active = active;
    }

    public User()
    {

    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", fullName='" + fullName + '\'' +
                ", birthDay=" + birthDay +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", timeRegister=" + timeRegister +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", Active=" + Active +
                '}';
    }
}
