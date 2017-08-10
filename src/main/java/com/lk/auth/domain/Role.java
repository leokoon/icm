package com.lk.auth.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/3/24.
 */
@Entity
public class Role implements Serializable {

    private static final long serialVersionUID = -1864828175895209182L;
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "ROLE_ID", nullable = false, length = 32)
    private String roleId;
    @Basic
    @Column(name = "ROLE_NAME", nullable = false,length = 80)
    private String roleName;
    @Basic
    @Column(name = "DESCRIPTION",length = 200)
    private String description;

    @Transient
    private Set<Auth> authSet = new HashSet<Auth>(0);

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Auth> getAuthSet() {
        return authSet;
    }

    public void setAuthSet(Set<Auth> authSet) {
        this.authSet = authSet;
    }
}