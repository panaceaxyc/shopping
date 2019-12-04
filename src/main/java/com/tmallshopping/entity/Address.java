package com.tmallshopping.entity;

/**
 * @author Yuyunkuo
 * @Description:    收货地址实体类
 * @date 2019/8/11 13:53
 */
public class Address {
    private Integer id;
    private Integer userId;
    private String province;
    private String city;
    private String county;
    private String detailAddress;
    private String conName;
    private String conTel;

    public Address(Integer id, Integer userId, String province, String city, String county, String detailAddress, String conName, String conTel) {
        this.id = id;
        this.userId = userId;
        this.province = province;
        this.city = city;
        this.county = county;
        this.detailAddress = detailAddress;
        this.conName = conName;
        this.conTel = conTel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getConName() {
        return conName;
    }

    public void setConName(String conName) {
        this.conName = conName;
    }

    public String getConTel() {
        return conTel;
    }

    public void setConTel(String conTel) {
        this.conTel = conTel;
    }
}
