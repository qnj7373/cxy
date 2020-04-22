package org.wzxy.breeze.model.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-03
 */
//@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@Validated
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class users implements Serializable {
    @NotNull
    private int userId;
    private String name;
    private String password;
    private profiles profile;
    private List<role> roles;

    public users() {
        super();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public profiles getProfile() {
        return profile;
    }

    public void setProfile(profiles profile) {
        this.profile = profile;
    }

    public List<role> getRoles() {
        return roles;
    }

    public void setRoles(List<role> roles) {
        this.roles = roles;
    }
}
