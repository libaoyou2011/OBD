package com.baolong.obd.my.data.entity;

import java.io.Serializable;

public class UserInfoModel implements Serializable {
    private static final long serialVersionUID = -8543857351323123798L;
    /**
     * password : 8d51eb2d428467276883ca85e4c0c589
     * loginName : admin
     * sex : 0
     * phonenumber : 15888888888
     * avatar : 2019/05/07/7b33edbad9426fed72075957efde42e2.jpg
     * userName : 宝龙
     * email : baolong@163.com
     */

    private String password;
    private String nickName;
    private String sex;
    private String phonenumber;
    private String avatar;
    private String userName;
    private String email;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

