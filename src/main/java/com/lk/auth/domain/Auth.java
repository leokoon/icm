package com.lk.auth.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/24.
 */
@Entity
public class Auth implements Serializable{
    private static final long serialVersionUID = -8691787588103534260L;
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "AUTH_ID", nullable = false, length = 32)
    private String authId;
    @Column(name = "PARENT_ID", nullable = false, length = 32)
    private String parentId;
    @Column(name = "AUTH_NAME", nullable = false)
    private String authName;
    @Column(name = "AUTH_URL",length = 500)
    private String authUrl;
    @Column(name = "SHOW_ORDER",length = 4)
    private Long showOrder;
    @Column(name = "IS_MENU")
    private boolean isMenu;
    @Column(name = "SAVE_LOG")
    private boolean saveLog;
    @Column(name = "ICON")
    private String icon;

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getAuthUrl() {
        return authUrl;
    }

    public void setAuthUrl(String authUrl) {
        this.authUrl = authUrl;
    }

    public Long getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Long showOrder) {
        this.showOrder = showOrder;
    }

    public boolean isMenu() {
        return isMenu;
    }

    public void setMenu(boolean menu) {
        isMenu = menu;
    }

    public boolean isSaveLog() {
        return saveLog;
    }

    public void setSaveLog(boolean saveLog) {
        this.saveLog = saveLog;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
