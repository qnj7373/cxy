package org.wzxy.breeze.model.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-04
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class role  implements Serializable {
    private  int roleId;
    private  String roleName;
    private  int roleGroup;
    private List<menu> menus;

    public role() {
      super();
    }
    public role(int roleId, String roleName, int roleGroup) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleGroup = roleGroup;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getRoleGroup() {
        return roleGroup;
    }

    public void setRoleGroup(int roleGroup) {
        this.roleGroup = roleGroup;
    }

    public List<menu> getMenus() {
        return menus;
    }

    public void setMenus(List<menu> menus) {
        this.menus = menus;
    }
}
