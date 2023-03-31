package com.trytry.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author 111
 * @since 2021-06-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Promptly implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 管理员ID
     */
    @TableId(value = "order_detail_id", type = IdType.AUTO)
    private Integer orderDetailId;

    private Integer orderId;

    private Integer productId;

    /**
     * 管理员名称
     */
    private Integer productNum;

    /**
     * 密码
     */
    private String productImage;

    /**
     * 联系方式
     */
    private String productName;

    /**
     * 管理员状态,0启用，1禁用
     */
    private Integer productPrice;



    private String productColor;

    private String address;

    private Long productPhones;

    private String paymentMethod;
//
    private String productMessage;

    private String shippingUser;

    private Integer orderMoney;
//
    private Integer shippingMoney;

    private Integer paymentMoney;

    private String shippingCompName;

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


}
