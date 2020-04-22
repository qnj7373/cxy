package org.wzxy.breeze.model.vo;

/**
 * @author 覃能健
 * @create 2020-04
 */
 public class ResponseCode {

    private static final int okcode=200;
    private static final int errorcode=-1;

    public static int getOkcode() {
        return okcode;
    }

    public static int getErrorcode() {
        return errorcode;
    }
}
