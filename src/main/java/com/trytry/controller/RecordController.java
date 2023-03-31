package com.trytry.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trytry.common.lang.Result;
import com.trytry.domain.Img;
import com.trytry.domain.Record;
import com.trytry.mapper.RecordMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 111
 * @since 2021-06-30
 */
@RestController
@RequestMapping("/record")
public class RecordController {

    @Resource
    private RecordMapper recordMapper;

    @GetMapping("/findAll")
    public List<Record> findAll(){
        List<Record> records = recordMapper.selectList(null);
        return records;
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable("id") Integer id){
        recordMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping("/find/{id}")
    public Record find(@PathVariable("id") Integer id){
        return this.recordMapper.selectById(id);
    }

    @PutMapping("/update")
    public Result<?> update(@RequestBody Record record){
        recordMapper.updateById(record);
        return Result.success();
    }

    @PostMapping("/add")
    public Result<?> insert(@RequestBody Record record){
        recordMapper.insert(record);
        return Result.success();
    }

    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Integer> ids){
        recordMapper.deleteBatchIds(ids);
        return Result.success();
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        new Page<>(pageNum, pageSize);
        Page<Record> recordPage = recordMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<Record>lambdaQuery().like(Record::getUsername, search).orderByDesc(Record::getCreateTime));
        LambdaQueryWrapper<Record> query = Wrappers.<Record>lambdaQuery().orderByDesc(Record::getRecordId);
        if (StrUtil.isNotBlank(search)) {
            query.like(Record::getUsername, search);
        }
        return Result.success(recordPage);
    }

}

