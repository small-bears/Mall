package com.trytry.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;


@Data
@TableName("t_notice")
public class Notice extends Model<Notice> {
    /**
     private static final long serialVersionUID = 1L;

     /**
     * 管理员ID
     */
    @TableId(value = "admin_id", type = IdType.AUTO)
    private Integer adminId;



    /**
     * 管理员名称
     */
    private String adminName;

    /**
     * 密码
     */
    private String adminPwd;
    private String productErm;
    /**
     * 联系方式
     */
    private String adminPhone;

    /**
     * 管理员状态,0启用，1禁用
     */
    private Integer adminState;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private String adminImage;

    private String adminMessage;
    private String adminAddress;
    private String adminDescribe;
    private Long adminPhones;
    private String adminPay;


}
