package com.phone.funoutdoors.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2016/10/25.
 */
@Entity
public class User {
    //用户编号
    @Id(autoincrement = true)
    private long user_id;
    //手机号（账号）
    private String phone;
    //昵称(用户名)
    private String nickName;
    //性别（0：男；1：女）
    private int gener;
    //密码（不能少于六位）
    private String password;
    //头像
    private String headIcon;
    //粉丝
    private int fans;
    //关注
    private int attention;

    public User() {
    }

    @Keep
    public User(long user_id, String phone, String nickName, int gener, String password, String headIcon, int fans, int attention) {
        this.user_id = user_id;
        this.phone = phone;
        this.nickName = nickName;
        this.gener = gener;
        this.password = password;
        this.headIcon = headIcon;
        this.fans = fans;
        this.attention = attention;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getGener() {
        return gener;
    }

    public void setGener(int gener) {
        this.gener = gener;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public int getAttention() {
        return attention;
    }

    public void setAttention(int attention) {
        this.attention = attention;
    }
}
