package com.trytry.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

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
public class Record implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "record_id", type = IdType.AUTO)
    private Integer recordId;

    private String username;

    private String recordNr;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
