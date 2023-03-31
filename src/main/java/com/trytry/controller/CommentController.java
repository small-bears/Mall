package com.trytry.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trytry.common.lang.Result;
import com.trytry.domain.Collection;
import com.trytry.domain.Comment;
import com.trytry.mapper.CommentMapper;
import com.trytry.service.CommentSerivce;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author 我自己
 * @since 2021-11-13
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    CommentSerivce commentSerivce;

    @Resource
    CommentMapper commentMapper;

    @PostMapping("/add")
    public Result<?> save(@Validated @RequestBody Comment comment){
        commentSerivce.save(comment);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@Validated @RequestBody Comment comment){
        commentSerivce.updateById(comment);
        return Result.success();
    }
    @DeleteMapping("/{commentId}")
    public Result<?> delete(@PathVariable Integer commentId){
        commentSerivce.removeById(commentId);
        return Result.success();

    }
    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Integer> ids){
        commentSerivce.removeByIds(ids);
        return Result.success();
    }
    @GetMapping("/findAll")
    public Result findAll(){
        List<Comment> comments = commentMapper.selectList(null);
        return Result.success(comments);
    }
    @GetMapping("/count")
    public Result Count(){
        Integer count = commentMapper.selectCount(null);
        return Result.success(count);
    }
    @GetMapping("/find/{productId}")
    public Result<?> findProductId(@RequestParam(defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "5") Integer pageSize,
                                   @PathVariable Integer productId){
        Page<Comment> page = commentMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Comment>lambdaQuery().eq(Comment::getProductId, productId));
        return Result.success(page);
    }


    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        new Page<>(pageNum,pageSize);
        Page<Comment> customerInfoPage = commentMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Comment>lambdaQuery().like(Comment::getCustomerName,search));
        LambdaQueryWrapper<Comment> query = Wrappers.<Comment>lambdaQuery().orderByDesc(Comment::getCommentId);
        if (StrUtil.isNotBlank(search)) {
            query.like(Comment::getCustomerName, search);
        }
        return Result.success(customerInfoPage);
    }

}

