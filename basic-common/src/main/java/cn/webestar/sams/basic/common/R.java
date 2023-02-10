package cn.webestar.sams.basic.common;

import java.io.Serializable;

public class R<T> implements Serializable {

    private Integer code;
    private T data;
    private String msg;

    /**
     * 禁止new，全部使用静态方法
     */
    private R() { }

    private R(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    private R(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private R(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static R success() {
        return new R(BizCode.OK, null);
    }

    public static <T> R<T> success(T data) {
        return new R(BizCode.OK, data);
    }

    public static R fail(String msg) {
        return new R(BizCode.UN_ERROR, msg);
    }

    public static R fail(Integer code, String msg) {
        return new R<>(code, msg);
    }

    public static <T> R<T> fail(Integer code, String msg, T data) {
        return new R(code, msg, data);
    }

    public static <T> R<T> fail(CommonException ex) {
        return new R(ex.getCode(), ex.getMessage());
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
