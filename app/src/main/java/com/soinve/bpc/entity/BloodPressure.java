package com.soinve.bpc.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

import java.io.Serializable;
import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by liuzhaowei on 2018/2/27.
 */

@Entity
public class BloodPressure implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id(autoincrement = true)
    private Long id;

    //高压
    private Integer highPressure;
    //低压
    private Integer lowPressure;
    //心率
    private Integer heartRate;
    //时间
    private Date createTime;
    @Generated(hash = 177080720)
    public BloodPressure(Long id, Integer highPressure, Integer lowPressure,
            Integer heartRate, Date createTime) {
        this.id = id;
        this.highPressure = highPressure;
        this.lowPressure = lowPressure;
        this.heartRate = heartRate;
        this.createTime = createTime;
    }
    @Generated(hash = 1446110616)
    public BloodPressure() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getHighPressure() {
        return this.highPressure;
    }
    public void setHighPressure(Integer highPressure) {
        this.highPressure = highPressure;
    }
    public Integer getLowPressure() {
        return this.lowPressure;
    }
    public void setLowPressure(Integer lowPressure) {
        this.lowPressure = lowPressure;
    }
    public Integer getHeartRate() {
        return this.heartRate;
    }
    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }
    public Date getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


}
