package org.wzxy.breeze.model.vo;

/**
 * @author 覃能健
 * @create 2020-04
 */

public class ResponseResult<T> {
    private String message;
    private T data;;
    private int status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
