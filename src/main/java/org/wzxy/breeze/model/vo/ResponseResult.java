package org.wzxy.breeze.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author 覃能健
 * @create 2020-04
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class ResponseResult<T> implements Serializable {
    private String message;
    private String url;
    private T data;
    private T dataBackUp;
    private HashMap<String,T> mapData;
    private int status;

    public ResponseResult() {
    }

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


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public HashMap<String, T> getMapData() {
        return mapData;
    }

    public void setMapData(HashMap<String, T> mapData) {
        this.mapData = mapData;
    }

    public T getDataBackUp() {
        return dataBackUp;
    }

    public void setDataBackUp(T dataBackUp) {
        this.dataBackUp = dataBackUp;
    }
}
