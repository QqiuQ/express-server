package com.bobby.securityjwt.common;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;

import java.io.Serial;
import java.util.HashMap;

public class AjaxResult extends HashMap<String, Object> {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    public static final String CODE_TAG = "code";

    /**
     * 返回内容
     */
    public static final String MSG_TAG = "msg";

    /**
     * 数据对象
     */
    public static final String DATA_TAG = "data";

    /**
     * 初始化一个AjaxResult 对象
     */
    public AjaxResult() {
    }

    /**
     * 初始化一个AjaxResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     */
    public AjaxResult(int code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化一个AjaxResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     */
    public AjaxResult(int code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (!(data == null)) {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 返回默认成功消息
     *
     * @return 成功消息
     */
    public static AjaxResult success() {
        return AjaxResult.success("操作成功");
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static AjaxResult success(String msg) {
        return AjaxResult.success(msg, null);
    }

    /**
     * 返回成功数据
     *
     * @param data 数据对象
     * @return
     */
    public static AjaxResult success(Object data) {
        return AjaxResult.success("操作成功", data);
    }

    /**
     * 初始化一个返回成功消息AjaxResult对象
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static AjaxResult success(String msg, Object data) {
        return new AjaxResult(HttpStatus.SUCCESS, msg, data);
    }

    /**
     * 返回默认错误消息
     *
     * @return
     */
    public static AjaxResult error() {
        return AjaxResult.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static AjaxResult error(String msg) {
        return AjaxResult.error(msg, null);
    }

    /**
     * 返回错误数据
     *
     * @param code 状态码
     * @param msg  返回内容
     * @return 警告消息
     */
    public static AjaxResult error(int code, String msg) {
        return new AjaxResult(code, msg, null);
    }

    /**
     * 初始化一个返回错误消息AjaxResult对象
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static AjaxResult error(String msg, Object data) {
        return new AjaxResult(HttpStatus.ERROR, msg, data);
    }

    /**
     * 将数据放进字段data里面
     *
     * @param key
     * @param data
     * @return
     */

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