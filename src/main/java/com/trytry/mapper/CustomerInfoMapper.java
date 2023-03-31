package com.trytry.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.trytry.domain.CustomerInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author 我自己
 * @since 2021-10-27
 */
@Mapper
public interface CustomerInfoMapper extends BaseMapper<CustomerInfo> {

}
