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
public class Receipt implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 管理员ID
     */
      @TableId(value = " receipt_id", type = IdType.AUTO)
    private Integer  receiptId;

    /**
     * 管理员名称
     */


    private Integer customerId;

    /**
     * 密码
     */
    private String shippingUser;

    /**
     * 联系方式
     */
    private String province;

    /**
     * 管理员状态,0启用，1禁用
     */
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

    private BigInteger shippingSn;

    private String productImage;

    private String productColor;

    private String productName;

    private Long productPhones;

    private String productMessage;

    private Integer productPrice;

    private Integer productNum;
//


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

    @TableField(fill = FieldFill.INSERT)
    private Date shippingTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date receiveTime;


}
