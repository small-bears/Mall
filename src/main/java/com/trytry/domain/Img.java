package com.trytry.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Img implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 管理员ID
     */
      @TableId(value = "img_id", type = IdType.AUTO)
    private Integer imgId;

    /**
     * 管理员名称
     */
    private Integer productId;

    private String productName;

    private String productImage;

    private Integer productPrice;
    private Integer customerId;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;



}
