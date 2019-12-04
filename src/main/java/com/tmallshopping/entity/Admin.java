package com.tmallshopping.entity;

/**
 * @author Yuyunkuo
 * @Description: 管理员实体类
 * @date 2019/8/20 10:46
 */
public class Admin {

    private Integer id;
    private String username;
    private String password;
    private String photo;

    public Admin(Integer id, String username, String password, String photo) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.photo = photo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
