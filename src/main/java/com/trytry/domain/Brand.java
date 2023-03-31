package com.trytry.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author 我自己
 * @since 2021-11-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Brand对象", description="")
public class Brand implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "brand_id", type = IdType.AUTO)
    private Integer brandId;

    private String brandName;

    private Integer oneCategoryId;

    private Integer twoCategoryId;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
