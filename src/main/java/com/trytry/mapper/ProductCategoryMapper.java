package com.trytry.mapper;

import com.trytry.domain.ProductCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 分类信息表 Mapper 接口
 * </p>
 *
 * @author 我自己
 * @since 2021-11-15
 */
@Mapper
public interface ProductCategoryMapper extends BaseMapper<ProductCategory> {

}
