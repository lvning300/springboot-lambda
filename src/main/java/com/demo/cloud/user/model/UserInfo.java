package com.demo.cloud.user.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

@Data
@Table(name = "dbo.ibp_user")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfo extends BaseEntity {

    String userName;
    String orgName;
    String password;
    String createTime;
    String nickName;

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
