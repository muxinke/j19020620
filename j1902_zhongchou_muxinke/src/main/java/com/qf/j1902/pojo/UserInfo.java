package com.qf.j1902.pojo;

/**
 * Created by Administrator on 2019/5/27.
 */
public class UserInfo {
    private int id;
    private  String zhanghu;
    private  String password;
    private  String email;
    private  String username;
    private  String idcard;
    private  String phoneNumber;
    private String  userImg;
    private  String zhType;
    private  String jsType;
    private String status;
    private String suggestion;
    public UserInfo() {
    }

    public UserInfo(int id, String zhanghu, String password, String email, String username, String idcard, String phoneNumber, String userImg, String zhType, String jsType, String status,String suggestion) {
        this.id = id;
        this.zhanghu = zhanghu;
        this.password = password;
        this.email = email;
        this.username = username;
        this.idcard = idcard;
        this.phoneNumber = phoneNumber;
        this.userImg = userImg;
        this.zhType = zhType;
        this.jsType = jsType;
        this.status = status;
        this.suggestion=suggestion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZhanghu() {
        return zhanghu;
    }

    public void setZhanghu(String zhanghu) {
        this.zhanghu = zhanghu;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getZhType() {
        return zhType;
    }

    public void setZhType(String zhType) {
        this.zhType = zhType;
    }

    public String getJsType() {
        return jsType;
    }

    public void setJsType(String jsType) {
        this.jsType = jsType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInfo userInfo = (UserInfo) o;

        if (zhanghu != null ? !zhanghu.equals(userInfo.zhanghu) : userInfo.zhanghu != null) return false;
        return password != null ? password.equals(userInfo.password) : userInfo.password == null;
    }

    @Override
    public int hashCode() {
        int result = zhanghu != null ? zhanghu.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

}
