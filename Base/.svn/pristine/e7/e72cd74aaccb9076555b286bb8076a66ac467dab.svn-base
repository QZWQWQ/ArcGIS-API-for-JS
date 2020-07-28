package com.njwpdi.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
import lombok.Data;

/**
 * 区域表
 * 
 * @author sunjing
 * @date 2019-12-13 14:53:01
 */
@Data
@TableName("SYS_AREA")
public class SysAreaEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 区域ID
     */
    @TableId
    private String areaId;
    /**
     * 区域名称
     */
    private String areaName;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 县/区
     */
    private String country;
    /**
     * 边界Json
     */
    private Blob extent;
    /**
     * $column.comments
     */
    private String parentId;
    /**
     * 目标区类型
     */
    private String areaType;

}
