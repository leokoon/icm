package com.lk.basic.centerykt.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;

@Entity
@Table(name = "basic_center_ykt", schema = "QHCARD", catalog = "")
public class BasicCenterYkt {
    private String centerNo;
    private String centerName;

    @Id
    @Column(name = "center_no", nullable = true, length = 20)
    public String getCenterNo() {
        return centerNo;
    }

    public void setCenterNo(String centerNo) {
        this.centerNo = centerNo;
    }

    @Basic
    @Column(name = "center_name", nullable = true, length = 50)
    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasicCenterYkt that = (BasicCenterYkt) o;

        if (centerNo != null ? !centerNo.equals(that.centerNo) : that.centerNo != null) return false;
        if (centerName != null ? !centerName.equals(that.centerName) : that.centerName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = centerNo != null ? centerNo.hashCode() : 0;
        result = 31 * result + (centerName != null ? centerName.hashCode() : 0);
        return result;
    }
}
