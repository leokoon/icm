package com.lk.auth.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Administrator on 2017/3/25.
 */
@Entity
public class RememberLogin {
    @Id
    @Column(name = "series", nullable = false,length = 80)
    private String series;
    @Column(name="user_name",length = 80)
    private String userName;
    @Column(name="token",length = 200)
    private String token;
    @Column(name="last_used")
    private Date lastUsed;

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(Date lastUsed) {
        this.lastUsed = lastUsed;
    }
}
