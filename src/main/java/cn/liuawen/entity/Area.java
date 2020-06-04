package cn.liuawen.entity;

import java.util.Date;

/**
 * @author : Liu Awen Email:willowawen@gmail.com
 * @create : 2020-01-04
 * 实体类设计与数据库表创建
 * 区域 Area
 * 名称 areaName、ID areaId、 权重 priority、创建时间createTime 、修改时间lastEditTime
 *
 * 现实的权重  优先级 priority
 */
public class Area {
    //ID
    private Long areaId;
    //名称
    private String areaName;
    //描述
    private String areaDesc;
    //权重 优先级
    private Integer priority;
    //创建时间
    private Date createTime;
    //最后的修改的时间
    private Date lastEditTime;

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaDesc() {
        return areaDesc;
    }

    public void setAreaDesc(String areaDesc) {
        this.areaDesc = areaDesc;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }
}
