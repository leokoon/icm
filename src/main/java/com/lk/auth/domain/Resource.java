package com.lk.auth.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/3/25.
 */
@Entity
public class Resource {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "RESOURCE_ID", nullable = false,length = 32)
    private String id;
    @Column(name = "TYPE",length = 8)
    private String type;
    @Column(name = "NAME",length = 80)
    private String name;
    @Column(name = "DESCRIPTION",length = 200)
    private String description;
    @Column(name = "PRIORITY",length = 8)
    private String priority;
    @Column(name = "ENABLE")
    private boolean enable;
    @Column(name = "IS_SYS")
    private boolean isSys;

    @OneToMany(targetEntity = Auth.class, fetch = FetchType.LAZY)
    private Set<Auth> authSet = new HashSet<>(0);

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isSys() {
        return isSys;
    }

    public void setSys(boolean sys) {
        isSys = sys;
    }

    public Set<Auth> getAuthSet() {
        return authSet;
    }

    public void setAuthSet(Set<Auth> authSet) {
        this.authSet = authSet;
    }
}
