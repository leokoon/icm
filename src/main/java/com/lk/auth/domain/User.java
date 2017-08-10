package com.lk.auth.domain;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Administrator on 2017/3/24.
 */
@Entity
public class User implements Serializable{

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "USER_ID", nullable = false, length = 32)
    private String id;

    @Basic
    @Column(name = "USER_NAME", nullable = false,length = 80)
    private String username;

    @Basic
    @Column(name = "REAL_NAME",length = 50)
    private String realName;

    @Basic
    @Column(name = "PASSWORD", nullable = false, length = 64)
    private String password;
    @Basic
    @Column(name = "ADDRESS", length = 200)
    private String address;
    @Basic
    @Column(name = "PHONE", length = 20)
    private String phone;
    @Basic
    @Column(name = "SEX")
    private byte sex;
    @Basic
    @Column(name = "UPDATE_TIME")
    private Date updateTime;
    @Basic
    @Column(name = "CREATE_TIME")
    private Date createTime;
    @Basic
    @Column(name = "LAST_LOGIN")
    private Date lastLogin;
    @Basic
    @Column(name = "LOGIN_IP")
    private String loginIp;
    @Basic
    @Column(name = "ACCOUNT_NON_EXPIRED")
    private boolean accountNonExpired;
    @Basic
    @Column(name = "ACCOUNT_NON_LOCKED")
    private boolean accountNonLocked;
    @Basic
    @Column(name = "CREDENTIALS_NON_EXPIRED")
    private boolean credentialsNonExpired;
    @Basic
    @Column(name = "ENABLED")
    private boolean enabled;

    @Transient
    private Collection<String> auths;

    @Transient
    private String credentialsSalt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
        this.sex = sex;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getCredentialsSalt() {return username;}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    public void setAuths(Collection<String> auths) {
        this.auths = auths;
    }

    public Collection<String> getAuthorities() {
        return this.auths;
    }
}
