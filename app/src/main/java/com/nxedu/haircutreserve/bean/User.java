package com.nxedu.haircutreserve.bean;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/3/23
 */

public class User {
    private String username;
    private String tel;
    private String nickname;
    private String gender;
    private String usericon;
    private String userstate;
    private String usersignature;
    private String idcard;


    public User() {
        super();
    }

    public User(String username, String tel, String nickname,
                        String gender, String usericon, String userstate,
                        String usersignature, String idcard) {
        super();
        this.username = username;
        this.tel = tel;
        this.nickname = nickname;
        this.gender = gender;
        this.usericon = usericon;
        this.userstate = userstate;
        this.usersignature = usersignature;
        this.idcard = idcard;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getTel() {
        return tel;
    }


    public void setTel(String tel) {
        this.tel = tel;
    }


    public String getNickname() {
        return nickname;
    }


    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public String getGender() {
        return gender;
    }


    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getUsericon() {
        return usericon;
    }


    public void setUsericon(String usericon) {
        this.usericon = usericon;
    }


    public String getUserstate() {
        return userstate;
    }


    public void setUserstate(String userstate) {
        this.userstate = userstate;
    }


    public String getUsersignature() {
        return usersignature;
    }


    public void setUsersignature(String usersignature) {
        this.usersignature = usersignature;
    }


    public String getIdcard() {
        return idcard;
    }


    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }
}
