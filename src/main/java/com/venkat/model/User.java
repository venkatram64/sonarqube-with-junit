package com.venkat.model;

import java.util.Objects;

public class User {

    public enum UserType {REGULAR_USER, ADMIN_USER}
    private Long id;
    private String userName;
    private String password;
    private UserType userType;
    private boolean live;

    public User(String userName, String password, UserType userType, boolean live) {
        this.userName = userName;
        this.password = password;
        this.userType = userType;
        this.live = live;
    }

    public static User createRegularUser(String userName, String password){
        return new User(userName, password, UserType.REGULAR_USER, true);
    }

    public static User createAdminUser(String userName, String password){
        return new User(userName, password, UserType.ADMIN_USER, true);
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public boolean isLive() {
        return live;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return live == user.live &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(password, user.password) &&
                userType == user.userType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password, userType, live);
    }
}
