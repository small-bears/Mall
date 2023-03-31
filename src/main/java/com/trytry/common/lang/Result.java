package com.trytry.common.lang;

import lombok.Data;

@Data
public class Result<T>{
    private int code;//200正常，非200异常
    private String msg;
    private T data;

    public Result() {
    }

    public Result(T data) {
        this.data = data;
    }

    public static Result success() {
        Result result = new Result<>();
        result.setCode(200);
        result.setMsg("成功");
        return result;
    }
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>(data);
        result.setCode(200);
        result.setMsg("成功");
        return result;
    }

    public static Result fail(String msg){
        return fail(400,msg);
    }

    public static Result fail(int code,String msg){
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }
}
