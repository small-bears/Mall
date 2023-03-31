package com.trytry.controller;


import com.trytry.common.lang.Result;
import com.trytry.domain.Notice;
import com.trytry.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeMapper noticeMapper;

    @GetMapping("/findAll")
    public List<Notice> findAll(){
        List<Notice> notices = noticeMapper.selectList(null);
        return notices;
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable("id") Integer id){
        noticeMapper.deleteById(id);
        return Result.success();
    }



    @GetMapping("/find/{id}")
    public Notice find(@PathVariable("id") Integer id){
        return this.noticeMapper.selectById(id);
    }

    @PutMapping("/update")
    public Result<?> update(@RequestBody Notice notice){
        noticeMapper.updateById(notice);
        return Result.success();
    }

    @PostMapping("/add")
    public Result<?> insert(@RequestBody Notice notice){
        noticeMapper.insert(notice);
        return Result.success();
    }


}
