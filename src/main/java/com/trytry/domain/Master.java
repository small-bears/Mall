package com.trytry.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
public class Master implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    private String orderSn;

    private Integer customerId;
    private String productErm;

    private String shippingUser;


    private String province;


    private String city;

    private String district;

    private String address;

    private String paymentMethod;

    private BigDecimal orderMoney;

    private BigDecimal shippingMoney;

    private Integer productId;
//
    private BigDecimal paymentMoney;

    private String shippingCompName;

    private Integer shippingSn;

    private String productImage;

    private String productColor;

    private String productName;

    private Long productPhones;

    private String productMessage;

    private Integer productPrice;

    private Integer productNum;

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
