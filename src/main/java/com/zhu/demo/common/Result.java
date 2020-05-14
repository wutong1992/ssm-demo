package com.zhu.demo.common;

/**
 * @Author: zhutao
 * @Date: 2020/4/29 16:16
 */
public class Result<T> {
    private int retCode;
    private String message;
    private T data;


    private Result(T data) {
        this.retCode = 200;
        this.message = "成功";
        this.data = data;
    }


    private Result(CodeMsg cm) {
        if(cm == null) {
            return;
        }
        this.retCode = cm.getRetCode();
        this.message = cm.getMessage();
    }


    /**
     * 成功的时候调用，需要传入参数
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }


    /**
     * 成功的时候调用，不需要传入参数
     * @param <T>
     * @return
     */
    public static <T> Result<T> success() {
        return (Result<T>) success("");
    }


    /**
     * 失败时调用
     * @param cm
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(CodeMsg cm) {
        return new Result<T>(cm);
    }


    /**
     * 失败的时候调用，扩展消息参数
     * @param cm
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(CodeMsg cm, String msg) {
        CodeMsg newCodeMsg = null;
        try {
            newCodeMsg = (CodeMsg) cm.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        newCodeMsg.setMessage(cm.getMessage() + "--" + msg);
        return new Result<T>(newCodeMsg);
    }


    public T getData() {
        return data;
    }


    public String getMessage() {
        return message;
    }


    public int getRetCode() {
        return retCode;
    }

}
