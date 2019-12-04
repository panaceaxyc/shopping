package com.tmallshopping.entity;

public class User {
    private Integer id;

    private String name;

    private String password;

    private String photo;

    private Integer points;

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getAnonymousName() {
        if (name == null){
            return null;
        }
        if (name.length() <= 1){
            return "*";
        }
        if (name.length() == 2){
            return name.substring(0, 1) + "*";
        }

        char[] cs = name.toCharArray();
        for (int i = 1; i < cs.length - 1; i++) {
            cs[i] = '*';
        }
        return new String(cs);
    }

    public User(Integer id, String name, String password, String photo, Integer points) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.photo = photo;
        this.points = points;
    }
}