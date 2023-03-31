package com.trytry.mapper;

import com.trytry.domain.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 评论 Mapper 接口
 * </p>
 *
 * @author 我自己
 * @since 2021-11-13
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}
