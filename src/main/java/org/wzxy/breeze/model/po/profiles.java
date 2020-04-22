package org.wzxy.breeze.model.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author 覃能健
 * @create 2020-03
 */
//@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@Validated
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class profiles  implements Serializable {
    @NotNull
    private int pId;
    private String pInfo;
    private int id;

    public profiles() {
        super();
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getpInfo() {
        return pInfo;
    }

    public void setpInfo(String pInfo) {
        this.pInfo = pInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
