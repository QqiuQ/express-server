package com.bobby.securityjwt.common;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @className: Result
 * @author: Bobby
 * @date: 10/25/2023
 * 统一返回接口
 **/
public class Result implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public Integer code;
    public String msg;
    public Map<String, Object> data;

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = new HashMap<>();
    }

    public static Result success() {
        return new Result(HttpStatus.SUCCESS, "操作成功");
    }

    public static Result success(String msg) {
        return new Result(HttpStatus.SUCCESS, msg);
    }

    public static Result error() {
        return new Result(HttpStatus.ERROR, "操作失败");
    }

    public static Result error(String msg) {
        return new Result(HttpStatus.ERROR, msg);
    }

    // 放到data Map中
    public void put(String key, Object value) {
        this.data.put(key, value);
    }

    public String asJsonString() {
        return JSONObject.toJSONString(this, JSONWriter.Feature.WriteNulls);
    }

    public static class HttpStatus {
        /**
         * 操作成功
         */
        public static final int SUCCESS = 200;

        /**
         * 对象创建成功
         */
        public static final int CREATED = 201;

        /**
         * 请求已经被接受
         */
        public static final int ACCEPTED = 202;

        /**
         * 操作已经执行成功，但是没有返回数据
         */
        public static final int NO_CONTENT = 204;

        /**
         * 资源已被移除
         */
        public static final int MOVED_PERM = 301;

        /**
         * 重定向
         */
        public static final int SEE_OTHER = 303;

        /**
         * 资源未被修改
         */
        public static final int NOT_MODIFIED = 304;

        /**
         * 参数列表错误（缺少、格式不匹配）
         */
        public static final int BAD_REQUEST = 400;

        /**
         * 未授权
         */
        public static final int UNAUTHORIZED = 401;

        /**
         * 访问受限，授权过期
         */
        public static final int FORBIDDEN = 403;

        /**
         * 资源，服务未找到
         */
        public static final int NOT_FOUND = 404;

        /**
         * 未被允许http方法
         */
        public static final int BAD_METHOD = 405;

        /**
         * 资源冲突，或者资源被锁
         */
        public static final int CONFLICT = 409;

        /**
         * 不支持媒体类型数据
         */
        public static final int UNSUPPORTED_TYPE = 415;

        /**
         * 系统内部错误
         */
        public static final int ERROR = 500;

        /**
         * 未实现相应接口
         */
        public static final int NOT_IMPLEMENTED = 501;
    }
}
