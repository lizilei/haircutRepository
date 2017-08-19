package com.nxedu.haircutreserve.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/3/23
 */

@Entity
public class User {
    @Id
    private String id;
    @Property(nameInDb = "USERNAME")
    private String username;
    @Property(nameInDb = "TEL")
    private String tel;
    @Property(nameInDb = "NICKNAME")
    private String nickname;
    @Property(nameInDb = "GENDER")
    private String gender;
    @Property(nameInDb = "USERICON")
    private String usericon;
    @Property(nameInDb = "USERSTATE")
    private String userstate;
    @Property(nameInDb = "USERSIGNATURE")
    private String usersignature;
    @Property(nameInDb = "IDCARD")
    private String idcard;
    @Generated(hash = 1810073750)
    public User(String id, String username, String tel, String nickname,
            String gender, String usericon, String userstate, String usersignature,
            String idcard) {
        this.id = id;
        this.username = username;
        this.tel = tel;
        this.nickname = nickname;
        this.gender = gender;
        this.usericon = usericon;
        this.userstate = userstate;
        this.usersignature = usersignature;
        this.idcard = idcard;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getTel() {
        return this.tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getNickname() {
        return this.nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getGender() {
        return this.gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getUsericon() {
        return this.usericon;
    }
    public void setUsericon(String usericon) {
        this.usericon = usericon;
    }
    public String getUserstate() {
        return this.userstate;
    }
    public void setUserstate(String userstate) {
        this.userstate = userstate;
    }
    public String getUsersignature() {
        return this.usersignature;
    }
    public void setUsersignature(String usersignature) {
        this.usersignature = usersignature;
    }
    public String getIdcard() {
        return this.idcard;
    }
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }
}
