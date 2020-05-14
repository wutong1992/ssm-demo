package com.zhu.demo.common;

/**
 * @Author: zhutao
 * @Date: 2020/4/29 16:15
 */
public class CodeMsg implements Cloneable {
    private int retCode;
    private String message;

    public static CodeMsg EMPTY_PARAM_ERROR = new CodeMsg(400, "参数异常");
    public static CodeMsg INTER_ERROR = new CodeMsg(505, "服务端异常");

    private CodeMsg(int retCode, String message) {
        this.retCode = retCode;
        this.message = message;
    }

    public int getRetCode() {
        return retCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return (CodeMsg)super.clone();
    }

}
