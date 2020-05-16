package org.wzxy.breeze.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author 覃能健
 * @create 2020-04
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
 public class ResponseCode implements Serializable {

    private static final int okcode=200;
    private static final int failcode=201;
    private static final int errorcode=-1;


    public ResponseCode() {
    }

    public static int getOkcode() {
        return okcode;
    }

    public static int getFailcode() {
        return failcode;
    }

    public static int getErrorcode() {
        return errorcode;
    }
}
