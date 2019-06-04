package com.demo.cloud.user.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

@Data
@Builder
@Table(name = "ibp_user")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfo extends BaseEntity {

    String userName;
    String orgName;
    String password;
    String createTime;
    String nickName;
    String b1;
    String b2;
    String b3;
    String b4;
    String b5;
    String b6;
    String b7;
    String b8;
    String b9;
    String b10;
    String b11;
    String b12;
    String b13;
    String b14;
    String b15;
    String b16;
    String b17;
    String b18;
    String b19;
    String b20;
    String b21;
    String b22;
    String b23;
    String b24;
    String b25;
    String b26;
    String b27;
    String b28;
    String b29;
    String b30;


    public String getUserName() {
        return userName;
    }

    public UserInfo setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getOrgName() {
        return orgName;
    }

    public UserInfo setOrgName(String orgName) {
        this.orgName = orgName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserInfo setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getCreateTime() {
        return createTime;
    }

    public UserInfo setCreateTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getNickName() {
        return nickName;
    }

    public UserInfo setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }
}
